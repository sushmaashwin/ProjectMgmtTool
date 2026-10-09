package org.example.Controller;


import org.example.Service.SprintService;
import org.example.model.Sprint;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/sprints")
public class SprintController {

    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping
    public List<Sprint> getSprints() {
        return sprintService.getAllSprints();
    }

    @GetMapping("/{id}")
    public Sprint getSprint(@PathVariable Long id) {
        return sprintService.getSprint(id);
    }

    @PostMapping
    public Sprint createSprint(@RequestBody Sprint sprint) {
        return sprintService.createSprint(sprint);
    }
}