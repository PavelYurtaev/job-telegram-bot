package ru.pavelyurtaev.jobbot.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pavelyurtaev.jobbot.web.dto.JobBotResponse;

@RestController
@Slf4j
public class JobBotController {
    /**
     * Prevent Heroku free dyno from sleeping
     */
    @GetMapping
    public JobBotResponse herokuPing() {
        return new JobBotResponse("OK");
    }
}
