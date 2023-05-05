package com.ricknash.repository.gson;

import com.google.gson.reflect.TypeToken;
import com.ricknash.model.Label;
import com.ricknash.repository.LabelRepository;

import java.util.List;

public class GsonLabelRepositoryImpl extends AbstractRepository<Label> implements LabelRepository {

    public GsonLabelRepositoryImpl(String filePath) {
        super(filePath, new TypeToken<List<Label>>(){});
    }

    public Label getByName(String name) {
        return getAll().stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
