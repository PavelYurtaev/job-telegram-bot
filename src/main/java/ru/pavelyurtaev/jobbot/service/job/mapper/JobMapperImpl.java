package ru.pavelyurtaev.jobbot.service.job.mapper;

import org.springframework.stereotype.Service;
import ru.pavelyurtaev.jobbot.model.Job;
import ru.pavelyurtaev.jobbot.service.job.arbeitnow.model.ArbeitnowJob;

import java.time.LocalDateTime;
import java.time.ZoneId;

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
                .timestamp(LocalDateTime.ofInstant(job.createdAt(), ZoneId.systemDefault()))
                .url(job.url())
                .build();
    }
}
