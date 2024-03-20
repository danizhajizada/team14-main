package com.tecchtitans.eng1.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.tecchtitans.eng1.InputKeys;
import com.tecchtitans.eng1.components.*;

/**
 * System that handles what should occur to players given certain actions in the game,
 * for instance if a key is pressed to move a player, the velocity of the player should
 * be modified accordingly. If a key is pressed to perform an action, it should check
 * if the player is the vicinity of an interactive entity, and if so set the player's
 * current activity to the respective activity.
 */
public class PlayerSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    /**
     * Instantiates an empty PlayerSystem.
     */
    public PlayerSystem() {};

    /**
     * Once a system is added to an engine, all the player entities in that engine
     * with a PlayerComponent, VelocityComponent, and InputComponent are added to
     * the system in an array.
     * @param engine - The engine the system was added to.
     */
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PlayerComponent.class, VelocityComponent.class, InputComponent.class).get());
    }

    /**
     * Called every tick. Checks each player entity if a movement key of up, down, left
     * or right is pressed in the InputComponent. If so, the velocity for that direction
     * is set to 1 or -1 respectively, where -1 is left or down, and 1 is up or right.
     * If there is no movement key pressed for a certain axis, the velocity for that
     * direction is 0. If the space key is pressed, the player's currentCollision is checked
     * to see if they are interacting with an interactive entity. If so, the player's
     * current activity is set to the correct activity type, given the entity interacted with.
     * @param deltaTime - Time passed since last frame in seconds.
     */
    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            //PlayerComponent player = ComponentMappers.player.get(entity);
            VelocityComponent velocity = ComponentMappers.velocity.get(entity);
            InputComponent input = ComponentMappers.input.get(entity);
            Entity currentCollision = ComponentMappers.collision.get(entity).currentCollision;

            velocity.velocityUnitVector = new Vector2();

            if ((input.keysPressed & InputKeys.UP) != 0) {
                velocity.velocityUnitVector.y = 1;
            }
            if ((input.keysPressed & InputKeys.DOWN) != 0) {
                velocity.velocityUnitVector.y = -1;
            }
            if ((input.keysPressed & InputKeys.LEFT) != 0) {
                velocity.velocityUnitVector.x = -1;
            }
            if ((input.keysPressed & InputKeys.RIGHT) != 0) {
                velocity.velocityUnitVector.x = 1;
            }
            if ((input.keysPressed & InputKeys.SPACE) != 0) {
                if(currentCollision != null) {
                    if(ComponentMappers.gameObject.get(currentCollision) != null) {
                        if(ComponentMappers.gameObject.get(currentCollision).type == GameObjectComponent.ObjectType.BUILDING) {
                            //System.out.println(ComponentMappers.activity.get(currentCollision).type);
                            ComponentMappers.player.get(entity).currentActivity = ComponentMappers.activity.get(currentCollision);
                        }
                    }
                }
            }
        }
    }
}
