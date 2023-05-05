package com.ricknash.model;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class Label implements Identifiable {

    private Integer id;
    private String name;
    private PostStatus status;
}
