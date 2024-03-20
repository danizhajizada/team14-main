package com.tecchtitans.eng1.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.tecchtitans.eng1.InputKeys;
import com.tecchtitans.eng1.components.ComponentMappers;
import com.tecchtitans.eng1.components.InputComponent;

//checks for key input

/**
 * System that checks for game-relevant keys input by the user. Can detect multiple
 * keys being input at once. Checks for arrow keys input and the space bar.
 */
public class InputSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    /**
     * Instantiates an empty InputSystem.
     */
    public InputSystem() {};

    /**
     * Once a system is added to an engine, all the entities in that engine
     * with an InputComponent are added to the system in an array.
     * @param engine - The engine the system was added to.
     */
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(InputComponent.class).get());
    }

    /**
     * Called every tick. Resets each entity in the system to have no keys currently pressed.
     * Checks if the up, down, left, right, or space keys are pressed respectively. Space bar
     * must have just been pressed this tick, other keys may have been pressed prior but still
     * be depressed to be detected. This prevents interactions being repeated while space is
     * being held down.
     * @param deltaTime - Time passed since last frame in seconds.
     */
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
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                input.keysPressed |= InputKeys.SPACE;
            }
        }
    }
}
