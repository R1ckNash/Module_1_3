package com.ricknash.controller;

import com.ricknash.model.Label;
import com.ricknash.model.Post;
import com.ricknash.model.Writer;
import com.ricknash.repository.implementations.AbstractRepository;
import com.ricknash.repository.implementations.GsonLabelRepositoryImpl;
import com.ricknash.repository.implementations.GsonPostRepositoryImpl;
import com.ricknash.repository.implementations.GsonWriterRepositoryImpl;
import com.ricknash.repository.interfaces.LabelRepository;
import com.ricknash.repository.interfaces.PostRepository;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static com.ricknash.util.Constants.*;

public class WriterController {

    AbstractRepository<Writer> writerRepository = new GsonWriterRepositoryImpl(WRITERS);
    AbstractRepository<Post> postRepository = new GsonPostRepositoryImpl(POSTS);
    AbstractRepository<Label> labelRepository = new GsonLabelRepositoryImpl(LABELS);

    public void saveWriter() {

        Label label1 = new Label(1, "label");
        Label label2 = new Label(2, "label");
        Label label3 = new Label(3, "label");
        Label label4 = new Label(4, "label");

        Post post1 = new Post(1, "bla bla", OffsetDateTime.now().toString(), OffsetDateTime.now().toString(),
                Collections.singletonList(label1));
        Post post2 = new Post(2, "bla bla", OffsetDateTime.now().toString(), OffsetDateTime.now().toString(),
                Collections.singletonList(label2));
        Post post3 = new Post(3, "bla bla", OffsetDateTime.now().toString(), OffsetDateTime.now().toString(),
                Collections.singletonList(label3));
        Post post4 = new Post(4, "bla bla", OffsetDateTime.now().toString(), OffsetDateTime.now().toString(),
                Collections.singletonList(label4));

        Writer writer1 = new Writer(1, "Ilia1", "LAlaLA", Collections.singletonList(post1));
        Writer writer2 = new Writer(2, "Ilia2", "LAlaLA", Collections.singletonList(post2));
        Writer writer3 = new Writer(3, "Ilia3", "LAlaLA", Collections.singletonList(post3));
        Writer writer4 = new Writer(4, "Ilia4", "LAlaLA", Collections.singletonList(post4));

        writerRepository.insert(writer1);
        writerRepository.insert(writer2);
        writerRepository.insert(writer3);
        writerRepository.insert(writer4);

        postRepository.insert(post1);
        postRepository.insert(post2);
        postRepository.insert(post3);
        postRepository.insert(post4);

        labelRepository.insert(label1);
        labelRepository.insert(label2);
        labelRepository.insert(label3);
        labelRepository.insert(label4);


        List<Writer> writers = writerRepository.getAll();
        System.out.println(writers.get(0).getId());
        System.out.println(writers.get(0).getFirstName());
        System.out.println(writers.get(0).getLastName());
        System.out.println(writers.get(0).getStatus());
        List<Post> posts = postRepository.getAll();
        System.out.println(posts);
        List<Label> labels = labelRepository.getAll();
        System.out.println(labels.toString());
        System.out.println();

        postRepository.deleteById(post1.getId());
    }

}
