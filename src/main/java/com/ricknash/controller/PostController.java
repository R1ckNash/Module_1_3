package com.ricknash.controller;

import com.ricknash.model.Label;
import com.ricknash.model.Post;
import com.ricknash.model.PostStatus;
import com.ricknash.repository.implementations.AbstractRepository;
import com.ricknash.view.PostView;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class PostController implements Controller<Post> {

    @NonNull
    private AbstractRepository<Post> postRepository;
    @NonNull
    private PostView postView;
    @NonNull
    private LabelController lc;

    public Post create(String content, List<String> labels) {
        String created = OffsetDateTime.now().toString();
        List<Label> newLabels = labels.stream()
                .map(lc::create)
                .collect(Collectors.toList());

        Post post = new Post(content, created, created, newLabels);
        Integer id = UUID.randomUUID().hashCode();
        post.setId(id);
        postRepository.insert(post);
        return post;
    }

    public void createAndUpdateView(String content, List<String> labels) {
        postView.updateView(create(content, labels));
    }

    @Override
    public void getAll() {
        List<Post> posts = postRepository.getAll();
        postView.updateView(posts);
    }

    @Override
    public void getByIdAndUpdateView(String id) {
        Post post = getById(id);
        if (Objects.isNull(post)) {
            return;
        }
        postView.updateView(post);
    }

    @Override
    public Post getById(String id) {
        try {
            return postRepository.getById(Integer.valueOf(id));
        } catch (RuntimeException e) {
            postView.updateView("Exception: " + e);
            return null;
        }
    }

    @Override
    public void update(String id, String content, String status) {
        Post post = getById(id);
        if (Objects.isNull(post)) {
            postView.updateView("There is no post with id: " + id);
            return;
        }

        PostStatus newStatus = PostStatus.fromValue(status);
        if (newStatus == null) {
            postView.updateView("Status in null");
            return;
        }

        String update = OffsetDateTime.now().toString();

        Post updatedPost = new Post(Integer.valueOf(id),
                content,
                post.getCreated(),
                update,
                post.getLabels(),
                newStatus);

        postRepository.update(post, updatedPost);
        postView.updateView(updatedPost);
    }

    @Override
    public void delete(String id) {
        Post post = getById(id);
        if (Objects.isNull(post)) {
            postView.updateView("There is no post with id: " + id);
            return;
        }
        postRepository.deleteById(post.getId());
        post.setStatus(PostStatus.DELETED);
        postRepository.insert(post);
        postView.updateView("Post was deleted");
    }
}
