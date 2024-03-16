package com.tecchtitans.eng1.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;
import com.tecchtitans.eng1.Map;
import com.tecchtitans.eng1.components.*;

import java.awt.*;

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
            VelocityComponent velocity = ComponentMappers.velocity.get(entity);
            PositionComponent position = ComponentMappers.position.get(entity);

            float newPositionX = position.x + velocity.x * deltaTime;
            float newPositionY = position.y + velocity.y * deltaTime;

            Rectangle newCollisionRectangle = new Rectangle(newPositionX, newPositionY,
                                                            collisionComponent.collisionRectangle.width,
                                                            collisionComponent.collisionRectangle.height);

            if (currentMap.getWorldBorder().contains(newCollisionRectangle)) {
                //System.out.println(currentMap.getWorldBorder().width + " " + currentMap.getWorldBorder().height);
                //System.out.println(collisionComponent.collisionRectangle.x + " " + collisionComponent.collisionRectangle.y);
                //System.out.println(collisionComponent.collisionRectangle.width + " " + collisionComponent.collisionRectangle.height);
                //System.out.println("out");

                position.x += velocity.x * deltaTime;
                position.y += velocity.y * deltaTime;

                collisionComponent.collisionRectangle.x = position.x;
                collisionComponent.collisionRectangle.y = position.y;
            }
        }
    }
}
