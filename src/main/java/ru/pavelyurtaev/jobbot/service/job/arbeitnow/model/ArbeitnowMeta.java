package ru.pavelyurtaev.jobbot.service.job.arbeitnow.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ArbeitnowMeta(
        @JsonProperty(value = "current_page")
        Integer currentPage,
        Integer from,
        String path,
        @JsonProperty(value = "per_page")
        Integer perPage,
        Integer to,
        String terms,
        String info
) {
}
