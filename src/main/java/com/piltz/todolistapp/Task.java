package com.piltz.todolistapp;

import java.time.LocalDate;
import java.util.UUID;

public class Task {
    private final UUID id;
    private String title;
    private String description;
    private Status status;
    private LocalDate dueDate;

    public Task(String title, String description, LocalDate dueDate) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.status = Status.TODO;
        this.dueDate = dueDate;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s (ID: %s) - Due: %s",
                status, title, id, dueDate
        );
    }
}
