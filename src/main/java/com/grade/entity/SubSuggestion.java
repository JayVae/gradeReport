package com.grade.entity;

import java.io.Serializable;

public class SubSuggestion implements Serializable {
    private Short ssId;

    private Short suggestId;

    private Short ssNo;

    private String ssSuggest;

    private static final long serialVersionUID = 1L;

    public Short getSsId() {
        return ssId;
    }

    public void setSsId(Short ssId) {
        this.ssId = ssId;
    }

    public Short getSuggestId() {
        return suggestId;
    }

    public void setSuggestId(Short suggestId) {
        this.suggestId = suggestId;
    }

    public Short getSsNo() {
        return ssNo;
    }

    public void setSsNo(Short ssNo) {
        this.ssNo = ssNo;
    }

    public String getSsSuggest() {
        return ssSuggest;
    }

    public void setSsSuggest(String ssSuggest) {
        this.ssSuggest = ssSuggest == null ? null : ssSuggest.trim();
    }
}