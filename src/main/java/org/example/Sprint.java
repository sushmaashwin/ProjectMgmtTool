package org.example;

import java.util.HashSet;
import java.util.Set;

public class Sprint {
    private String sprintId;
    private String sprintName;
    private Set<Task> tasks = new HashSet<>();
    public Sprint(String sprintId, String sprintName) {
        this.sprintId = sprintId;
        this.sprintName = sprintName;
    }
    public void addTask(Task task) {
        tasks.add(task);
    }
    public void removeTask(Task task) {
        tasks.remove(task);
    }
    public void printSprintDetails() {
        System.out.println("Sprint ID: " + sprintId);
        System.out.println("Sprint Name: " + sprintName);
        System.out.println("Tasks in this sprint:");
        for (Task task : tasks) {
            task.print();
        }
    }
    public Set<Task> getTasks() {
        return tasks;
    }

}
