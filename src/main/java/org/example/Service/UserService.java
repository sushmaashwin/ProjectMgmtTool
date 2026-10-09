package org.example.Service;

import org.example.Repository.IssueRepository;
import org.example.Repository.TaskAssigneeRepository;
import org.example.Repository.UserRepository;
import org.example.Response.AssigneeResponse;
import org.example.Response.TaskSummaryResponse;
import org.example.Response.UserTaskOverviewResponse;
import org.example.Response.UserTaskResponse;
import org.example.model.Issue;
import org.example.model.TaskAssignee;
import org.example.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TaskAssigneeRepository taskAssigneeRepository;
    private final IssueRepository issueRepository;

    public UserService(UserRepository userRepository,
                       TaskAssigneeRepository taskAssigneeRepository,
                       IssueRepository issueRepository) {
        this.userRepository = userRepository;
        this.taskAssigneeRepository = taskAssigneeRepository;
        this.issueRepository = issueRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found: " + id));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<AssigneeResponse> getUsersAssignedToTask(Long taskId) {

        return taskAssigneeRepository.findByIdTaskId(taskId)
                .stream()
                .map(assignee -> new AssigneeResponse(
                        assignee.getUser().getId(),
                        assignee.getUser().getName(),
                        assignee.getUser().getEmail()
                ))
                .toList();
    }

    public UserTaskOverviewResponse getTasksForUser(Long userId) {
        User user = getUser(userId);

        List<TaskSummaryResponse> assignedTasks = taskAssigneeRepository.findByUserId(userId)
                .stream()
                .map(TaskAssignee::getTask)
                .filter(task -> task != null)
                .map(task -> new TaskSummaryResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getStatus(),
                        task.getPriority()
                ))
                .toList();

        Set<Long> assignedTaskIds = assignedTasks.stream()
                .map(TaskSummaryResponse::id)
                .collect(Collectors.toSet());

        List<TaskSummaryResponse> unassignedTasks = issueRepository.findAll()
                .stream()
                .filter(task -> !assignedTaskIds.contains(task.getId()))
                .map(task -> new TaskSummaryResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getStatus(),
                        task.getPriority()
                ))
                .toList();

        return new UserTaskOverviewResponse(
                user.getId(),
                user.getName(),
                assignedTasks,
                unassignedTasks
        );
    }

    public List<UserTaskResponse> getUserTasks(Long userId) {

        // Check that user exists
        userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found: " + userId));

        // Get all assignments for this user
        List<TaskAssignee> assignments =
                taskAssigneeRepository.findByIdUserId(userId);

        // Store task IDs assigned to this user
        Set<Long> assignedTaskIds = assignments.stream()
                .map(a -> a.getTask().getId())
                .collect(Collectors.toSet());

        // Get all tasks
        List<Issue> allTasks = issueRepository.findAll();

        return allTasks.stream()
                .map(task -> new UserTaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getTaskType(),
                        task.getStatus(),
                        task.getPriority(),
                        assignedTaskIds.contains(task.getId())
                                ? "ASSIGNED"
                                : "UNASSIGNED"
                ))
                .toList();
    }
}