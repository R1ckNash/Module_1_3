package com.ricknash.repository.implementations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ricknash.model.Identifiable;
import com.ricknash.repository.interfaces.GenericRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractRepository<T> implements GenericRepository<T, Integer> {

    File file;
    private final Gson objectMapper;
    TypeToken<List<T>> targetClassType;

    AbstractRepository(String filePath, TypeToken<List<T>> token) {
        this.file = new File(filePath);
        this.objectMapper = new GsonBuilder().setPrettyPrinting().create();
        this.targetClassType = token;
        if (!file.exists()) {
            try {
                file.createNewFile();
                Files.write(file.toPath(), "[]".getBytes());
            } catch (IOException e) {
                System.err.println("Error while creating a file: " + file.getName() + " " + e);
            }
        }
    }

    public void insert(T entity) {
        List<T> entities = getAll();
        entities.add(entity);
        save(entities);
    }

    public void update(T oldEntity, T newEntity) {
        List<T> entities = getAll();
        int index = entities.indexOf(oldEntity);
        entities.set(index, newEntity);
        save(entities);
    }

    private void save(List<T> entities) {
        try {
            String data = objectMapper.toJson(entities);
            Files.writeString(file.toPath(), data);
        } catch (IOException e) {
            System.err.println("Exception while saving writers: " + e);
        }
    }

    public List<T> getAll() {
        try {
            String data = new String(Files.readAllBytes(file.toPath()));
            return objectMapper.fromJson(data, targetClassType.getType());
        } catch (IOException e) {
            System.err.println("Error while getting all: " + e);
            return new ArrayList<>();
        }
    }

    public T getById(Integer id) throws RuntimeException {
        Optional<T> entity = getAll().stream()
                .filter(d -> ((Identifiable) d).getId().equals(id))
                .findFirst();
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new RuntimeException("There is no entity with id - " + id);
        }
    }

    public void deleteById(Integer id) {
        List<T> data = getAll().stream()
                .filter(d -> !((Identifiable)d).getId().equals(id))
                .collect(Collectors.toList());
        save(data);
    }
}
