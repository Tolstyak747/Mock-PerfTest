package ru.malkovrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.malkovrest.model.Issue;
import ru.malkovrest.service.IssueService;

import java.util.List;

@RestController
@RequestMapping("rest/api/2/issue")
public class IssueController {
    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping()
    public ResponseEntity<Issue> create(@RequestBody Issue issue) {
        issueService.create(issue);
        return new ResponseEntity<>(issue, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Issue>> read() {
        final List<Issue> issues = issueService.readAll();
        return issues != null && !issues.isEmpty()
                ? new ResponseEntity<>(issues, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Issue> read(@PathVariable(name = "id") int id) {
        final Issue issue = issueService.read(id);
        return issue != null
                ? new ResponseEntity<>(issue, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Issue issue) {
        final boolean updated = issueService.update(issue, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = issueService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
