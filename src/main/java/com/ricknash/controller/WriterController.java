package com.ricknash.controller;

import com.ricknash.model.Post;
import com.ricknash.model.PostStatus;
import com.ricknash.model.Writer;
import com.ricknash.repository.implementations.AbstractRepository;
import com.ricknash.view.WriterView;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class WriterController implements Controller<Writer> {

    @NonNull
    private AbstractRepository<Writer> writerRepository;
    @NonNull
    private WriterView writerView;
    @NonNull
    private PostController pc;

    public void create(String firstName, String lastName, List<String> posts, List<String> labels) {
        List<Post> newPosts = posts.stream()
                .map(p -> pc.create(p, labels))
                .collect(Collectors.toList());
        Writer writer = new Writer(firstName, lastName, newPosts);
        Integer id = UUID.randomUUID().hashCode();
        writer.setId(id);
        writerRepository.insert(writer);
        writerView.updateView(writer);
    }

    @Override
    public void getAll() {
        List<Writer> writers = writerRepository.getAll();
        writerView.updateView(writers);
    }

    @Override
    public void getByIdAndUpdateView(String id) {
        Writer writer = getById(id);
        if (Objects.isNull(writer)) {
            return;
        }
        writerView.updateView(writer);
    }

    @Override
    public Writer getById(String id) {
        try {
            return writerRepository.getById(Integer.valueOf(id));
        } catch (RuntimeException e) {
            writerView.updateView("Exception: " + e);
            return null;
        }
    }

    public void update(String id, String firstName, String lastName, String status) {
        Writer writer = getById(id);
        if (Objects.isNull(writer)) {
            writerView.updateView("There is no writer with id: " + id);
            return;
        }

        PostStatus newStatus = PostStatus.fromValue(status);
        if (newStatus == null) {
            writerView.updateView("Status in null");
            return;
        }

        Writer updatedWriter = new Writer(Integer.valueOf(id),
                firstName,
                lastName,
                writer.getPosts(),
                newStatus);

        writerRepository.update(writer, updatedWriter);
        writerView.updateView(updatedWriter);
    }

    @Override
    public void delete(String id) {
        Writer writer = getById(id);
        if (Objects.isNull(writer)) {
            writerView.updateView("There is no writer with id: " + id);
            return;
        }
        writerRepository.deleteById(writer.getId());
        writer.setStatus(PostStatus.DELETED);
        writerRepository.insert(writer);
        writerView.updateView("Writer was deleted");
    }
}
