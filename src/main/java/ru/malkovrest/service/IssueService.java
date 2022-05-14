package ru.malkovrest.service;

import ru.malkovrest.model.Issue;

import java.util.List;

public interface IssueService {

    void create(Issue issue);

    List<Issue> readAll();

    Issue read(int id);

    boolean update(Issue issue, int id);


    boolean delete(int id);
}