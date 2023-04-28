package com.ricknash.controller;

import java.util.List;

public interface Controller<T> {

    default void create(String name) {};

    default void create(String content, List<String> labels) {};

    void getAll();

    void getByIdAndPrint(String id);

    T getById(String id);

    void update(String id, String name, String status);

    void delete(String id);
}
