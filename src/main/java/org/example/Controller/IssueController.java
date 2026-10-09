package org.example.Controller;

import org.example.Response.CreateIssueRequest;
import org.example.Service.IssueService;
import org.example.dto.UpdateIssueRequest;
import org.example.dto.UpdateStatusRequest;
import org.example.model.Issue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public List<Issue> getAllIssues() {
        return issueService.getAllIssues();
    }

    @GetMapping("/{id}")
    public Issue getIssue(@PathVariable Long id) {
        return issueService.getIssueById(id);
    }

    @GetMapping("/status/{status}")
    public List<Issue> getByStatus(
            @PathVariable String status) {

        return issueService.getIssuesByStatus(status);
    }

    @GetMapping("/type/{type}")
    public List<Issue> getByType(
            @PathVariable String type) {

        return issueService.getIssuesByType(type);
    }

    @GetMapping("/sprint/{sprintId}")
    public List<Issue> getBySprint(
            @PathVariable Long sprintId) {

        return issueService.getIssuesBySprint(sprintId);
    }

    @DeleteMapping("/{id}")
    public String deleteIssue(@PathVariable Long id) {

        issueService.deleteIssue(id);

        return "Issue deleted successfully";
    }

    @PostMapping
    public ResponseEntity<Issue> createIssue(
            @RequestBody CreateIssueRequest request) {

        Issue issue = issueService.createIssue(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }

    @PutMapping("/{issueId}/status")
    public ResponseEntity<Issue> updateStatus(
            @PathVariable Long issueId,
            @RequestBody UpdateStatusRequest request) {

        Issue updatedIssue =
                issueService.updateStatus(issueId, request.getStatus());

        return ResponseEntity.ok(updatedIssue);
    }

    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<String> assignUser(
            @PathVariable Long issueId,
            @PathVariable Long userId) {

        issueService.assignUser(issueId, userId);

        return ResponseEntity.ok(
                "User " + userId +
                        " assigned to issue " + issueId
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Issue> updateIssue(
            @PathVariable Long id,
            @RequestBody UpdateIssueRequest request) {

        Issue updatedIssue = issueService.updateIssue(id, request);

        return ResponseEntity.ok(updatedIssue);
    }

}