package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Story extends Task {
    private List<Task> subTasks = new ArrayList<>();
    public Story(String taskId, String title, LocalDate dueDate) {
        super(taskId, title, TaskType.STORY, dueDate);
    }

    public void addSubTask(Task subTask) {
        subTasks.add(subTask);
    }
    @Override
    public void print() {
        System.out.println("Story: " + title + " | Status: " + status);
        for (Task sub : subTasks) {
            System.out.print("   -> ");
            sub.print();
        }
    }
}
