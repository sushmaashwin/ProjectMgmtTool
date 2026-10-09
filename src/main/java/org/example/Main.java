package org.example;

import org.example.Repository.IssueRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(
                Main.class,
                args);
//        User alice = new User("U1", "Alice");
//        User bob = new User("U2", "Bob");
//        IssueRepository repo = new IssueRepository();
//        JiraService service = new JiraService(repo);
//        Story story = new Story("S1", "Payment Integration", LocalDate.now().minusDays(1));
//        SubTask sub1 = new SubTask("ST1", "Design API", LocalDate.now().minusDays(2));
//        SubTask sub2 = new SubTask("ST2", "Implement API", LocalDate.now().plusDays(2));
//        sub1.assignTo(alice);
//        sub2.assignTo(bob);
//        story.addSubTask(sub1);
//        story.addSubTask(sub2);
//        Bug bug = new Bug("B1", "Payment Failure", LocalDate.now().minusDays(3));
//        bug.assignTo(bob);
//        Feature feature = new Feature("F1", "Dashboard", LocalDate.now().plusDays(3));;
//        feature.assignTo(bob);
//        repo.addTask(story);
//        repo.addTask(bug);
//        repo.addTask(feature);
//        repo.addTask(sub1);
//        repo.addTask(sub2);
//        Sprint sprint = new Sprint("SP1", "Sprint 1");
//        sprint.addTask(story);
//        sprint.addTask(bug);
//        sprint.printSprintDetails();
//        service.printDelayedTasks();
//        service.printTasksAssignedTo(alice);
    }
}