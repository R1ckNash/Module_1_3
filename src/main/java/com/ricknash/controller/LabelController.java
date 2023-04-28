package com.ricknash.controller;

import com.ricknash.model.Label;
import com.ricknash.model.PostStatus;
import com.ricknash.repository.implementations.AbstractRepository;
import com.ricknash.view.LabelView;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
public class LabelController implements Controller<Label> {

    @NonNull
    private AbstractRepository<Label> labelRepository;
    @NonNull
    private LabelView labelView;

    public void create(String name) {
        Label label = new Label(name);
        Integer id = UUID.randomUUID().hashCode();
        label.setId(id);
        labelRepository.insert(label);

        labelView.updateView(label);
    }

    public void getAll() {
        List<Label> labels = labelRepository.getAll();

        labelView.updateView(labels);
    }

    public void getByIdAndPrint(String id) {
        Label label = getById(id);
        if (Objects.isNull(label)) {
            return;
        }
        labelView.updateView(label);
    }

    public Label getById(String id) throws RuntimeException  {
        try {
            return labelRepository.getById(Integer.valueOf(id));
        } catch (RuntimeException e) {
            System.err.println("Exception: " + e);
            return null;
        }
    }

    public void update(String id, String name, String status) {
        Label label = getById(id);
        if (Objects.isNull(label)) return;

        PostStatus newStatus = PostStatus.fromValue(status);
        if (newStatus == null) return;

        Label updatedLabel = new Label(Integer.valueOf(id), name, newStatus);
        labelRepository.update(label, updatedLabel);

        labelView.updateView(updatedLabel);
    }

    public void delete(String id) {
        Label label = getById(id);
        if (Objects.isNull(label)) {
            labelView.updateView("There is no label with id: " + id);
            return;
        }
        labelRepository.deleteById(label.getId());
        label.setStatus(PostStatus.DELETED);
        labelRepository.insert(label);

        labelView.updateView("Label was deleted");
    }
}
