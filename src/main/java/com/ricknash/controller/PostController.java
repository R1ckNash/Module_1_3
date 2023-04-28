package com.ricknash.controller;

import com.ricknash.model.Label;
import com.ricknash.model.Post;
import com.ricknash.repository.implementations.AbstractRepository;
import com.ricknash.view.LabelView;
import com.ricknash.view.PostView;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PostController implements Controller<Post> {

    @NonNull
    private AbstractRepository<Post> postRepository;
    @NonNull
    private PostView postView;

    @Override
    public void create(String name) {

    }

    @Override
    public void getAll() {
        List<Post> posts = postRepository.getAll();
        postView.updateView(posts);
    }

    @Override
    public void getByIdAndPrint(String id) {

    }

    @Override
    public Post getById(String id) {
        return null;
    }

    @Override
    public void update(String id, String name, String status) {

    }

    @Override
    public void delete(String id) {

    }
}
