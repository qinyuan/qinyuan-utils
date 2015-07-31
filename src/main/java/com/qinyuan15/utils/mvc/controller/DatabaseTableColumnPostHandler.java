package com.qinyuan15.utils.mvc.controller;

public interface DatabaseTableColumnPostHandler {
    /*/**
     * deal with table row
     *
     * @param columnIndex column index of target field
     * @param row         row of table
     */
    //void handle(int columnIndex, Table.Row row);

    /**
     * deal with target value
     *
     * @param targetValue value of target field
     * @return new value to replace target value
     */
    Object handle(Object targetValue);
}
