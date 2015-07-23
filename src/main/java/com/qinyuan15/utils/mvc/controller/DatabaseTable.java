package com.qinyuan15.utils.mvc.controller;

import com.google.common.base.Joiner;
import com.qinyuan15.utils.hibernate.HibernateListBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement Table by SQL or HQL
 * Created by qinyuan on 15-7-23.
 */
public class DatabaseTable extends AbstractTable implements PaginationItemFactory<Table.Row> {
    public final static QueryType DEFAULT_QUERY_TYPE = QueryType.HQL;

    private final String tableName;
    private final QueryType queryType;
    private final List<String> fields = new ArrayList<>();
    private final HibernateListBuilder listBuilder = new HibernateListBuilder();
    private final String keyField;

    public DatabaseTable(String tableName, String keyField, QueryType queryType) {
        this.tableName = tableName;
        this.queryType = queryType == null ? DEFAULT_QUERY_TYPE : queryType;
        this.keyField = keyField;
    }

    public DatabaseTable(String tableName, String keyField) {
        this(tableName, keyField, DEFAULT_QUERY_TYPE);
    }

    public DatabaseTable addField(String head, String field, String alias) {
        addHeadAlias(head, alias);
        fields.add(field);
        return this;
    }

    public DatabaseTable addField(String head, String field) {
        return addField(head, field, field);
    }

    public DatabaseTable addOrder(String field, boolean asc) {
        listBuilder.addOrder(field, asc);
        return this;
    }

    public DatabaseTable addEqualFilter(String field, Object value) {
        listBuilder.addEqualFilter(field, value);
        return this;
    }

    public DatabaseTable addFilter(String filter) {
        listBuilder.addFilter(filter);
        return this;
    }

    public DatabaseTable addFilter(String field, List<?> availableValues) {
        if (availableValues == null || availableValues.size() == 0) {
            return this;
        }

        String filter = field + " IN (";
        int i = 0;
        for (Object availableValue : availableValues) {
            if (i > 0) {
                filter += ",";
            }
            String paramName = field + "_" + i + "_" + RandomStringUtils.randomAlphanumeric(5);

            filter += ":" + paramName;
            listBuilder.addArgument(paramName, availableValue);
            i++;
        }
        filter += ")";
        listBuilder.addFilter(filter);
        return this;
    }

    @Override
    public long getCount() {
        if (queryType.equals(QueryType.SQL)) {
            return listBuilder.countBySQL(tableName);
        } else if (queryType.equals(QueryType.HQL)) {
            return listBuilder.count(tableName);
        } else {
            throw new RuntimeException("Invalid query type");
        }
    }

    @Override
    public List<Row> getInstances(int firstResult, int maxResults) {
        return getRows(firstResult, maxResults);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Row> getRows(int firstReset, int maxResults) {
        String query = "SELECT " + getFieldString() + " FROM " + tableName;

        listBuilder.limit(firstReset, maxResults);
        List<Object[]> list;
        if (queryType.equals(QueryType.SQL)) {
            list = listBuilder.buildBySQL(query);
        } else if (queryType.equals(QueryType.HQL)) {
            list = listBuilder.build(query);
        } else {
            return new ArrayList<>();
        }

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

        return rows;
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
