package com.tecchtitans.eng1;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(InputComponent.class).get());
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            InputComponent input = ComponentMappers.input.get(entity);
            input.keysPressed = InputKeys.NONE;

            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                input.keysPressed |= InputKeys.UP;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                input.keysPressed |= InputKeys.DOWN;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                input.keysPressed |= InputKeys.LEFT;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                input.keysPressed |= InputKeys.RIGHT;
            }

            System.out.println(input.keysPressed);
        }
    }
}
