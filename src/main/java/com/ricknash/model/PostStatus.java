package com.ricknash.model;

public enum PostStatus {

    ACTIVE("ACTIVE"),
    UNDER_REVIEW("UNDER_REVIEW"),
    DELETED("DELETED");

    private String status;

    PostStatus(String status) {
        this.status = status;
    }
}
