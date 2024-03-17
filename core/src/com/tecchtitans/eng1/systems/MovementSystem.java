package com.tecchtitans.eng1.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.tecchtitans.eng1.components.CollisionComponent;
import com.tecchtitans.eng1.components.ComponentMappers;
import com.tecchtitans.eng1.components.PositionComponent;
import com.tecchtitans.eng1.components.VelocityComponent;

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

            //position =

            CollisionComponent rectangleComponent = ComponentMappers.collision.get(entity);

            rectangleComponent.collisionRectangle.x = position.positionVector.x;
            rectangleComponent.collisionRectangle.y = position.positionVector.y;
        }
    }
}
