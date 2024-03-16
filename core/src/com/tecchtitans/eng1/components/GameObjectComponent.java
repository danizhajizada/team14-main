package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;

public class GameObjectComponent implements Component {
    public enum ObjectType {
        NULL, BUILDING
    }

    public ObjectType type = ObjectType.NULL;
}
