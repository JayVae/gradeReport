package com.grade.entity;

import java.io.Serializable;

public class StuAttrGrade implements Serializable {
    private Integer sagId;

    private String studentId;

    private Float attr1Grade;

    private Float attr2Grade;

    private Float attr3Grade;

    private Float attr4Grade;

    private Float attr5Grade;

    private Float attr6Grade;

    private Float attr7Grade;

    private static final long serialVersionUID = 1L;

    public Integer getSagId() {
        return sagId;
    }

    public void setSagId(Integer sagId) {
        this.sagId = sagId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public Float getAttr1Grade() {
        return attr1Grade;
    }

    public void setAttr1Grade(Float attr1Grade) {
        this.attr1Grade = attr1Grade;
    }

    public Float getAttr2Grade() {
        return attr2Grade;
    }

    public void setAttr2Grade(Float attr2Grade) {
        this.attr2Grade = attr2Grade;
    }

    public Float getAttr3Grade() {
        return attr3Grade;
    }

    public void setAttr3Grade(Float attr3Grade) {
        this.attr3Grade = attr3Grade;
    }

    public Float getAttr4Grade() {
        return attr4Grade;
    }

    public void setAttr4Grade(Float attr4Grade) {
        this.attr4Grade = attr4Grade;
    }

    public Float getAttr5Grade() {
        return attr5Grade;
    }

    public void setAttr5Grade(Float attr5Grade) {
        this.attr5Grade = attr5Grade;
    }

    public Float getAttr6Grade() {
        return attr6Grade;
    }

    public void setAttr6Grade(Float attr6Grade) {
        this.attr6Grade = attr6Grade;
    }

    public Float getAttr7Grade() {
        return attr7Grade;
    }

    public void setAttr7Grade(Float attr7Grade) {
        this.attr7Grade = attr7Grade;
    }
}