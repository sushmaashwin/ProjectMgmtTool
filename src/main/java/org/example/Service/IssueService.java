package org.example.Service;

import org.example.Repository.IssueRepository;
import org.example.Repository.SprintRepository;
import org.example.Repository.TaskAssigneeRepository;
import org.example.Repository.UserRepository;
import org.example.Response.CreateIssueRequest;
import org.example.dto.UpdateIssueRequest;
import org.example.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    private final SprintRepository sprintRepository;
    private final TaskAssigneeRepository taskAssigneeRepository;

    public IssueService(
            IssueRepository issueRepository,
            UserRepository userRepository,
            SprintRepository sprintRepository,
            TaskAssigneeRepository taskAssigneeRepository, TaskAssigneeRepository taskAssigneeRepository1) {

        this.issueRepository = issueRepository;
        this.userRepository = userRepository;
        this.sprintRepository = sprintRepository;
        this.taskAssigneeRepository = taskAssigneeRepository1;
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Issue getIssueById(Long id) {

        return issueRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Issue not found: " + id));
    }

    public List<Issue> getIssuesByStatus(String status) {
        return issueRepository.findByStatus(status);
    }

    public List<Issue> getIssuesByType(String type) {
        return issueRepository.findByTaskType(type);
    }

    public List<Issue> getIssuesBySprint(Long sprintId) {
        return issueRepository.findBySprintId(sprintId);
    }

    public void deleteIssue(Long id) {

        if (!issueRepository.existsById(id)) {
            throw new RuntimeException("Issue not found: " + id);
        }

        issueRepository.deleteById(id);
    }

    public Issue createIssue(CreateIssueRequest request) {

        Sprint sprint = sprintRepository.findById(request.getSprintId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Sprint not found: " + request.getSprintId()));

        User createdBy = userRepository.findById(request.getCreatedBy())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found: " + request.getCreatedBy()));

        Issue issue = new Issue();

        issue.setTitle(request.getTitle());
        issue.setDescription(request.getDescription());
        issue.setTaskType(request.getTaskType());
        issue.setStatus(request.getStatus());
        issue.setPriority(request.getPriority());
        issue.setStoryPoints(request.getStoryPoints());

        issue.setSprint(sprint);
        issue.setCreatedBy(createdBy);

        return issueRepository.save(issue);
    }

    @Transactional
    public TaskAssignee assignUser(Long issueId, Long userId) {

        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() ->
                        new RuntimeException("Issue not found: " + issueId));

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found: " + userId));

        Optional<TaskAssignee> existing =
                taskAssigneeRepository.findByIdTaskId(issueId);

        // Already assigned to this user
        if (existing.isPresent()
                && existing.get().getUser().getId().equals(userId)) {
            return existing.get();
        }

        // Remove old user
        if (existing.isPresent()) {
            taskAssigneeRepository.delete(existing.get());
            taskAssigneeRepository.flush();   // IMPORTANT
        }

        // Assign new user
        TaskAssigneeId id =
                new TaskAssigneeId(issueId, userId);

        TaskAssignee assignment = new TaskAssignee();
        assignment.setId(id);
        assignment.setTask(issue);
        assignment.setUser(user);

        return taskAssigneeRepository.save(assignment);
    }

    public Issue updateStatus(Long issueId, String status) {

        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() ->
                        new RuntimeException("Issue not found: " + issueId));

        issue.setStatus(status);

        return issueRepository.save(issue);
    }

    @Transactional
    public Issue updateIssue(Long id, UpdateIssueRequest request) {

        Issue issue = issueRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Issue not found: " + id));

        if (request.getTitle() != null) {
            issue.setTitle(request.getTitle());
        }

        if (request.getDescription() != null) {
            issue.setDescription(request.getDescription());
        }

        if (request.getTaskType() != null) {
            issue.setTaskType(request.getTaskType());
        }

        if (request.getStatus() != null) {
            issue.setStatus(request.getStatus());
        }

        if (request.getPriority() != null) {
            issue.setPriority(request.getPriority());
        }

        if (request.getStoryPoints() != null) {
            if (request.getStoryPoints() < 0) {
                throw new IllegalArgumentException(
                        "Story points cannot be negative");
            }
            issue.setStoryPoints(request.getStoryPoints());
        }

        if (request.getSprintId() != null) {
            Sprint sprint = sprintRepository
                    .findById(request.getSprintId())
                    .orElseThrow(() -> new RuntimeException(
                            "Sprint not found: " + request.getSprintId()));

            issue.setSprint(sprint);
        }

        if (request.getCreatedBy() != null) {
            User user = userRepository
                    .findById(request.getCreatedBy())
                    .orElseThrow(() -> new RuntimeException(
                            "User not found: " + request.getCreatedBy()));

            issue.setCreatedBy(user);
        }

        return issueRepository.save(issue);
    }
}