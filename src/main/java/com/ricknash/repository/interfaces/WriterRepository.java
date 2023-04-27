package com.ricknash.repository.interfaces;


import com.ricknash.model.Writer;

import java.util.List;

public interface WriterRepository extends GenericRepository<Writer, Integer> {

    List<Writer> getAll();

    void insert(Writer writer);

    void deleteById(Integer id);
}
