package com.piltz.todolistapp;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@Valid @RequestBody CreateTaskRequest request) {
        return service.addTask(request.title(), request.description(), request.dueDate());
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable UUID id) {
        return service.getTaskById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @PatchMapping("/{id}/status")
    public void updateStatus(@PathVariable UUID id,
                             @RequestParam Status status) {
        boolean updated = service.updateStatus(id, status);
        if (!updated) {
            throw new TaskNotFoundException(id);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable UUID id) {
        boolean deleted = service.deleteTask(id);
        if (!deleted) {
            throw new TaskNotFoundException(id);
        }
    }
}

