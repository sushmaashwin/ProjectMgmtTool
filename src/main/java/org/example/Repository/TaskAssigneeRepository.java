package org.example.Repository;

import org.example.model.TaskAssignee;
import org.example.model.TaskAssigneeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskAssigneeRepository
        extends JpaRepository<TaskAssignee, TaskAssigneeId> {

    List<TaskAssignee> findByTaskId(Long taskId);

    List<TaskAssignee> findByUserId(Long userId);

    //List<TaskAssignee> findByIdTaskId(Long taskId);
    Optional<TaskAssignee> findByIdTaskId(Long taskId);
    List<TaskAssignee> findByIdUserId(Long userId);
    boolean existsByIdTaskIdAndIdUserId(Long taskId, Long userId);
}
