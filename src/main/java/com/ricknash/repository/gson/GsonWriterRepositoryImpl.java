package com.ricknash.repository.gson;

import com.google.gson.reflect.TypeToken;
import com.ricknash.model.Writer;
import com.ricknash.repository.WriterRepository;

import java.util.List;


public class GsonWriterRepositoryImpl extends AbstractRepository<Writer> implements WriterRepository {

    public GsonWriterRepositoryImpl(String filePath) {
        super(filePath, new TypeToken<List<Writer>>(){});
    }
}
