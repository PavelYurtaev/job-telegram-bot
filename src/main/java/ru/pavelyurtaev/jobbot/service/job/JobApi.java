package ru.pavelyurtaev.jobbot.service.job;

import ru.pavelyurtaev.jobbot.model.Job;

import java.util.List;

public interface JobApi {
    List<Job> getJobs(String queryString);
}
