package com.grade.entity;

import java.io.Serializable;

public class Questionattr implements Serializable {
    private Short quesId;

    private Short typeId;

    private Short attr1;

    private Short attr2;

    private Short attr3;

    private Short attr4;

    private Short attr5;

    private Short attr6;

    private Short attr7;

    private Short subgrade;

    private static final long serialVersionUID = 1L;

    public Short getQuesId() {
        return quesId;
    }

    public void setQuesId(Short quesId) {
        this.quesId = quesId;
    }

    public Short getTypeId() {
        return typeId;
    }

    public void setTypeId(Short typeId) {
        this.typeId = typeId;
    }

    public Short getAttr1() {
        return attr1;
    }

    public void setAttr1(Short attr1) {
        this.attr1 = attr1;
    }

    public Short getAttr2() {
        return attr2;
    }

    public void setAttr2(Short attr2) {
        this.attr2 = attr2;
    }

    public Short getAttr3() {
        return attr3;
    }

    public void setAttr3(Short attr3) {
        this.attr3 = attr3;
    }

    public Short getAttr4() {
        return attr4;
    }

    public void setAttr4(Short attr4) {
        this.attr4 = attr4;
    }

    public Short getAttr5() {
        return attr5;
    }

    public void setAttr5(Short attr5) {
        this.attr5 = attr5;
    }

    public Short getAttr6() {
        return attr6;
    }

    public void setAttr6(Short attr6) {
        this.attr6 = attr6;
    }

    public Short getAttr7() {
        return attr7;
    }

    public void setAttr7(Short attr7) {
        this.attr7 = attr7;
    }

    public Short getSubgrade() {
        return subgrade;
    }

    public void setSubgrade(Short subgrade) {
        this.subgrade = subgrade;
    }
}