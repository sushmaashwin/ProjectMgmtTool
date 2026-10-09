package org.example.Repository;

import org.example.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findByStatus(String status);

    List<Issue> findByTaskType(String taskType);

    List<Issue> findByPriority(String priority);

    List<Issue> findBySprintId(Long sprintId);
}
