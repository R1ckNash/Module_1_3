package com.ricknash.view;

import com.ricknash.model.Post;
import java.util.List;

public class PostView {

    public void updateView(List<Post> posts) {
        posts.forEach(this::updateView);
    }

    public void updateView(String s) {
        System.out.println(s);
    }

    public void updateView(Post post) {
        System.out.println("----------------");
        System.out.println("Id: " + post.getId());
        System.out.println("Content: " + post.getContent());
        System.out.println("Created: " + post.getCreated());
        System.out.println("Updated: " + post.getUpdated());
        System.out.println("Status: " + post.getStatus());
        System.out.println("----------------");
        System.out.println();
    }
}
