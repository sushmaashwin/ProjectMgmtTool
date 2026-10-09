package org.example.Service;

import org.example.Repository.SprintRepository;
import org.example.model.Sprint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprintService {

    private final SprintRepository sprintRepository;

    public SprintService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }

    public Sprint getSprint(Long id) {

        return sprintRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Sprint not found: " + id));
    }

    public Sprint createSprint(Sprint sprint) {
        return sprintRepository.save(sprint);
    }
}