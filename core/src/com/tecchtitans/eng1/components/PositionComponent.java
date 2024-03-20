package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Stores relevant information about position.
 */
public class PositionComponent implements Component {
    /**
     * A vector that stores current position.
     */
    public Vector2 positionVector = new Vector2();
}
