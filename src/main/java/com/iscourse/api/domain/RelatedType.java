package com.iscourse.api.domain;

public enum RelatedType {
    DEAL("/deal/");

    private String path;

    RelatedType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
