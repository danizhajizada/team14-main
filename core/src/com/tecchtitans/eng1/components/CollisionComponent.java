package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;

public class CollisionComponent implements Component {
    public Entity currentCollision = null;
    public Rectangle collisionRectangle = new Rectangle();
}
