package com.ricknash.view;

import com.ricknash.controller.LabelController;
import com.ricknash.controller.PostController;
import com.ricknash.controller.WriterController;
import com.ricknash.repository.gson.GsonLabelRepositoryImpl;
import com.ricknash.repository.gson.GsonPostRepositoryImpl;
import com.ricknash.repository.gson.GsonWriterRepositoryImpl;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static com.ricknash.util.Constants.*;

public class MainView {

    private final Scanner scanner = new Scanner(System.in);
    private final LabelController lc = new LabelController(new GsonLabelRepositoryImpl(LABELS), new LabelView());
    private final PostController pc = new PostController(new GsonPostRepositoryImpl(POSTS), new PostView(), lc);
    private final WriterController wc = new WriterController(new GsonWriterRepositoryImpl(WRITERS), new WriterView(), pc);
    private boolean exit = false;

    public void run() {
        while (!exit) {
            System.out.println("Please choose number");
            System.out.println("1. Writer\n2. Post\n3. Label\n0. quit");
            String s = scanner.nextLine();
            switch (s) {
                case "1":
                    chooseActionWriter();
                case "2":
                    chooseActionPost();
                case "3":
                    chooseActionLabel();
                case "0": exit = true;
            }
        }
    }

    // LABEL

    private void chooseActionLabel() {
        while (!exit) {
            System.out.println("1. GET\n2. POST\n3. UPDATE\n4. DELETE\n0. BACK");
            switch (scanner.nextLine()) {
                case "1":
                    whichGetLabel();
                    run();
                    break;
                case "2":
                    createLabel();
                    run();
                    break;
                case "3":
                    updateLabel();
                    run();
                    break;
                case "4":
                    deleteLabel();
                    run();
                    break;
                case"0":
                    run();
                    break;
                default:
                    System.out.println("Sorry, try again");
            }
        }
    }

    private void createLabel() {
        System.out.println("Enter name");
        String name = scanner.nextLine();
        lc.createAndUpdateView(name);
        System.out.println();
    }

    private void whichGetLabel() {
        System.out.println("1. GET ALL\n2. GET BY ID");
        String id = scanner.nextLine();
        if (Objects.equals(id, "1")) {
            getLabels();
        } else {
            getLabelById();
        }
    }

    private void getLabels() {
        lc.getAll();
        System.out.println();
    }

    private void getLabelById() {
        System.out.println("Enter id");
        String id = scanner.nextLine();
        lc.getByIdAndUpdateView(id);
        System.out.println();
    }

    private void updateLabel() {
        System.out.println("Enter id");
        String id = scanner.nextLine();
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter status");
        String status = scanner.nextLine();
        lc.update(id, name, status);
        System.out.println();
    }

    private void deleteLabel() {
        System.out.println("Enter id");
        String id = scanner.nextLine();
        lc.delete(id);
        System.out.println();
    }

    // POST

    private void chooseActionPost() {
        while (!exit) {
            System.out.println("1. GET\n2. POST\n3. UPDATE\n4. DELETE\n0. BACK");
            switch (scanner.nextLine()) {
                case "1":
                    whichGetPost();
                    run();
                    break;
                case "2":
                    createPost();
                    run();
                    break;
                case "3":
                    updatePost();
                    run();
                    break;
                case "4":
                    deletePost();
                    run();
                    break;
                case"0":
                    run();
                    break;
                default:
                    System.out.println("Sorry, try again");
            }
        }
    }

    private void createPost() {
        System.out.println("Enter content");
        String content = scanner.nextLine();
        System.out.println("Enter labels using \",\"");
        List<String> labels = List.of(scanner.nextLine().split(",", 0));
        pc.createAndUpdateView(content, labels);
        System.out.println();
    }

    private void whichGetPost() {
        System.out.println("1. GET ALL\n2. GET BY ID");
        String id = scanner.nextLine();
        if (Objects.equals(id, "1")) {
            getPosts();
        } else {
            getPostById();
        }
    }

    private void getPosts() {
        pc.getAll();
        System.out.println();
    }

    private void getPostById() {
        System.out.println("Enter id");
        String id = scanner.nextLine();
        pc.getByIdAndUpdateView(id);
        System.out.println();
    }

    private void updatePost() {
        System.out.println("Enter id");
        String id = scanner.nextLine();
        System.out.println("Enter content");
        String content = scanner.nextLine();
        System.out.println("Enter status");
        String status = scanner.nextLine();
        pc.update(id, content, status);
        System.out.println();
    }

    private void deletePost() {
        System.out.println("Enter id");
        String id = scanner.nextLine();
        pc.delete(id);
        System.out.println();
    }

    // WRITER

    private void chooseActionWriter() {
        while (!exit) {
            System.out.println("1. GET\n2. POST\n3. UPDATE\n4. DELETE\n0. BACK");
            switch (scanner.nextLine()) {
                case "1":
                    whichGetWriter();
                    run();
                    break;
                case "2":
                    createWriter();
                    run();
                    break;
                case "3":
                    updateWriter();
                    run();
                    break;
                case "4":
                    deleteWriter();
                    run();
                    break;
                case"0":
                    run();
                    break;
                default:
                    System.out.println("Sorry, try again");
            }
        }
    }

    private void createWriter() {
        System.out.println("Enter first name");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name");
        String lastName = scanner.nextLine();
        System.out.println("Enter posts using \",\"");
        List<String> posts = List.of(scanner.nextLine().split(",", 0));
        System.out.println("Enter labels using \",\"");
        List<String> labels = List.of(scanner.nextLine().split(",", 0));
        wc.create(firstName, lastName, posts, labels);
        System.out.println();
    }

    private void whichGetWriter() {
        System.out.println("1. GET ALL\n2. GET BY ID");
        String id = scanner.nextLine();
        if (Objects.equals(id, "1")) {
            getWriters();
        } else {
            getWriterById();
        }
    }

    private void getWriters() {
        wc.getAll();
        System.out.println();
    }

    private void getWriterById() {
        System.out.println("Enter id");
        String id = scanner.nextLine();
        wc.getByIdAndUpdateView(id);
        System.out.println();
    }

    private void updateWriter() {
        System.out.println("Enter id");
        String id = scanner.nextLine();
        System.out.println("Enter first name");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name");
        String lastName = scanner.nextLine();
        System.out.println("Enter status");
        String status = scanner.nextLine();
        wc.update(id, firstName, lastName, status);
        System.out.println();
    }

    private void deleteWriter() {
        System.out.println("Enter id");
        String id = scanner.nextLine();
        wc.delete(id);
        System.out.println();
    }
}
