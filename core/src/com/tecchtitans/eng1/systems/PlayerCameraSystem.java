package com.tecchtitans.eng1.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tecchtitans.eng1.components.*;

public class PlayerCameraSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private OrthographicCamera currentCamera;

    public PlayerCameraSystem() {};

    public void addedToEngine(Engine engine)
    {
        entities = engine.getEntitiesFor(Family.all(PlayerComponent.class, PositionComponent.class).get());
    }

    public void updateCamera(OrthographicCamera camera) {
        this.currentCamera = camera;
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);

            PositionComponent position = ComponentMappers.position.get(entity);

            currentCamera.translate(position.x, position.y);
            currentCamera.position.x = position.x;
            currentCamera.position.y = position.y;
            currentCamera.update();
        }
    }
}
