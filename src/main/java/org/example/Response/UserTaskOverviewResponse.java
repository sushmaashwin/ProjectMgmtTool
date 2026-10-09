package org.example.Response;

import java.util.List;

public record UserTaskOverviewResponse(
        Long userId,
        String userName,
        List<TaskSummaryResponse> assignedTasks,
        List<TaskSummaryResponse> unassignedTasks
) {}
