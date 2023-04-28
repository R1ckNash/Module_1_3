package com.ricknash.model;

public enum PostStatus {

    ACTIVE("ACTIVE"),
    UNDER_REVIEW("UNDER_REVIEW"),
    DELETED("DELETED"),
    INVALID("INVALID");

    private String status;

    PostStatus(String status) {
        this.status = status;
    }
    
    public static PostStatus fromValue(String value) {
        if (value == null) {
            return null;
        }
        for (PostStatus postStatus : PostStatus.values()) {
            if (postStatus.status.equalsIgnoreCase(value)) {
                return postStatus;
            }
        }
        return INVALID;
    }
}
