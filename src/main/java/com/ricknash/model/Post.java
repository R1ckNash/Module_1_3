package com.ricknash.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Post implements Identifiable {

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
