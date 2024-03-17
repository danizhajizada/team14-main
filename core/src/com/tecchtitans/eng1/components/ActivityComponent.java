package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;
import com.tecchtitans.eng1.ActivityType;

public class ActivityComponent implements Component {

    public int timeChange;
    public int energyChange;
    public int studyChange;
    public ActivityType type;
}
