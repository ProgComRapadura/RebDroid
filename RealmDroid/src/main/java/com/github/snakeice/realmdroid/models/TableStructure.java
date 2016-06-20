package com.github.snakeice.realmdroid.models;

import java.util.List;

/**
 * Basic structure of a database table to be filled without knowledge of it
 * Created by Rodrigo on 12/05/2016.
 */
public class TableStructure {

    private String name;
    private List<Column> columns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}

