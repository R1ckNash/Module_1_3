package com.ricknash.view;

import com.ricknash.model.Label;
import java.util.List;

public class LabelView {

    public void updateView(List<Label> labels) {
        labels.forEach(this::updateView);
    }

    public void updateView(String s) {
        System.out.println(s);
    }

    public void updateView(Label label) {
        System.out.println("----------------");
        System.out.println("Id: " + label.getId());
        System.out.println("Name: " + label.getName());
        System.out.println("Status: " + label.getStatus());
        System.out.println("----------------");
        System.out.println();
    }
}
