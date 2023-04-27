package com.ricknash.controller;

import com.ricknash.model.Label;
import com.ricknash.model.Post;
import com.ricknash.model.Writer;
import com.ricknash.repository.implementations.GsonWriterRepositoryImpl;
import com.ricknash.repository.interfaces.WriterRepository;

import java.time.OffsetDateTime;
import java.util.Collections;

import static com.ricknash.util.Constants.WRITERS;

public class WriterController {

    WriterRepository writerRepository = new GsonWriterRepositoryImpl(WRITERS);

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

        System.out.println(writerRepository.getAll());
        System.out.println();
    }

}
