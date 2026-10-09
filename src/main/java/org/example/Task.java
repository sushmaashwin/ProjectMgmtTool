package org.example;

import java.time.LocalDate;
import java.util.List;

public abstract class Task {
    protected String id;
    protected String title;
    protected TaskType type;
    protected Status status;
    protected User user;
    protected LocalDate dueDate;
    protected List<String> comments;
    public Task(String id, String title, TaskType type, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.status = Status.TODO;
        this.dueDate = dueDate;
    }
    public void assignTo(User user) {
        this.user = user;
    }
    public void changeStatus(Status status) {
        this.status = status;
    }
    public void addComment(String comment) {
        this.comments.add(comment);
    }
    public boolean isDelayed() {
        return LocalDate.now().isAfter(dueDate) && status != Status.DONE;
    }
    public User getUser() {
        return user;
    }
    public String getId() {
        return id;
    }
    public abstract void print();
}
