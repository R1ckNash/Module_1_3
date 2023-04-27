package com.ricknash.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Label {

    @NonNull
    private Integer id;
    @NonNull
    private String name;
}
