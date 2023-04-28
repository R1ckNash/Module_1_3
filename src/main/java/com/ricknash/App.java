package com.ricknash;

import com.ricknash.controller.Controller;
import com.ricknash.controller.LabelController;
import com.ricknash.controller.PostController;
import com.ricknash.controller.WriterController;
import com.ricknash.model.Label;
import com.ricknash.model.Post;
import com.ricknash.repository.implementations.GsonLabelRepositoryImpl;
import com.ricknash.repository.implementations.GsonPostRepositoryImpl;
import com.ricknash.view.LabelView;
import com.ricknash.view.PostView;

import java.util.Objects;
import java.util.Scanner;

import static com.ricknash.util.Constants.LABELS;
import static com.ricknash.util.Constants.POSTS;

/**
 * Hello world!
 */
public class App {

    static Scanner scanner = new Scanner(System.in);
    static boolean exit = false;
    static WriterController wc = new WriterController();
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
                whichGet();
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

    public static void whichGet() {
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
        String name = scanner.nextLine();
        lc.getByIdAndPrint(name);
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
    }

}

