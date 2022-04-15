package ru.pavelyurtaev.jobbot.service.job.arbeitnow.model;

import java.util.List;

public record ArbeitnowResponse(
        List<ArbeitnowJob> data,
        ArbeitnowLinks links,
        ArbeitnowMeta meta
) {
}
