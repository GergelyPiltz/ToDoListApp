package com.piltz.todolistapp;

import java.time.LocalDate;
import java.util.*;

public class TaskService {
    private final Map<UUID, Task> tasks;
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
        this.tasks = repository.load();
    }

    public Task addTask(String title, String description, LocalDate dueDate) {
        Task task = new Task(title, description, dueDate);
        tasks.put(task.getId(), task);
        repository.save(tasks);
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
        repository.save(tasks);
        return true;
    }

    public boolean deleteTask(UUID id) {
        boolean removed = tasks.remove(id) != null;
        if (removed) {
            repository.save(tasks);
        }
        return removed;
    }

}
