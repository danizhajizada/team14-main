package com.tecchtitans.eng1.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.tecchtitans.eng1.components.*;

public class PlayerCameraSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private OrthographicCamera currentCamera;

    private Rectangle cameraBorder;

    public PlayerCameraSystem() {};

    public void addedToEngine(Engine engine)
    {
        entities = engine.getEntitiesFor(Family.all(PlayerComponent.class, PositionComponent.class).get());
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

            TextureComponent playerTextureComponent = ComponentMappers.texture.get(entity);

            currentCamera.position.x = MathUtils.clamp(
                    position.positionVector.x + playerTextureComponent.width / 2.0f,
                    cameraBorder.x + currentCamera.viewportWidth / 2,
                    cameraBorder.x + cameraBorder.width - currentCamera.viewportWidth / 2
            );
            currentCamera.position.y = MathUtils.clamp(
                    position.positionVector.y + playerTextureComponent.height / 2.0f,
                    cameraBorder.y + currentCamera.viewportHeight / 2,
                    cameraBorder.y + cameraBorder.height - currentCamera.viewportHeight / 2
            );
        }
    }
}
