package org.example;

import java.time.LocalDate;

public class Bug extends Task {
    public Bug(String id, String title, LocalDate dueDate) {
        super(id, title, TaskType.BUG, dueDate);
    }

    @Override
    public void print() {
        System.out.println("Bug: " + title + " | Status: " + status);
    }



}

