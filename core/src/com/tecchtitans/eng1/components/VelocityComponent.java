package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Stores relevant information about velocity.
 */
public class VelocityComponent implements Component {
    /**
     * Stores the speed at which the entity is moving at.
     */
    public float movementSpeed = 500.0f;

    /**
     * Stores the direction of the velocity as a unit vector.
     */
    public Vector2 velocityUnitVector = new Vector2();
}
