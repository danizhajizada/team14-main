package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.tecchtitans.eng1.ActivityType;

import java.util.Dictionary;
import java.util.EnumMap;
import java.util.HashMap;

public class PlayerComponent implements Component {
    public int energy;
    public int study;
    //not sure whether an enummap or an array is more appropriate here
    //public EnumMap<ActivityType, Integer> activityCount;
    public EnumMap<ActivityType, Integer> activityCount;
    public ActivityComponent currentActivity;
}
