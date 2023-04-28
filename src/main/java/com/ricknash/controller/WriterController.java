package com.ricknash.controller;

import com.ricknash.model.Writer;
import com.ricknash.repository.implementations.AbstractRepository;
import com.ricknash.view.WriterView;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WriterController implements Controller<Writer> {

    @NonNull
    private AbstractRepository<Writer> writerRepository;
    @NonNull
    private WriterView writerView;

    @Override
    public void getAll() {

    }

    @Override
    public void getByIdAndPrint(String id) {

    }

    @Override
    public Writer getById(String id) {
        return null;
    }

    @Override
    public void update(String id, String name, String status) {

    }

    @Override
    public void delete(String id) {

    }
}
