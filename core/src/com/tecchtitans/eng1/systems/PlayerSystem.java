package com.tecchtitans.eng1.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.tecchtitans.eng1.InputKeys;
import com.tecchtitans.eng1.components.ComponentMappers;
import com.tecchtitans.eng1.components.InputComponent;
import com.tecchtitans.eng1.components.PlayerComponent;
import com.tecchtitans.eng1.components.VelocityComponent;

public class PlayerSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    public PlayerSystem() {};

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PlayerComponent.class, VelocityComponent.class, InputComponent.class).get());
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            //PlayerComponent player = ComponentMappers.player.get(entity);
            VelocityComponent velocity = ComponentMappers.velocity.get(entity);
            InputComponent input = ComponentMappers.input.get(entity);

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
        }
    }
}
