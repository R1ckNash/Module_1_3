package com.ricknash.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Label implements Identifiable {

    @NonNull
    private Integer id;
    @NonNull
    private String name;
    private PostStatus status = PostStatus.ACTIVE;
}
