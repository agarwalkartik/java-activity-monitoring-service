package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class ActivityPruneTask {

    @Autowired
    private ActivityService activityService;

    // prune activities every 5 seconds
    @Scheduled(fixedRate = 5000)
    public void pruneActivities() {
        activityService.prune();
    }

}