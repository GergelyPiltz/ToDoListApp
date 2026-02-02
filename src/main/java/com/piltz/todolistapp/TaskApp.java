package com.piltz.todolistapp;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class TaskApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskService service = new TaskService();

    public static void main(String[] args) {
        System.out.println("Welcome to Task Manager!");

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addTask();
                case "2" -> listTasks();
                case "3" -> updateTaskStatus();
                case "4" -> deleteTask();
                case "0" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add Task");
        System.out.println("2. List Tasks");
        System.out.println("3. Update Task Status");
        System.out.println("4. Delete Task");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    private static void addTask() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Due date (YYYY-MM-DD): ");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());

        Task task = service.addTask(title, description, dueDate);
        System.out.println("Task added: " + task);
    }

    private static void listTasks() {
        if (service.getAllTasks().isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        service.getAllTasks().forEach(System.out::println);
    }

    private static void updateTaskStatus() {
        try {
            System.out.print("Enter task ID: ");
            UUID id = UUID.fromString(scanner.nextLine());

            System.out.print("Enter new status (TODO, IN_PROGRESS, DONE): ");
            Status status = Status.valueOf(scanner.nextLine().toUpperCase());

            boolean updated = service.updateStatus(id, status);
            System.out.println(updated ? "Task updated." : "Task not found.");

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid ID or status format.");
        }
    }

    private static void deleteTask() {
        try {
            System.out.print("Enter task ID: ");
            UUID id = UUID.fromString(scanner.nextLine());

            boolean deleted = service.deleteTask(id);
            System.out.println(deleted ? "Task deleted." : "Task not found.");

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid ID format.");
        }
    }

}
