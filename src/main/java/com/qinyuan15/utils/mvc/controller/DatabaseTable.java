package com.qinyuan15.utils.mvc.controller;

import com.google.common.base.Joiner;
import com.qinyuan15.utils.DateUtils;
import com.qinyuan15.utils.database.hibernate.HibernateListBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Implement Table by SQL or HQL
 * Created by qinyuan on 15-7-23.
 */
public class DatabaseTable extends AbstractTable {
    public final static QueryType DEFAULT_QUERY_TYPE = QueryType.HQL;

    private final String tableName;
    private final QueryType queryType;
    private final List<String> fields = new ArrayList<>();
    private final String keyField;
    private final List<String> normalFilters = new ArrayList<>();
    private final List<Pair<String, Boolean>> orderFields = new ArrayList<>();
    private final List<Pair<String, Object>> equalFilters = new ArrayList<>();
    private final List<Pair<String, List>> inFilters = new ArrayList<>();
    private final List<DatabaseTableColumnPostHandler> columnPostHandlers = new ArrayList<>();

    public DatabaseTable(String tableName, String keyField, QueryType queryType) {
        this.tableName = tableName;
        this.queryType = queryType == null ? DEFAULT_QUERY_TYPE : queryType;
        this.keyField = keyField;
    }

    public DatabaseTable(String tableName, String keyField) {
        this(tableName, keyField, DEFAULT_QUERY_TYPE);
    }

    public DatabaseTable addField(String head, String field, String alias, DatabaseTableColumnPostHandler postHandler) {
        addHeadAlias(head, alias);
        fields.add(field);
        columnPostHandlers.add(postHandler);
        return this;
    }

    public DatabaseTable addField(String head, String field, String alias) {
        return addField(head, field, alias, null);
    }

    public DatabaseTable addField(String head, String field) {
        return addField(head, field, field);
    }

    public DatabaseTable addOrder(String field, boolean asc) {
        orderFields.add(Pair.of(field, asc));
        return this;
    }

    public DatabaseTable addEqualFilter(String field, Object value) {
        equalFilters.add(Pair.of(field, value));
        return this;
    }

    public DatabaseTable addFilter(String filter) {
        normalFilters.add(filter);
        return this;
    }

    public DatabaseTable addFilter(String field, List availableValues) {
        if (availableValues == null) {
            return this;
        }
        inFilters.add(Pair.of(field, availableValues));
        return this;
    }

    @Override
    public long getCount() {
        if (queryType.equals(QueryType.SQL)) {
            return getFilteredListBuilder().countBySQL(tableName);
        } else if (queryType.equals(QueryType.HQL)) {
            return getFilteredListBuilder().count(tableName);
        } else {
            throw new RuntimeException("Invalid query type");
        }
    }

    @Override
    public List<Row> getInstances(int firstResult, int maxResults) {
        return getRows(firstResult, maxResults);
    }

