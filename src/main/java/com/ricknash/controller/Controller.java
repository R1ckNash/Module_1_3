package com.ricknash.controller;

public interface Controller<T> {

    void create(String name);

    void getAll();

    void getByIdAndPrint(String id);

    T getById(String id);

    void update(String id, String name, String status);

    void delete(String id);
}
