package com.tecchtitans.eng1.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.tecchtitans.eng1.Map;
import com.tecchtitans.eng1.components.*;
import java.util.ArrayList;

/**
 * System that handles moving a player around a map. Ensures that a player cannot
 * walk outside the bounds of a map by checking for a collision ????(not sure if this is true)
 */
public class PlayerMovementSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private Map currentMap;

    /**
     * Instantiates an empty PlayerMovementSystem.
     */
    public PlayerMovementSystem() {};

    /**
     * Once a system is added to an engine, all the entities in that engine
     * with a PlayerComponent and CollisionComponent are added to the system in an array.
     * @param engine - The engine the system was added to.
     */
    public void addedToEngine(Engine engine)
    {
        entities = engine.getEntitiesFor(Family.all(PlayerComponent.class, CollisionComponent.class).get());
    }

    /**
     * Updates the current map the player is on with a desired map.
     * @param map - Desired Map instance for the play to be on.
     */
    public void updateMap(Map map) {
        this.currentMap = map;
    }

    /**
     * Called every tick. Write about how it works.
     * @param deltaTime - Time passed since last frame in seconds.
     */
    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);

            CollisionComponent collisionComponent = ComponentMappers.collision.get(entity);
            VelocityComponent velocity = ComponentMappers.velocity.get(entity);
            PositionComponent position = ComponentMappers.position.get(entity);

            Vector2 newVelocity = velocity.velocityUnitVector.scl(velocity.movementSpeed * deltaTime);

            float newXPosition = position.positionVector.x + newVelocity.x;
            float newYPosition = position.positionVector.y + newVelocity.y;

            Rectangle newXCollisionRectangle = new Rectangle(newXPosition, position.positionVector.y,
                                                            collisionComponent.collisionRectangle.width,
                                                            collisionComponent.collisionRectangle.height);

            Rectangle newYCollisionRectangle = new Rectangle(position.positionVector.x, newYPosition,
                    collisionComponent.collisionRectangle.width,
                    collisionComponent.collisionRectangle.height);

            Rectangle newCollisionRectangle = new Rectangle(newXPosition, newYPosition,
                    collisionComponent.collisionRectangle.width,
                    collisionComponent.collisionRectangle.height);

            ArrayList<RectangleMapObject> solidObjects = currentMap.getSolidObjects();

            boolean doesTouchSolid = false;
            for (RectangleMapObject obj : solidObjects) {
                if (newCollisionRectangle.overlaps(obj.getRectangle())) {
                    doesTouchSolid = true;
                }
            }

            // If player will touch a solid object then don't move.
            if (doesTouchSolid) { continue; }

            if (currentMap.getWorldBorder().contains(newXCollisionRectangle)) {
                position.positionVector.x = newXPosition;

                collisionComponent.collisionRectangle.x = position.positionVector.x;
            }

            if (currentMap.getWorldBorder().contains(newYCollisionRectangle)) {
                position.positionVector.y = newYPosition;

                collisionComponent.collisionRectangle.y = position.positionVector.y;
            }
        }
    }
}
