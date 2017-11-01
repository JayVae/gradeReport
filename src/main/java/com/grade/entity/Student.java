package com.grade.entity;

import java.io.Serializable;

public class Student implements Serializable {
    private String studentId;

    private String stuName;
    private String stuExamNo;

    public String getStuExamNo() {
        return stuExamNo;
    }

    public void setStuExamNo(String stuExamNo) {
        this.stuExamNo = stuExamNo;
    }

    private static final long serialVersionUID = 1L;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }
}