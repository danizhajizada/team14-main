package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;

public class ActivityComponent implements Component {
    public enum ActivityType {
        SLEEP, STUDY, EAT, REC
    }
    public int timeChange;
    public int energyChange;
    public int studyChange;
}
