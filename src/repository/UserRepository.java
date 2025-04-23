package repository;

import Model.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Path storageFile = Paths.get("users.json");
    private final Map<String, User> users = new HashMap<>();
    private User currentUser;
    private final Gson gson = new Gson();
    private final Type mapType = new TypeToken<Map<String, User>>(){}.getType();

    public UserRepository() throws IOException {
        loadAll();
    }

    private void loadAll() throws IOException {
        if (Files.exists(storageFile)) {
            try (Reader reader = Files.newBufferedReader(storageFile)) {
                Map<String, User> loaded = gson.fromJson(reader, mapType);
                if (loaded != null) {
                    users.putAll(loaded);
                }
            }
        }
    }

    private void saveAll() throws IOException {
        try (Writer writer = Files.newBufferedWriter(storageFile)) {
            gson.toJson(users, writer);
        }
    }

    public void save(User user) throws IOException {
        if (users.containsKey(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        users.put(user.getUsername(), user);
        saveAll();
    }

    public User findByUsername(String username) {
        return users.get(username);
    }

    public void update(User user) throws IOException {
        if (!users.containsKey(user.getUsername())) {
            throw new IllegalArgumentException("User not found");
        }
        users.put(user.getUsername(), user);
        saveAll();
    }

    public void setCurrent(User user) {
        this.currentUser = user;
    }

    public User getCurrent() {
        return currentUser;
    }
}