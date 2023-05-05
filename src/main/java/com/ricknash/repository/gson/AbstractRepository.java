package com.ricknash.repository.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ricknash.model.Identifiable;
import com.ricknash.repository.GenericRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public abstract class AbstractRepository<T> implements GenericRepository<T, Integer> {

    private final File file;
    private final Gson objectMapper;
    private final TypeToken<List<T>> targetClassType;

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

    private Integer generateId(List<T> entities) {
        OptionalInt max = entities.stream().mapToInt(e -> ((Identifiable) e).getId()).max();
        if (max.isEmpty()) {
            return 1;
        }
        return max.getAsInt() + 1;
    }

    public T insert(T entity) {
        List<T> entities = getAllItemsFromTheFile();
        Integer newId = generateId(entities);
        ((Identifiable) entity).setId(newId);
        entities.add(entity);
        saveToFile(entities);
        return entity;
    }

    public T update(T newEntity) {
        List<T> updatedList = getAllItemsFromTheFile().stream().map(e -> {
            if (((Identifiable) e).getId().equals(((Identifiable) newEntity).getId())) {
                return newEntity;
            }
            return e;
        }).collect(Collectors.toList());
        saveToFile(updatedList);
        return newEntity;
    }

    private void saveToFile(List<T> entities) {
        try {
            String data = objectMapper.toJson(entities);
            Files.writeString(file.toPath(), data);
        } catch (IOException e) {
            System.err.println("Exception while saving writers: " + e);
        }
    }

    public List<T> getAll() {
        return getAllItemsFromTheFile();
    }

    private List<T> getAllItemsFromTheFile() {
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
            throw new RuntimeException("There is no entity with id: " + id);
        }
    }

    public void deleteById(Integer id) {
        List<T> data = getAllItemsFromTheFile();
        data.removeIf(d -> !((Identifiable) d).getId().equals(id));
        saveToFile(data);
    }
}
