package com.github.snakeice.realmdroid.models;

import com.github.snakeice.realmdroid.enums.FieldTypes;

import io.realm.RealmFieldType;

/**
 * Created by Rodrigo on 12/05/2016.
 */
public class Column {

    private String name;
    private RealmFieldType type;
    private Boolean primaryKey;

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    private Boolean required;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmFieldType getType() {
        return type;
    }

    public void setType(RealmFieldType fieldType) {
        this.type = fieldType;
    }
}
