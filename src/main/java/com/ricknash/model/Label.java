package com.ricknash.model;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Label implements Identifiable {

    private Integer id;

    @NonNull
    private String name;

    private PostStatus status = PostStatus.ACTIVE;
}
