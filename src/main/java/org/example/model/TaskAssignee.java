package org.example.model;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task_assignees")
public class TaskAssignee {

    @EmbeddedId
    private TaskAssigneeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("taskId")
    @JoinColumn(name = "task_id")
    private Issue task;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;

    public TaskAssignee() {
    }

    public TaskAssigneeId getId() {
        return id;
    }

    public Issue getTask() {
        return task;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setId(TaskAssigneeId id) {
        this.id = id;
    }

    public void setTask(Issue task) {
        this.task = task;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }
}