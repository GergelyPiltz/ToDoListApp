package com.piltz.todolistapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class TaskRepository {
    private static final String FILE_PATH = "tasks.json";
    private final ObjectMapper objectMapper;

    public TaskRepository() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public Map<UUID, Task> load() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return new HashMap<>();
            }
            return objectMapper.readValue(file, new TypeReference<Map<UUID, Task>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to load tasks from file", e);
        }
    }

    public void save(Map<UUID, Task> tasks) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(FILE_PATH), tasks);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save tasks to file", e);
        }
    }
}
