package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;

/**
 * A component that stores relevant information about collisions.
 */
public class CollisionComponent implements Component {
    /**
     * Stores the most recent entity that has been collided with.
     */
    public Entity currentCollision = null;

    /**
     * Stores the collision rectangle for the entity.
     */
    public Rectangle collisionRectangle = new Rectangle();
}
