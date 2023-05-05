package com.ricknash.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Post implements Identifiable {

    private Integer id;
    private String content;
    private String created;
    private String updated;
    private List<Label> labels;
    private PostStatus status;

}
