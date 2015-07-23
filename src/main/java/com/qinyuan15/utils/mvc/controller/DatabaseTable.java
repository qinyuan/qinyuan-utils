package com.qinyuan15.utils.mvc.controller;

import com.google.common.base.Joiner;
import com.qinyuan15.utils.hibernate.HibernateListBuilder;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement Table by SQL or HQL
 * Created by qinyuan on 15-7-23.
 */
public class DatabaseTable extends AbstractTable {
    private final String tableName;
    private final QueryType queryType;
    private final List<String> fields = new ArrayList<>();
    private final HibernateListBuilder listBuilder = new HibernateListBuilder();
    private String keyField;

    public DatabaseTable(String tableName, QueryType queryType) {
        this.tableName = tableName;
        this.queryType = queryType;
    }

    public DatabaseTable setKeyField(String keyField) {
        this.keyField = keyField;
        return this;
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

    public DatabaseTable addFilter(String filter) {
        listBuilder.addFilter(filter);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Row> getRows() {
        String query = "SELECT " + getFieldString() + " FROM " + tableName;

        List<Object[]> list;
        if (queryType.equals(QueryType.SQL)) {
            list = listBuilder.buildBySQL(query);
        } else if (queryType.equals(QueryType.HQL)) {
            list = listBuilder.build(query);
        } else {
            return new ArrayList<>();
        }

        List<Row> rows = new ArrayList<>();


        return null;
    }

    private String getFieldString() {
        List<String> fieldsWithAlias = new ArrayList<>();
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

    private static enum QueryType {
        SQL, HQL
    }
}
