package com.ricknash.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Post implements Identifiable {

    @NonNull
    private Integer id;
    @NonNull
    private String content;
    @NonNull
    private String created;
    @NonNull
    private String updated;
    @NonNull
    private List<Label> labels;
    private PostStatus status = PostStatus.ACTIVE;

}
