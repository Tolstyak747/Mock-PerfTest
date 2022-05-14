package ru.malkovrest.service;

import org.springframework.stereotype.Service;
import ru.malkovrest.model.Issue;

import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class IssueServiceImpl implements IssueService {

    private static final Map<Integer, Issue> ISSUE_REPOSITORY_MAP = new HashMap<>();


    @Override
    public void create(Issue issue) {
        Random randomId = new Random();
        Random randomPriority = new Random();
        Random randomKey = new Random();

        String url = new String("http://localhost:8080/rest/api/2/issue/");
        int issueId = randomId.nextInt(1000);
        int priority = randomPriority.nextInt(4) + 1;
        int key = randomKey.nextInt(4999) + 1;
        Formatter formatter = new Formatter();
        issue.setKey(String.valueOf(formatter.format("TEST-%d",key)));
        issue.setPriority(priority);
        issue.setId(issueId);
        issue.setSelf(url + issueId);
        ISSUE_REPOSITORY_MAP.put(issueId, issue);
    }

    @Override
    public List<Issue> readAll() {
        return new ArrayList<>(ISSUE_REPOSITORY_MAP.values());
    }

    @Override
    public Issue read(int id) {
        return ISSUE_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Issue issue, int id) {
        if (ISSUE_REPOSITORY_MAP.containsKey(id)) {
            String key = ISSUE_REPOSITORY_MAP.get(id).getKey();
            String url = ISSUE_REPOSITORY_MAP.get(id).getSelf();
            issue.setSelf(url);
            issue.setId(id);
            issue.setKey(key);
            ISSUE_REPOSITORY_MAP.put(id, issue);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return ISSUE_REPOSITORY_MAP.remove(id) != null;
    }
}