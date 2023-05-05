package com.ricknash.controller;

import com.ricknash.model.Label;
import com.ricknash.model.Post;
import com.ricknash.model.PostStatus;
import com.ricknash.repository.gson.AbstractRepository;
import com.ricknash.view.PostView;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class PostController {

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

        Post post = Post.builder()
                .content(content)
                .created(created)
                .updated(created)
                .labels(newLabels)
                .status(PostStatus.ACTIVE)
                .build();

        postRepository.insert(post);
        return post;
    }

    public void createAndUpdateView(String content, List<String> labels) {
        postView.updateView(create(content, labels));
    }

    public void getAll() {
        List<Post> posts = postRepository.getAll();
        postView.updateView(posts);
    }

    public void getByIdAndUpdateView(String id) {
        Post post = getById(id);
        if (Objects.isNull(post)) {
            return;
        }
        postView.updateView(post);
    }

    public Post getById(String id) {
        try {
            return postRepository.getById(Integer.valueOf(id));
        } catch (RuntimeException e) {
            postView.updateView("Exception: " + e);
            return null;
        }
    }

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

        postRepository.update(updatedPost);
        postView.updateView(updatedPost);
    }

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
