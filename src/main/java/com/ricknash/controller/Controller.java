package com.ricknash.controller;

public interface Controller<T> {

    void getAll();

    void getByIdAndUpdateView(String id);

    T getById(String id);

    default void update(String id, String name, String status) {};

    void delete(String id);
}
