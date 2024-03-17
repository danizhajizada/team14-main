package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class VelocityComponent implements Component {
    public float movementSpeed = 500.0f;
    public Vector2 velocityUnitVector = new Vector2();
}
