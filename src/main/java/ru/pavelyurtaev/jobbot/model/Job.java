package ru.pavelyurtaev.jobbot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Job {
    private String title;
    private String description;
    private String companyName;
    private boolean remote;
    private String location;
    private String salary;
}
