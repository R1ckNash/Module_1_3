package com.ricknash.repository.implementations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ricknash.model.Writer;
import com.ricknash.repository.interfaces.WriterRepository;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class GsonWriterRepositoryImpl implements WriterRepository {

    private final Gson objectMapper;
    private final File file;

    public GsonWriterRepositoryImpl(String filePath) {
        this.file = new File(filePath);
        this.objectMapper = new GsonBuilder().setPrettyPrinting().create();

        if (!file.exists()) {
            try {
                file.createNewFile();
                Files.write(file.toPath(), "[]".getBytes());
            } catch (IOException e) {
                System.err.println("Error while creating a file: " + e);
            }
        }
    }

    @Override
    public void insert(Writer writer) {
        List<Writer> writers = getAll();
        writers.add(writer);
        save(writers);
    }

    private void save(List<Writer> writers) {
        try {
            String data = objectMapper.toJson(writers);
            Files.writeString(file.toPath(), data);
        } catch (IOException e) {
            System.err.println("Exception while saving writers: " + e);
        }
    }

    @Override
    public List<Writer> getAll() {
        try {
            String data = new String(Files.readAllBytes(file.toPath()));
            return objectMapper.fromJson(data, new TypeToken<List<Writer>>() {}.getType());
        } catch (IOException e) {
            System.err.println("Error while getting all writers: " + e);
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteById(Integer id) {
        List<Writer> writers = getAll().stream()
                .filter(w -> !w.getId().equals(id))
                .collect(Collectors.toList());
        save(writers);
    }
}
