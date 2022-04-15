package ru.pavelyurtaev.jobbot.service.job.mapper;

import org.springframework.stereotype.Service;
import ru.pavelyurtaev.jobbot.model.Job;
import ru.pavelyurtaev.jobbot.service.job.arbeitnow.model.ArbeitnowJob;

@Service
public class JobMapperImpl implements JobMapper {
    @Override
    public Job mapArbeitnowJob(final ArbeitnowJob job) {
        return Job.builder()
                .title(job.title())
                .description(job.description())
                .companyName(job.companyName())
                .remote(job.remote())
                .location(job.location())
                .salary(null)
                .build();
    }
}
