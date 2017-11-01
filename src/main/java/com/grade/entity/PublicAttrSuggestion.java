package com.grade.entity;

import java.io.Serializable;

public class PublicAttrSuggestion implements Serializable {
    private Short suggestId;

    private Short suggestNo;

    private String suggest;

    private static final long serialVersionUID = 1L;

    public Short getSuggestId() {
        return suggestId;
    }

    public void setSuggestId(Short suggestId) {
        this.suggestId = suggestId;
    }

    public Short getSuggestNo() {
        return suggestNo;
    }

    public void setSuggestNo(Short suggestNo) {
        this.suggestNo = suggestNo;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest == null ? null : suggest.trim();
    }
}