package com.tecchtitans.eng1;

import com.badlogic.ashley.core.Engine;

/**
 * Extends the Engine class to add a currentMap field.
 //* @see com.badlogic.ashley.core.Engine
 */
public class ECSEngine extends Engine {
    Map currentMap;

    /**
     * Instantiates an ECSEngine.
     */
    public ECSEngine() {
        super();
    }

    /**
     * Updates the current map in the ECSEngine to a desired map.
     * @param map - Desired map given as a Map type.
     */
    public void updateMap(Map map) {
        this.currentMap = map;
    }
}
