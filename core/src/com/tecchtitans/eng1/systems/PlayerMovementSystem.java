package com.tecchtitans.eng1.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.tecchtitans.eng1.Map;
import com.tecchtitans.eng1.components.*;

public class PlayerMovementSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private Map currentMap;

    public PlayerMovementSystem() {};

    public void addedToEngine(Engine engine)
    {
        entities = engine.getEntitiesFor(Family.all(PlayerComponent.class, CollisionComponent.class).get());
    }

    public void updateMap(Map map) {
        this.currentMap = map;
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);

            CollisionComponent collisionComponent = ComponentMappers.collision.get(entity);

            if (!currentMap.getWorldBorder().contains(collisionComponent.collisionRectangle)) {
                System.out.println("out");
            }
        }
    }
}
