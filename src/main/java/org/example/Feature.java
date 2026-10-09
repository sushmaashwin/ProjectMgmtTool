package org.example;

import java.time.LocalDate;

public class Feature extends Task {
    public Feature(String id, String title, LocalDate dueDate) {
        super(id, title, TaskType.FEATURE, dueDate);
    }

    @Override
    public void print() {
        System.out.println("Feature: " + title + " | Status: " + status);
    }
}
