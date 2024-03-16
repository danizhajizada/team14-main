package com.tecchtitans.eng1.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.tecchtitans.eng1.components.*;

public class PlayerCameraSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private OrthographicCamera currentCamera;

    private Rectangle cameraBorder;

    public PlayerCameraSystem() {};

    public void addedToEngine(Engine engine)
    {
        entities = engine.getEntitiesFor(Family.all(PlayerComponent.class, PositionComponent.class, CollisionComponent.class).get());
    }

    public void updateCamera(OrthographicCamera camera) {
        this.currentCamera = camera;
    }

    public void updateCameraBorder(Rectangle cameraBorder) {
        this.cameraBorder = cameraBorder;
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);

            PositionComponent position = ComponentMappers.position.get(entity);
            CollisionComponent collisionComponent = ComponentMappers.collision.get(entity);

            float viewportWidth = currentCamera.viewportWidth;
            float viewportHeight = currentCamera.viewportHeight;

            Rectangle newCameraViewRectangle = new Rectangle(position.x + viewportWidth / 2,
                                                            position.y + viewportHeight / 2,
                                                            viewportWidth, viewportHeight);

            if (cameraBorder.contains(newCameraViewRectangle)) {
                currentCamera.position.x = newCameraViewRectangle.x;
                currentCamera.position.y = newCameraViewRectangle.y;
                currentCamera.update();
            }
        }
    }
}
