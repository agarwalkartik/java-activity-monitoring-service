package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@PostMapping(value = "/activity/{key}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public Activity recordActivity(@PathVariable("key") String key, @RequestBody Activity activity) {
		activity.setKey(key);
		activityService.recordActivity(activity);
		return activity;
	}

	@GetMapping("/activity/{key}/total")
	public ActivityTotal getActivityTotal(@PathVariable("key") String key) {

		ActivityTotal activityTotal = activityService.getActivityTotal(key);
		return activityTotal;
	}
}