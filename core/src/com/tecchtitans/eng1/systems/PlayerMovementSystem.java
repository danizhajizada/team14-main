package com.tecchtitans.eng1.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.tecchtitans.eng1.components.*;

public class PlayerMovementSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    public PlayerMovementSystem() {};

    public void addedToEngine(Engine engine)
    {
        entities = engine.getEntitiesFor(Family.all(PlayerComponent.class, PositionComponent.class).get());
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);

            PositionComponent position = ComponentMappers.position.get(entity);
            CollisionComponent rectangleComponent = ComponentMappers.collision.get(entity);

            rectangleComponent.collisionRectangle.x = position.x;
            rectangleComponent.collisionRectangle.y = position.y;
        }
    }
}
