package com.ricknash.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Writer implements Identifiable {

    private Integer id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private List<Post> posts;

    private PostStatus status = PostStatus.ACTIVE;
}
