package com.ricknash.repository.interfaces;

import java.util.List;

public interface GenericRepository<T, ID> {

    List<T> getAll();

    void insert(T entity);

    void deleteById(Integer id);
}
