package ru.pavelyurtaev.jobbot.service.heroku;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.pavelyurtaev.jobbot.util.StringUtils;
import ru.pavelyurtaev.jobbot.web.dto.JobBotResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class HerokuIdleService {

    private final RestTemplate restTemplate;

    @Value("${heroku.app}")
    private String herokuAppUrl;


    @Scheduled(cron = "*/25 * * * * *")
    public void selfPing() {
        if (StringUtils.isNotNullAndNotBlank(herokuAppUrl)) {
            try {
                restTemplate.getForEntity(herokuAppUrl, JobBotResponse.class);
                log.info("Ping OK");
            } catch (RestClientException e) {
                log.error("Failed to ping self", e);
            }
        }

    }
}
