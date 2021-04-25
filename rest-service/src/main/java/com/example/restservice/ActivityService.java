package com.example.restservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.ListIterator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("singleton")
@Component("activityService")
public class ActivityService {

	private static int EXPIRY_TIME_MS = 12 * 60 * 60 * 1000;
	private ArrayList<Activity> activities = new ArrayList<Activity>();
	// keep a list of all past activities so that only the activities which get
	// older are pruned

	private HashMap<String, ActivityTotal> activitySumMap = new HashMap<String, ActivityTotal>();
	// keep sum in a seperate hashmap to optimise for performance

	private void updateActivitySum(String key, int value) {
		ActivityTotal activityTotal = activitySumMap.getOrDefault(key, new ActivityTotal(0));
		activityTotal.setValue(activityTotal.getValue() + value);
		activitySumMap.put(key, activityTotal);
	}

	private boolean isActivityExpired(Activity activity) {
		Date date = new Date();
		long timestamp = date.getTime();
		if (activity.getTimestamp() < timestamp - EXPIRY_TIME_MS) {
			return true;
		}
		return false;
	}

	// prune function is called by task scheduled in ActivityPruneTask every 5 s
	public void prune() {

		ListIterator<Activity> iter = activities.listIterator();

		while (iter.hasNext()) {
			Activity current = iter.next();
			if (isActivityExpired(current)) {
				updateActivitySum(current.getKey(), -1 * current.getValue()); // reduce the total activity count
				iter.remove();
			} else {
				break;
				// if an activity is not expired then all the activities after it wont be
				// expired as well, as they are inserted in increasing order of timestamps
			}
		}
	}

	public void recordActivity(Activity activity) {
		activities.add(activity);
		updateActivitySum(activity.getKey(), activity.getValue());
		return;
	}

	public ActivityTotal getActivityTotal(String key) {
		return activitySumMap.getOrDefault(key, new ActivityTotal(0));
	}

}