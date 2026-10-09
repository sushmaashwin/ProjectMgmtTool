package org.example.model;


import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TaskAssigneeId implements Serializable {

    private Long taskId;
    private Long userId;

    public TaskAssigneeId() {
    }

    public TaskAssigneeId(Long taskId, Long userId) {
        this.taskId = taskId;
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
