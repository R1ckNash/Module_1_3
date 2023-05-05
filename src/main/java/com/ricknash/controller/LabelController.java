package com.ricknash.controller;

import com.ricknash.model.Label;
import com.ricknash.model.PostStatus;
import com.ricknash.repository.gson.AbstractRepository;
import com.ricknash.repository.gson.GsonLabelRepositoryImpl;
import com.ricknash.view.LabelView;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
public class LabelController {

    @NonNull
    private AbstractRepository<Label> labelRepository;
    @NonNull
    private LabelView labelView;

    public Label create(String name) {
        Label alreadyExists = ((GsonLabelRepositoryImpl) labelRepository).getByName(name);
        if (!Objects.isNull(alreadyExists)) return alreadyExists;

        Label label = Label.builder()
                .name(name)
                .status(PostStatus.ACTIVE)
                .build();

        labelRepository.insert(label);
        return label;
    }

    public void createAndUpdateView(String name) {
        labelView.updateView(create(name));
    }

    public void getAll() {
        List<Label> labels = labelRepository.getAll();
        labelView.updateView(labels);
    }

    public void getByIdAndUpdateView(String id) {
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
        if (Objects.isNull(label)) {
            labelView.updateView("There is no label with id: " + id);
            return;
        }

        PostStatus newStatus = PostStatus.fromValue(status);
        if (newStatus == null) {
            labelView.updateView("Status id is null");
        }

        Label updatedLabel = new Label(Integer.valueOf(id), name, newStatus);
        labelRepository.update(updatedLabel);
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
