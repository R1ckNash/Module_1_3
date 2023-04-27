package com.ricknash.repository.implementations;

import com.google.gson.reflect.TypeToken;
import com.ricknash.model.Label;
import com.ricknash.repository.interfaces.LabelRepository;

import java.util.List;

public class GsonLabelRepositoryImpl extends AbstractRepository<Label> implements LabelRepository {

    public GsonLabelRepositoryImpl(String filePath) {
        super(filePath, new TypeToken<List<Label>>(){});
    }
}
