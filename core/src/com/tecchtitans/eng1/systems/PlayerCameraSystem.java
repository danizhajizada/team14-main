package com.tecchtitans.eng1.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.tecchtitans.eng1.components.*;

/**
 * A system that handles updates to a maps camera based on player position.
 */
public class PlayerCameraSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private OrthographicCamera currentCamera;

    private Rectangle cameraBorder;

    private boolean cameraAtLeftBorder, cameraAtRightBorder;
    private boolean cameraAtTopBorder, cameraAtBottomBorder;

    /**
     * Instantiates PlayerCameraSystem by setting the boolean values for the camera being at
     * any given border to false.
     */
    public PlayerCameraSystem() {
        cameraAtLeftBorder = cameraAtRightBorder = cameraAtTopBorder = cameraAtBottomBorder = false;
    };

    /**
     * Once a system is added to an engine, all the entities in that engine
     * with a PlayerComponent and PositionComponent are added to the system in an array.
     * @param engine - The engine the system was added to.
     */
    public void addedToEngine(Engine engine)
    {
        entities = engine.getEntitiesFor(Family.all(PlayerComponent.class, PositionComponent.class).get());
    }

    /**
     * Sets camera to the desired camera.
     * @param camera - Desired camera.
     */
    public void updateCamera(OrthographicCamera camera) {
        this.currentCamera = camera;
    }

    /**
     * Updates the camera border to the desired cameraBorder.
     * @param cameraBorder - Rectangle that represents the bounds of the camera.
     */
    public void updateCameraBorder(Rectangle cameraBorder) {
        this.cameraBorder = cameraBorder;
    }

    /**
     * Called every tick. Changes the cameras position so the player is at the centre.
     * If the player goes beyond the camera border so it no longer can be centred around the player,
     * the appropriate field is changed to indicate which side of the border has been passed.
     * @param deltaTime - Time passed in seconds since last frame.
     */
    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);

            PositionComponent position = ComponentMappers.position.get(entity);
            TextureComponent playerTextureComponent = ComponentMappers.texture.get(entity);

            float playerCentreX = position.positionVector.x + playerTextureComponent.width / 2.0f;
            float playerCentreY = position.positionVector.y + playerTextureComponent.height / 2.0f;

            float minCameraXPosition = cameraBorder.x + currentCamera.viewportWidth / 2.0f;
            float maxCameraXPosition = cameraBorder.x + cameraBorder.width - currentCamera.viewportWidth / 2.0f;

            float minCameraYPosition = cameraBorder.y + currentCamera.viewportHeight / 2.0f;
            float maxCameraYPosition = cameraBorder.y + cameraBorder.height - currentCamera.viewportHeight / 2.0f;

            currentCamera.position.x = MathUtils.clamp(
                    playerCentreX,
                    minCameraXPosition,
                    maxCameraXPosition
            );

            currentCamera.position.y = MathUtils.clamp(
                    playerCentreY,
                    minCameraYPosition,
                    maxCameraYPosition
            );

            cameraAtLeftBorder = currentCamera.position.x <= minCameraXPosition;
            cameraAtRightBorder = currentCamera.position.x >= maxCameraXPosition;

            cameraAtBottomBorder = currentCamera.position.y <= minCameraYPosition;
            cameraAtTopBorder = currentCamera.position.y >= maxCameraYPosition;
        }
    }

    /**
     * Returns a boolean value which denotes whether the camera is at
     * the left border or not.
     * @return true if camera is at left border, false otherwise.
     */
    public boolean isCameraAtLeftBorder() {
        return cameraAtLeftBorder;
    }

    /**
     * Returns a boolean value which denotes whether the camera is at
     * the right border or not.
     * @return true if camera is at right border, false otherwise.
     */
    public boolean isCameraAtRightBorder() {
        return cameraAtRightBorder;
    }

    /**
     * Returns a boolean value which denotes whether the camera is at
     * the top border or not.
     * @return true if camera is at top border, false otherwise.
     */
    public boolean isCameraAtTopBorder() {
        return cameraAtTopBorder;
    }

    /**
     * Returns a boolean value which denotes whether the camera is at
     * the bottom border or not.
     * @return true if camera is at bottom border, false otherwise.
     */
    public boolean isCameraAtBottomBorder() {
        return cameraAtBottomBorder;
    }
}
