package com.ricknash.controller;

import java.util.List;

public interface Controller<T> {

    default T create(String name) {return null;}

    default T create(String content, List<String> labels) {return null;}

    default void create(String firstName, String lastName, List<String> posts, List<String> labels) {};

    default void createAndUpdateView(String content, List<String> labels) {}

    default void createAndUpdateView(String name) {}

    void getAll();

    void getByIdAndUpdateView(String id);

    T getById(String id);

    default void update(String id, String name, String status) {};

    default void update(String id, String firstName, String lastName, String status) {};

    void delete(String id);
}
