package com.tecchtitans.eng1;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;

public class MovementSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    public MovementSystem() {};

    public void addedToEngine(Engine engine)
    {
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);

            PositionComponent position = ComponentMappers.position.get(entity);
            VelocityComponent velocity = ComponentMappers.velocity.get(entity);

            position.x += velocity.x * deltaTime;
            position.y += velocity.y * deltaTime;
        }
    }
}
