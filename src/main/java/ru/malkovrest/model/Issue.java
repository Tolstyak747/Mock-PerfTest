package ru.malkovrest.model;

import lombok.Data;

@Data
public class Issue {
    private Integer id;
    private String key;
    private String summary;
    private Integer priority;
    private String description;
    private String self;
}

