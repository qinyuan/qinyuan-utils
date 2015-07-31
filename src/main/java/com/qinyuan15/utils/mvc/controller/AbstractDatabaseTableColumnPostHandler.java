package com.qinyuan15.utils.mvc.controller;

public abstract class AbstractDatabaseTableColumnPostHandler implements DatabaseTableColumnPostHandler {
    /*@Override
    public void handle(int columnIndex, Table.Row row) {
        row.getCols()[columnIndex] = handle(row.getCols()[columnIndex]);
    }*/

    abstract public Object handle(Object targetValue);
}
