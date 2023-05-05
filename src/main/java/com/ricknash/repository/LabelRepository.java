package com.ricknash.repository;

import com.ricknash.model.Label;

public interface LabelRepository extends GenericRepository<Label, Integer> {
    Label getByName(String name);
}
