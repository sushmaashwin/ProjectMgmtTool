package org.example.Controller;

import org.example.Repository.TaskAssigneeRepository;
import org.example.Response.AssigneeResponse;
import org.example.Response.UserTaskOverviewResponse;
import org.example.Response.UserTaskResponse;
import org.example.Service.UserService;
import org.example.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final TaskAssigneeRepository taskAssigneeRepository;

    public UserController(UserService userService, TaskAssigneeRepository taskAssigneeRepository) {
        this.userService = userService;
        this.taskAssigneeRepository = taskAssigneeRepository;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<UserTaskResponse>> getUserTasks(@PathVariable Long id) {
        return ResponseEntity.ok(
                userService.getUserTasks(id)
        );
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return "User deleted successfully";
    }

    @GetMapping("/task1/{taskId}")
    public List<AssigneeResponse> getUsersAssignedToTask1(
            @PathVariable Long taskId) {

        return taskAssigneeRepository.findByIdTaskId(taskId)
                .stream()
                .map(a -> new AssigneeResponse(
                        a.getUser().getId(),
                        a.getUser().getName(),
                        a.getUser().getEmail()
                ))
                .toList();
    }

    @GetMapping("/task/{taskId}")
    public List<AssigneeResponse> getUsersAssignedToTask(
            @PathVariable Long taskId) {

        return userService.getUsersAssignedToTask(taskId);
    }
}