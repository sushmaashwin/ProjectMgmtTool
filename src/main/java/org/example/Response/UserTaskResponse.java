package org.example.Response;


public class UserTaskResponse {

    private Long id;
    private String title;
    private String description;
    private String taskType;
    private String status;
    private String priority;
    private String assignmentStatus;

    public UserTaskResponse(
            Long id,
            String title,
            String description,
            String taskType,
            String status,
            String priority,
            String assignmentStatus) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.taskType = taskType;
        this.status = status;
        this.priority = priority;
        this.assignmentStatus = assignmentStatus;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getStatus() {
        return status;
    }

    public String getPriority() {
        return priority;
    }

    public String getAssignmentStatus() {
        return assignmentStatus;
    }
}
