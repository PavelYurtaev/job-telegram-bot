package ru.pavelyurtaev.jobbot.service.job.arbeitnow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

public record ArbeitnowJob(
        String slug,
        @JsonProperty(value = "company_name")
        String companyName,
        String title,
        String description,
        Boolean remote,
        String url,
        List<String> tags,
        List<String> job_types,
        String location,
        @JsonProperty(value = "created_at")
        Instant createdAt
) {
}
