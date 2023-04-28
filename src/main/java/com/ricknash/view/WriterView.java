package com.ricknash.view;

import com.ricknash.model.Writer;

import java.util.List;

public class WriterView {

    public void updateView(List<Writer> writers) {
        writers.forEach(this::updateView);
    }

    public void updateView(String s) {
        System.out.println(s);
    }

    public void updateView(Writer writer) {
        System.out.println("----------------");
        System.out.println("Id: " + writer.getId());
        System.out.println("First name: " + writer.getFirstName());
        System.out.println("Last name: " + writer.getLastName());
        System.out.println("Posts: " + writer.getPosts());
        System.out.println("Status: " + writer.getStatus());
        System.out.println("----------------");
        System.out.println();
    }
}
