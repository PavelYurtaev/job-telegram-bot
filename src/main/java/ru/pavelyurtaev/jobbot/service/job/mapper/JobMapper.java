package ru.pavelyurtaev.jobbot.service.job.mapper;

import ru.pavelyurtaev.jobbot.model.Job;
import ru.pavelyurtaev.jobbot.service.job.arbeitnow.model.ArbeitnowJob;

public interface JobMapper {
    Job mapArbeitnowJob(ArbeitnowJob job);
}
