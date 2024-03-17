package com.tecchtitans.eng1.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.tecchtitans.eng1.*;
import com.tecchtitans.eng1.components.*;
import com.badlogic.ashley.core.Entity;
import com.tecchtitans.eng1.components.GameObjectComponent.ObjectType;
import com.tecchtitans.eng1.systems.PlayerCameraSystem;
import com.tecchtitans.eng1.systems.PlayerMovementSystem;

import java.io.ObjectInput;
import java.util.ArrayList;

public class PlayScreen implements Screen {
    //may be worth having reference to game engine rather than having to fetch it from game object each time
    ENGGame game;
    ECSEngine engine;
    Map map;
    SpriteBatch batch;

    Entity player;
    ArrayList<Entity> buildings;

    float xRatio;
    float yRatio;

    public PlayScreen(ENGGame game) {
        this.game = game;
        this.engine = game.getEngine();
    }

    @Override
    public void show() {
        map = new Map("testmap5.tmx", 1728, 1728);

        batch = new SpriteBatch();

        player = createPlayer(100, 100);

        buildings = new ArrayList<>();
        for(RectangleMapObject building : map.getBuildingObjects()) {
            buildings.add(createBuilding(building));
        }

        engine.getSystem(PlayerMovementSystem.class).updateMap(map);
        engine.getSystem(PlayerCameraSystem.class).updateCamera(map.getCamera());

        engine.getSystem(PlayerCameraSystem.class).updateCameraBorder(map.getCameraBorder());

        map.getCamera().setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //building = createBuilding(100, 100, 50, 50);

        xRatio =  (float)Gdx.graphics.getWidth() / (float)map.getWidth();
        yRatio =  (float)Gdx.graphics.getHeight() / (float)map.getHeight();
    }

    private Entity createBuilding(int spawnX, int spawnY, int width, int height) {
        Entity building = engine.createEntity();

        building.add(engine.createComponent(PositionComponent.class));

        CollisionComponent collisionComponent = engine.createComponent(CollisionComponent.class);
        collisionComponent.collisionRectangle.x = spawnX;
        collisionComponent.collisionRectangle.y = spawnY;
        collisionComponent.collisionRectangle.width = width;
        collisionComponent.collisionRectangle.height = height;
        building.add(collisionComponent);

        GameObjectComponent gameObjectComponent = engine.createComponent(GameObjectComponent.class);
        gameObjectComponent.type = ObjectType.BUILDING;
        building.add(gameObjectComponent);

        engine.addEntity(building);

        return building;
    }

    // adding entity assuming building already on map
    private Entity createBuilding(RectangleMapObject buildingObject) {
        Entity building = engine.createEntity();

        CollisionComponent collisionComponent = engine.createComponent(CollisionComponent.class);
        collisionComponent.collisionRectangle = buildingObject.getRectangle();
        building.add(collisionComponent);

        ActivityComponent activityComponent = engine.createComponent(ActivityComponent.class);
        String test = (String)buildingObject.getProperties().get("type");
        activityComponent.type = ActivityType.valueOf((String)buildingObject.getProperties().get("type"));
        switch(activityComponent.type) {
            case EAT:
                activityComponent.energyChange = 0;
                activityComponent.studyChange = 0;
                break;
            case REC:
                activityComponent.energyChange = -10;
                activityComponent.studyChange = 0;
                break;
            case SLEEP:
                activityComponent.energyChange = 0;
                activityComponent.studyChange = 0;
                break;
            case STUDY:
                activityComponent.energyChange = -10;
                activityComponent.studyChange = 10;
                break;
        }
        building.add(activityComponent);

        //unsure if this is needed so commented out
        /*GameObjectComponent gameObjectComponent = engine.createComponent(GameObjectComponent.class);
        gameObjectComponent.type = ObjectType.BUILDING;
        building.add(gameObjectComponent);*/

        engine.addEntity(building);

        return building;
    }

    private Entity createPlayer(int spawnX, int spawnY) {
        Entity player = new Entity();

        player.add(engine.createComponent(PlayerComponent.class));

        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
        positionComponent.positionVector.x = spawnX;
        positionComponent.positionVector.y = spawnY;
        player.add(positionComponent);

        player.add(engine.createComponent(VelocityComponent.class));
        player.add(engine.createComponent(InputComponent.class));

        TextureComponent playerTexture = engine.createComponent(TextureComponent.class);
        playerTexture.texture = new Texture("spacesoldier.png");
        playerTexture.srcStartX = 8;
        playerTexture.srcStartY = 11;
        playerTexture.width = 50;
        playerTexture.height = 50;

        player.add(playerTexture);

        CollisionComponent collisionComponent = engine.createComponent(CollisionComponent.class);
        collisionComponent.collisionRectangle.x = spawnX;
        collisionComponent.collisionRectangle.y = spawnY;
        collisionComponent.collisionRectangle.width = 50;
        collisionComponent.collisionRectangle.height = 50;
        player.add(collisionComponent);

        PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);
        playerComponent.activityCount = new int[ActivityType.values().length];
        playerComponent.energy = 100;
        playerComponent.study = 0;
        player.add(playerComponent);

        engine.addEntity(player);

        return player;
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);

        map.render();

        // Draw player
        TextureComponent playerTexture = player.getComponent(TextureComponent.class);
        PositionComponent playerPosition = player.getComponent(PositionComponent.class);

        Camera camera = map.getCamera();

        //float xRenderPosition = camera.viewportWidth / 2 - playerTexture.width / 2.0f;
        //float yRenderPosition = camera.viewportHeight / 2 - playerTexture.height / 2.0f;
        float xRenderPosition = camera.viewportWidth / 2;
        float yRenderPosition = camera.viewportHeight / 2;

        float cameraXCenter = camera.position.x - camera.viewportWidth / 2;

        if (playerPosition.positionVector.x < map.getCameraBorder().x + camera.viewportWidth / 2) {
            xRenderPosition -= map.getCameraBorder().x + camera.viewportWidth / 2 - playerPosition.positionVector.x;
        }
        if (playerPosition.positionVector.x > map.getCameraBorder().width - camera.viewportWidth / 2) {
            xRenderPosition += playerPosition.positionVector.x - (map.getCameraBorder().width - camera.viewportWidth / 2);
        }

        if (playerPosition.positionVector.y < map.getCameraBorder().x + camera.viewportHeight / 2) {
            yRenderPosition -= map.getCameraBorder().x + camera.viewportHeight / 2 - playerPosition.positionVector.y;
        }
        if (playerPosition.positionVector.y > map.getCameraBorder().height - camera.viewportHeight / 2) {
            yRenderPosition += playerPosition.positionVector.y - (map.getCameraBorder().height - camera.viewportHeight / 2);
        }

        //System.out.println(camera.position.x + map.getCameraBorder().width - camera.viewportWidth / 2);
        //System.out.println(playerPosition.positionVector.x);

        batch.begin();
        batch.draw(playerTexture.texture, xRenderPosition, yRenderPosition, playerTexture.srcStartX, playerTexture.srcStartY,
                   playerTexture.width, playerTexture.height);
        batch.end();

        engine.update(v);
    }

    @Override
    public void resize(int i, int i1) {
        if (i < map.getWidth() && i1 < map.getHeight()) {
            map.getCamera().setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            //map.getCamera().update();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
