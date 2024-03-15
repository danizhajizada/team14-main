package com.tecchtitans.eng1;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;
import com.tecchtitans.eng1.components.*;

public class CollisionSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    public CollisionSystem() {};

    public void addedToEngine(Engine engine)
    {
        entities = engine.getEntitiesFor(Family.all(CollisionRectangleComponent.class, CollisionComponent.class).get());
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); i++) {
            for (int j = 0; j < entities.size(); j++) {

                if (i == j) {
                    continue;
                }

                Entity entity1 = entities.get(i);
                Entity entity2 = entities.get(j);

                Rectangle entity1CollisionRectangle = ComponentMappers.collisionRectangle.get(entity1).collisionRectangle;
                Rectangle entity2CollisionRectangle = ComponentMappers.collisionRectangle.get(entity2).collisionRectangle;

                if (entity1CollisionRectangle.overlaps(entity2CollisionRectangle)) {
                    ComponentMappers.collision.get(entity1).currentCollision = entity2;
                    ComponentMappers.collision.get(entity2).currentCollision = entity1;

                    System.out.println("Collide!");
                }
            }
        }
    }
}
