package com.grade.entity;

import java.io.Serializable;

public class Questiontype implements Serializable {
    private Short typeId;

    private String typeName;

    private static final long serialVersionUID = 1L;

    public Short getTypeId() {
        return typeId;
    }

    public void setTypeId(Short typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }
}