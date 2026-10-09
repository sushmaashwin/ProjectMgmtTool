package org.example;

import java.time.LocalDate;

public class SubTask extends Task {
    public SubTask(String id, String title, LocalDate dueDate) {
        super(id, title, TaskType.SUBTASK, dueDate);
    }

    @Override
    public void print() {
        System.out.println("SubTask: " + title + " | Status: " + status);
    }
}
