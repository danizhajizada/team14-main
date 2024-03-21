package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;
import com.tecchtitans.eng1.ActivityType;
import java.util.EnumMap;

/**
 * A component that stores relevant information about a player.
 */
public class PlayerComponent implements Component {
    /**
     * Stores the amount of energy the player has.
     */
    public int energy = 0;
    /**
     * Stores the study score the player has.
     */
    public int study = 0;
    /**
     * An enum map that maps an activity type to the amount of times it has been performed by the player.
     */
    public EnumMap<ActivityType, Integer> activityCount;
    /**
     * Stores the current activity that is to be performed.
     */
    public ActivityComponent currentActivity = null;
}
