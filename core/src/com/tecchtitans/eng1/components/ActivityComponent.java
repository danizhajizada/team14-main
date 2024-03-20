package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;
import com.tecchtitans.eng1.ActivityType;

/**
 * A component that stores relevant information about activities.
 */
public class ActivityComponent implements Component {
    /**
     * Stores the amount of time that is passed when the activity is performed.
     */
    public int timeChange = 0;

    /**
     * Stores the amount of energy that is used when the activity is performed.
     */
    public int energyChange = 0;

    /**
     * Stores the amount the activity will change the study score.
     */
    public int studyChange = 0;

    /**
     * Stores the type of activity that will be performed.
     */
    public ActivityType type = ActivityType.NONE;
}
