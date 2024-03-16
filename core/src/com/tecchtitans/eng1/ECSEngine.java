package com.tecchtitans.eng1;

import com.badlogic.ashley.core.Engine;

public class ECSEngine extends Engine {
    Map currentMap;

    public ECSEngine() {
        super();
    }

    public void updateMap(Map map) {
        this.currentMap = map;
    }
}
