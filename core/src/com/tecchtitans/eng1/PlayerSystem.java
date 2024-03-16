package com.tecchtitans.eng1;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.tecchtitans.eng1.components.*;

public class PlayerSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    public PlayerSystem() {};

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PlayerComponent.class, VelocityComponent.class, InputComponent.class).get());
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            PlayerComponent player = ComponentMappers.player.get(entity);
            VelocityComponent velocity = ComponentMappers.velocity.get(entity);
            InputComponent input = ComponentMappers.input.get(entity);

            velocity.x = 0.0f;
            velocity.y = 0.0f;

            if ((input.keysPressed & InputKeys.UP) != 0) {
                velocity.y += player.movementSpeed;
            }
            if ((input.keysPressed & InputKeys.DOWN) != 0) {
                velocity.y += -player.movementSpeed;
            }
            if ((input.keysPressed & InputKeys.LEFT) != 0) {
                velocity.x += -player.movementSpeed;
            }
            if ((input.keysPressed & InputKeys.RIGHT) != 0) {
                velocity.x += player.movementSpeed;
            }
        }
    }
}
