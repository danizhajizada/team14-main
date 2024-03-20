package com.tecchtitans.eng1.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;
import com.tecchtitans.eng1.components.CollisionComponent;
import com.tecchtitans.eng1.components.ComponentMappers;


/**
 * System that checks all entities in the ECSEngine with a CollisionComponent.
 * Checks if entities are colliding with one another, and if so, the entity
 * an entity is colliding with is stored inside the CollisionComponent.
 */
public class CollisionSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    /**
     * Instantiates an empty CollisionSystem.
     */
    public CollisionSystem() {};

    /**
     * Once a system is added to an engine, all the entities in that engine
     * with a CollisionComponent are added to the system in an array.
     * @param engine - Reference to engine that the system was added to.
     */
    public void addedToEngine(Engine engine)
    {
        entities = engine.getEntitiesFor(Family.all(CollisionComponent.class).get());
    }

    /**
     * This update method is called every tick. Each entity's collisionRectangle
     * in the system is checked against every other entity's collisionRectangle.
     * If these rectangles overlap, then the entities are colliding and the currentCollision
     * in each entity's CollisionComponent is updated with the entity it is colliding with.
     * @param deltaTime - The time passed since last frame in seconds.
     */
    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); i++) {
            for (int j = 0; j < entities.size(); j++) {
                Entity entity1 = entities.get(i);
                Entity entity2 = entities.get(j);

                ComponentMappers.collision.get(entity1).currentCollision = null;
                ComponentMappers.collision.get(entity2).currentCollision = null;
            }
        }
        for (int i = 0; i < entities.size(); i++) {
            for (int j = 0; j < entities.size(); j++) {

                if (i == j) {
                    continue;
                }

                Entity entity1 = entities.get(i);
                Entity entity2 = entities.get(j);

                Rectangle entity1CollisionRectangle = ComponentMappers.collision.get(entity1).collisionRectangle;
                Rectangle entity2CollisionRectangle = ComponentMappers.collision.get(entity2).collisionRectangle;

                if (entity1CollisionRectangle.overlaps(entity2CollisionRectangle)) {
                    ComponentMappers.collision.get(entity1).currentCollision = entity2;
                    ComponentMappers.collision.get(entity2).currentCollision = entity1;
                }
            }
        }
    }
}
