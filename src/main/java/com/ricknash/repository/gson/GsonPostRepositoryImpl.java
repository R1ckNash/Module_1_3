package com.ricknash.repository.gson;

import com.google.gson.reflect.TypeToken;
import com.ricknash.model.Post;
import com.ricknash.repository.PostRepository;

import java.util.List;

public class GsonPostRepositoryImpl extends AbstractRepository<Post> implements PostRepository {

    public GsonPostRepositoryImpl(String filePath) {
        super(filePath, new TypeToken<List<Post>>(){});
    }
}
