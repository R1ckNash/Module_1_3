package com.ricknash.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@Builder
public class Writer implements Identifiable {

    private Integer id;
    private String firstName;
    private String lastName;
    private List<Post> posts;
    private PostStatus status;
}
