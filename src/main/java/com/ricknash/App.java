package com.ricknash;

import com.ricknash.controller.Controller;
import com.ricknash.controller.LabelController;
import com.ricknash.controller.PostController;
import com.ricknash.controller.WriterController;
import com.ricknash.model.Label;
import com.ricknash.model.Post;
import com.ricknash.model.Writer;
import com.ricknash.repository.implementations.GsonLabelRepositoryImpl;
import com.ricknash.repository.implementations.GsonPostRepositoryImpl;
import com.ricknash.repository.implementations.GsonWriterRepositoryImpl;
import com.ricknash.view.LabelView;
import com.ricknash.view.PostView;
import com.ricknash.view.WriterView;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static com.ricknash.util.Constants.*;

public class App {

    static Scanner scanner = new Scanner(System.in);
    static boolean exit = false;
    static Controller<Writer> wc = new WriterController(new GsonWriterRepositoryImpl(WRITERS), new WriterView());
    static Controller<Post> pc = new PostController(new GsonPostRepositoryImpl(POSTS), new PostView());
    static Controller<Label> lc = new LabelController(new GsonLabelRepositoryImpl(LABELS), new LabelView());


    public static void main(String[] args) {
        System.out.println("Hello World!");
        welcome();
    }

    public static void welcome() {
        while (!exit) {
            System.out.println("Please choose number");
            System.out.println("1. Writer\n2. Post\n3. Label\n0. quit");
            String s = scanner.nextLine();
            switch (s) {
                case "1":
                case "2":
                    chooseActionPost();
                case "3":
                    chooseActionLabel();
                case "0": exit = true;
            }
        }
    }

    public static void chooseActionLabel() {
        System.out.println("1. GET\n2. POST\n3. UPDATE\n4. DELETE");
        switch (scanner.nextLine()) {
            case "1":
                whichGetLabel();
                welcome();
                break;
            case "2":
                createLabel();
                welcome();
                break;
            case "3":
                updateLabel();
                welcome();
                break;
            case "4":
                deleteLabel();
                welcome();
                break;
        }
    }

    public static void createLabel() {
        System.out.println("Enter name");
        String name = scanner.nextLine();
        lc.create(name);
        System.out.println();
    }

    public static void whichGetLabel() {
        System.out.println("1. GET ALL\n2. GET BY ID");
        String id = scanner.nextLine();
        if (Objects.equals(id, "1")) {
            getLabels();
        } else {
            getLabelById();
        }
    }

    public static void getLabels() {
        lc.getAll();
        System.out.println();
    }

    public static void getLabelById() {
        System.out.println("Enter id");
        String id = scanner.nextLine();
        lc.getByIdAndPrint(id);
        System.out.println();
    }

    public static void updateLabel() {
        System.out.println("Enter id");
        String id = scanner.nextLine();
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter status");
        String status = scanner.nextLine();
        lc.update(id, name, status);
        System.out.println();
    }

    public static void deleteLabel() {
        System.out.println("Enter id");
        String id = scanner.nextLine();
        lc.delete(id);
        System.out.println();
    }



    public static void chooseActionPost() {
        System.out.println("1. GET\n2. POST\n3. UPDATE\n4. DELETE");
        switch (scanner.nextLine()) {
            case "1":
                whichGetPost();
                welcome();
                break;
            case "2":
                createPost();
                welcome();
                break;
            case "3":
                updatePost();
                welcome();
                break;
            case "4":
                deletePost();
                welcome();
                break;
        }
    }

    public static void createPost() {
        System.out.println("Enter content");
        String content = scanner.nextLine();
        System.out.println("Enter labels using \",\"");
        List<String> labels = List.of(scanner.nextLine().split(",", 0));
        pc.create(content, labels);
        System.out.println();
    }

    public static void whichGetPost() {
        System.out.println("1. GET ALL\n2. GET BY ID");
        String id = scanner.nextLine();
        if (Objects.equals(id, "1")) {
            getPosts();
        } else {
            getPostById();
        }
    }

    public static void getPosts() {
        pc.getAll();
        System.out.println();
    }

    public static void getPostById() {
        System.out.println("Enter id");
        String id = scanner.nextLine();
        pc.getByIdAndPrint(id);
        System.out.println();
    }

    public static void updatePost() {
        System.out.println("Enter id");
        String id = scanner.nextLine();
        System.out.println("Enter content");
        String content = scanner.nextLine();
        System.out.println("Enter status");
        String status = scanner.nextLine();
        pc.update(id, content, status);
        System.out.println();
    }

    public static void deletePost() {
        System.out.println("Enter id");
        String id = scanner.nextLine();
        pc.delete(id);
        System.out.println();
    }
}

