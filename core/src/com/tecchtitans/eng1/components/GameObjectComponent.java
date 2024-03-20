package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;

/**
 * A component that relevant information about game objects.
 */
public class GameObjectComponent implements Component {
    /**
     * An enum that holds a value for the type of game object.
     */
    public enum ObjectType {
        NULL, BUILDING
    }

    /**
     * Stores a value indicating the type of game object.
     */
    public ObjectType type = ObjectType.NULL;
}
