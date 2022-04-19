package ru.pavelyurtaev.jobbot.service.job.arbeitnow;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.pavelyurtaev.jobbot.model.Job;
import ru.pavelyurtaev.jobbot.service.job.JobApi;
import ru.pavelyurtaev.jobbot.service.job.arbeitnow.model.ArbeitnowResponse;
import ru.pavelyurtaev.jobbot.service.job.mapper.JobMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArbeitnowJobApi implements JobApi {

    private static final String apiUrl = "https://arbeitnow.com/api/job-board-api";

    private final RestTemplate restTemplate;
    private final JobMapper jobMapper;

    @SneakyThrows
    @Override
    public List<Job> getJobs(final String queryString) {
        ArbeitnowResponse response = null;
        try {
            response = restTemplate.getForObject(apiUrl, ArbeitnowResponse.class);
        } catch (RestClientException e) {
            log.error("Exception on Arbeitnow api call", e);
        }

        if (response == null) {
            log.info("Arbeitnow returned null response");
            return List.of();
        }
        return response.data().stream()
                .filter(item -> item.slug().contains(queryString))
                .map(jobMapper::mapArbeitnowJob).toList();
    }
}
