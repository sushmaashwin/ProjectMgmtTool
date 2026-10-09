package org.example.dto;

public class UpdateIssueRequest {

    private String title;
    private String description;
    private String taskType;
    private String status;
    private String priority;
    private Integer storyPoints;
    private Long sprintId;
    private Long createdBy;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskType() { return taskType; }
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Integer getStoryPoints() { return storyPoints; }
    public void setStoryPoints(Integer storyPoints) {
        this.storyPoints = storyPoints;
    }

    public Long getSprintId() { return sprintId; }
    public void setSprintId(Long sprintId) {
        this.sprintId = sprintId;
    }

    public Long getCreatedBy() { return createdBy; }
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
}
