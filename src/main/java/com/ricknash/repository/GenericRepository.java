package com.ricknash.repository;

import java.util.List;

public interface GenericRepository<T, ID> {

    List<T> getAll();

    T getById(Integer id);

    T insert(T entity);

    T update(T newEntity);

    void deleteById(Integer id);
}
