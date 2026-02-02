package com.piltz.todolistapp;

import java.time.LocalDate;
import java.util.*;

public class TaskService {
    private final Map<UUID, Task> tasks = new HashMap<>();

    public Task addTask(String title, String description, LocalDate dueDate) {
        Task task = new Task(title, description, dueDate);
        tasks.put(task.getId(), task);
        return task;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public Optional<Task> getTaskById(UUID id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public boolean updateStatus(UUID id, Status status) {
        Task task = tasks.get(id);
        if (task == null) {
            return false;
        }
        task.setStatus(status);
        return true;
    }

    public boolean deleteTask(UUID id) {
        return tasks.remove(id) != null;
    }

}
