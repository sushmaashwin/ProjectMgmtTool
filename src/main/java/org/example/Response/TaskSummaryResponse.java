package org.example.Response;

public record TaskSummaryResponse(
        Long id,
        String title,
        String status,
        String priority
) {}
