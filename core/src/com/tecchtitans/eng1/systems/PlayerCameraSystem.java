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

            //Rectangle playerCameraRectangle = new Rectangle(position.positionVector.x - currentCamera.viewportWidth / 2,
            //        position.positionVector.y - currentCamera.viewportHeight / 2, currentCamera.viewportWidth, currentCamera.viewportHeight);

            //System.out.println(cameraBorder.x);

            //currentCamera.position.x = cameraBorder.x + currentCamera.viewportWidth / 2;
            currentCamera.position.x = MathUtils.clamp(position.positionVector.x + playerTextureComponent.width / 2.0f, cameraBorder.x + currentCamera.viewportWidth / 2, cameraBorder.x + cameraBorder.width - currentCamera.viewportWidth / 2) ;
            currentCamera.position.y = MathUtils.clamp(position.positionVector.y + playerTextureComponent.height / 2.0f, cameraBorder.y + currentCamera.viewportHeight / 2, cameraBorder.y + cameraBorder.height - currentCamera.viewportHeight / 2) ;

            /*
            float viewportWidth = currentCamera.viewportWidth;
            float viewportHeight = currentCamera.viewportHeight;

            float lengthToRightXEdge = viewportWidth - position.x;
            float lengthToLeftXEdge = position.x - viewportWidth;

            float lengthToClosestXEdge = Math.max(viewportWidth - position.x, position.x);

            float lengthToClosestYEdge = Math.max(viewportHeight - position.y, position.y);

            float lengthToTopYEdge = viewportHeight - position.y;
            float lengthToBottomYEdge = position.y - viewportHeight;

            Rectangle newCameraViewXRectangle = new Rectangle(position.x,
                                                            position.y,
                                                        viewportWidth,
                                                        lengthToClosestYEdge);

            Rectangle newCameraViewYRectangle = new Rectangle(position.x,
                    position.y,
                    lengthToClosestXEdge,
                    viewportHeight);

            if (true) {
                //currentCamera.position.x = newCameraViewXRectangle.x + viewportWidth / 2;

                //currentCamera.position.x = MathUtils.clamp(position.x + viewportWidth / 2, cameraBorder.x + viewportWidth, cameraBorder.x + cameraBorder.width - viewportWidth);

                currentCamera.update();
            }
            if (true) {
                currentCamera.position.y = position.y + viewportHeight / 2;
               // currentCamera.position.y = MathUtils.clamp(position.y, cameraBorder.y + viewportHeight, cameraBorder.y + cameraBorder.height - viewportHeight);
              //  currentCamera.update();
            }
            */
        }
    }
}