    @Override
    public List<Row> getRows() {
        return getRows(-1, -1);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Row> getRows(int firstReset, int maxResults) {
        // TODO if there is only one field, exception will be raised up
        String query = "SELECT " + getFieldString() + " FROM " + tableName;

        HibernateListBuilder listBuilder = getFilteredListBuilder();
        addOrderFieldsToListBuilder(listBuilder);

        listBuilder.limit(firstReset, maxResults);
        List<Object[]> list = getList(listBuilder, query);
        List<Row> rows = new ArrayList<>();
        for (Object[] objects : list) {
            if (StringUtils.hasText(keyField)) {
                Object[] cols = new Object[objects.length - 1];
                System.arraycopy(objects, 1, cols, 0, cols.length);
                rows.add(new Row((Integer) objects[0], cols));
            } else {
                rows.add(new Row(null, objects));
            }
        }

        for (int i = 0; i < columnPostHandlers.size(); i++) {
            DatabaseTableColumnPostHandler handler = columnPostHandlers.get(i);
            int columnIndexToHandle = StringUtils.hasText(keyField) ? i : i - 1;
            if (handler != null && columnIndexToHandle >= 0) {
                for (Row row : rows) {
                    row.getCols()[columnIndexToHandle] = handler.handle(row.getCols()[columnIndexToHandle]);
                }
            }
        }

        // adjust datetime
        for (Row row : rows) {
            for (int i = 0; i < row.getCols().length; i++) {
                Object col = row.getCols()[i];
                if (col instanceof Timestamp && DateUtils.isDateTimeFromMySQL(col.toString())) {
                    row.getCols()[i] = DateUtils.trimMilliSecond(col.toString());
                }
            }
        }

        return rows;
    }

    // TODO 当存在columnPostHandlers时，getDistinctValues和getRows中的结果会出现一致性问题
    @SuppressWarnings("unchecked")
    public List getDistinctValues(String field) {
        if (!StringUtils.hasText(field)) {
            return new ArrayList<>();
        }

        String readField = getFieldByAlias(field);
        String query = "SELECT DISTINCT ";
        if (readField.equals(field)) {
            query += field;
        } else {
            query += readField + " AS " + field;
        }
        query += " FROM " + tableName;

        HibernateListBuilder listBuilder = getFilteredListBuilder();
        List list = getList(listBuilder, query);

        // adjust datetime
        for (int i = 0; i < list.size(); i++) {
            Object value = list.get(i);
            if (value instanceof Timestamp && DateUtils.isDateTimeFromMySQL(value.toString())) {
                list.set(i, DateUtils.trimMilliSecond(value.toString()));
            }
        }

        return list;
    }

    private List getList(HibernateListBuilder listBuilder, String query) {
        if (queryType.equals(QueryType.SQL)) {
            return listBuilder.buildBySQL(query);
        } else if (queryType.equals(QueryType.HQL)) {
            return listBuilder.build(query);
        } else {
            return new ArrayList<>();
        }
    }

    private HibernateListBuilder getFilteredListBuilder() {
        HibernateListBuilder listBuilder = new HibernateListBuilder();
        addFiltersToListBuilder(listBuilder);
        return listBuilder;
    }

    private void addFiltersToListBuilder(HibernateListBuilder listBuilder) {
        // add normal filters
        for (String filter : normalFilters) {
            listBuilder.addFilter(filter);
        }

        // add equal filters
        for (Pair<String, Object> filter : equalFilters) {
            listBuilder.addEqualFilter(getFieldByAlias(filter.getLeft()), filter.getRight());
        }

        // add in filters
        for (Pair<String, List> inFilter : inFilters) {
            String alias = inFilter.getLeft();
            addFilterStyle(alias);

            List availableValues = inFilter.getRight();
            if (availableValues.size() == 0) {
                listBuilder.addFilter("false");
                break;
            }

            String field = getFieldByAlias(alias);
            String filter = "";
            int i = 0;
            boolean hasNull = false;
            for (Object availableValue : availableValues) {
                if (availableValue == null) {
                    hasNull = true;
                    continue;
                }

                filter += (i == 0 ? (field + " IN (") : ",");
                String paramName = field + "_" + i + "_" + RandomStringUtils.randomAlphanumeric(5);

                filter += ":" + paramName;
                listBuilder.addArgument(paramName, availableValue);
                i++;
            }
            if (!filter.isEmpty()) {
                filter += ")";
            }
            if (hasNull) {
                if (!filter.isEmpty()) {
                    filter += " OR ";
                }
                filter += field + " IS null";
            }
            listBuilder.addFilter(filter);
        }
    }

    private void addOrderFieldsToListBuilder(HibernateListBuilder listBuilder) {
        for (Pair<String, Boolean> orderField : orderFields) {
            addOrderStyle(orderField.getLeft(), orderField.getRight());
            listBuilder.addOrder(getFieldByAlias(orderField.getLeft()), orderField.getRight());
        }
    }

    private String getFieldByAlias(String alias) {
        if (alias == null) {
            return null;
        }

        List<String> aliases = getAliases();
        for (int i = 0; i < aliases.size(); i++) {
            if (alias.equals(aliases.get(i)) && i < fields.size()) {
                return fields.get(i);
            }
        }
        return alias;
    }

    private String getFieldString() {
        List<String> fieldsWithAlias = new ArrayList<>();
        if (StringUtils.hasText(keyField)) {
            fieldsWithAlias.add(keyField);
        }

        List<String> aliases = getAliases();
        for (int i = 0; i < Math.min(aliases.size(), fields.size()); i++) {
            String field = fields.get(i);
            if (!StringUtils.hasText(field)) {
                continue;
            }
            String alias = aliases.get(i);

            if (field.equals(alias)) {
                fieldsWithAlias.add(field);
            } else {
                fieldsWithAlias.add(field + " AS " + alias);
            }
        }
        return Joiner.on(",").join(fieldsWithAlias);
    }

    public static enum QueryType {
        SQL, HQL
    }
}
