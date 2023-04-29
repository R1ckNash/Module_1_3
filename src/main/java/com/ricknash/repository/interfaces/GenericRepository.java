package com.ricknash.repository.interfaces;

import java.util.List;

public interface GenericRepository<T, ID> {

    List<T> getAll();

    T getById(Integer id);

    void insert(T entity);

    void update(T oldEntity, T newEntity);

    void deleteById(Integer id);
}
