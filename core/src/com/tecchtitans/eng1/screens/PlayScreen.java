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
import com.tecchtitans.eng1.systems.GameSystem;
import com.tecchtitans.eng1.systems.PlayerCameraSystem;
import com.tecchtitans.eng1.systems.PlayerMovementSystem;
import com.tecchtitans.eng1.systems.UIRenderSystem;

import java.io.ObjectInput;
import java.util.ArrayList;
import java.util.EnumMap;

/**
 * PlayScreen is the screen that will be displayed during gameplay. It allows for
 * a user designed map to be displayed and can create entities for buildings
 * that can be interacted so long as they are identified in the map file. A
 * player entity is added on the map that can interact with the aforementioned
 * buildings, along with implementing UI entities that display useful information
 * about the currently running game to the player.
 */
public class PlayScreen extends GameScreen {
    ECSEngine engine;
    SpriteBatch batch;
    Entity player;
    ArrayList<Entity> buildings;
    Entity energyBar;
    Entity timeUI;
    Entity dayCounter;
    Entity activityCounter;

    /**
     * Instantiates a play screen with the currently running game instance, along with
     * the ECS engine contained within the game.
     * @param game
     */
    public PlayScreen(ENGGame game) {
        this.game = game;
        this.engine = game.getEngine();
    }


    @Override
    public void show() {
        map = new Map("testmap5.tmx", 1728, 1728);

        batch = new SpriteBatch();

        player = createPlayer(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 50, 50);

        // Find building objects from the map, then create entities for them and add ti the engine.
        buildings = new ArrayList<>();
        for(RectangleMapObject building : map.getBuildingObjects()) {
            buildings.add(createBuilding(building));
        }

        // Update relevant systems with the map that has just been loaded.
        engine.getSystem(PlayerMovementSystem.class).updateMap(map);
        engine.getSystem(PlayerCameraSystem.class).updateCamera(map.getCamera());
        engine.getSystem(PlayerCameraSystem.class).updateCameraBorder(map.getCameraBorder());

        map.getCamera().setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        game.getAudioManager().playMusic("audio/bgmusic.mp3");

        // Create UI entities
        energyBar = createStatBar(50, Gdx.graphics.getHeight() - 100, 250, 50);
        timeUI = createUIClock(350, Gdx.graphics.getHeight() - 100, 150, 50);
        dayCounter = createUIDayCounter(550, Gdx.graphics.getHeight() - 100, 150, 50);
        activityCounter = createUIActivityCounter(50, 50, 100, 100);
    }

    private Entity createUIActivityCounter(int renderX, int renderY, int width, int height) {
        Entity activityCounter = new Entity();

        UIComponent uiComponent = engine.createComponent(UIComponent.class);
        uiComponent.type = UIComponentType.ACTCOUNT;
        activityCounter.add(uiComponent);

        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        textureComponent.texture = new Texture("stats.png");
        textureComponent.srcStartX = 0;
        textureComponent.srcStartY = 86;
        textureComponent.width = width;
        textureComponent.height = height;
        activityCounter.add(textureComponent);

        UIActivityCountComponent uiActivityCountComponent = engine.createComponent(UIActivityCountComponent.class);
        uiActivityCountComponent.outerPartSrcX = 0;
        uiActivityCountComponent.outerPartSrcY = 86;
        uiActivityCountComponent.outerPartSrcWidth = 49;
        uiActivityCountComponent.outerPartSrcHeight = 49;

        uiActivityCountComponent.numbersSrcX = 0;
        uiActivityCountComponent.numbersSrcY = 50;
        uiActivityCountComponent.numbersSrcWidth = 5;
        uiActivityCountComponent.numbersSrcHeight = 7;

        uiActivityCountComponent.sleepNumberXOffset = 36;
        uiActivityCountComponent.sleepNumberYOffset = 39;

        uiActivityCountComponent.studyNumberXOffset = 36;
        uiActivityCountComponent.studyNumberYOffset = 27;

        uiActivityCountComponent.eatNumberXOffset = 36;
        uiActivityCountComponent.eatNumberYOffset = 15;

        uiActivityCountComponent.recNumberXOffset = 36;
        uiActivityCountComponent.recNumberYOffset = 3;
        activityCounter.add(uiActivityCountComponent);

        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
        positionComponent.positionVector.x = renderX;
        positionComponent.positionVector.y = renderY;
        activityCounter.add(positionComponent);

        engine.addEntity(activityCounter);

        return activityCounter;
    }

    private Entity createUIDayCounter(int renderX, int renderY, int width, int height) {
        Entity dayCounter = new Entity();

        UIComponent uiComponent = engine.createComponent(UIComponent.class);
        uiComponent.type = UIComponentType.DAY;
        dayCounter.add(uiComponent);

        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        textureComponent.texture = new Texture("stats.png");
        textureComponent.srcStartX = 0;
        textureComponent.srcStartY = 74;
        textureComponent.width = width;
        textureComponent.height = height;
        dayCounter.add(textureComponent);

        UIDayComponent uiDayComponent = engine.createComponent(UIDayComponent.class);
        uiDayComponent.outerPartSrcX = 0;
        uiDayComponent.outerPartSrcY = 74;
        uiDayComponent.outerPartSrcWidth = 31;
        uiDayComponent.outerPartSrcHeight = 11;

        uiDayComponent.numbersSrcX = 0;
        uiDayComponent.numbersSrcY = 50;
        uiDayComponent.numbersSrcWidth = 5;
        uiDayComponent.numbersSrcHeight = 7;

        uiDayComponent.numberXOffset = 24;
        uiDayComponent.numberYOffset = 2;

        uiDayComponent.currentDay = 0;
        dayCounter.add(uiDayComponent);

        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
        positionComponent.positionVector.x = renderX;
        positionComponent.positionVector.y = renderY;
        dayCounter.add(positionComponent);

        engine.addEntity(dayCounter);

        return dayCounter;
    }

    private Entity createUIClock(int renderX, int renderY, int width, int height) {
        Entity clock = new Entity();

        UIComponent uiComponent = engine.createComponent(UIComponent.class);
        uiComponent.type = UIComponentType.TIME;
        clock.add(uiComponent);

        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        textureComponent.texture = new Texture("stats.png");
        textureComponent.srcStartX = 0;
        textureComponent.srcStartY = 38;
        textureComponent.width = width;
        textureComponent.height = height;
        //textureComponent.width = 100;
        //textureComponent.height = 100;
        clock.add(textureComponent);

        UITimeComponent uiTimeComponent = engine.createComponent(UITimeComponent.class);
        uiTimeComponent.outerPartSrcX = 0;
        uiTimeComponent.outerPartSrcY = 38;
        uiTimeComponent.outerPartSrcWidth = 54;
        uiTimeComponent.outerPartSrcHeight = 11;

        uiTimeComponent.numbersSrcX = 0;
        uiTimeComponent.numbersSrcY = 50;
        uiTimeComponent.numbersSrcWidth = 5;
        uiTimeComponent.numbersSrcHeight = 7;

        uiTimeComponent.amSrcX = 0;
        uiTimeComponent.amSrcY = 58;
        uiTimeComponent.amSrcWidth = 11;
        uiTimeComponent.amSrcHeight = 7;

        uiTimeComponent.pmSrcX = 0;
        uiTimeComponent.pmSrcY = 66;
        uiTimeComponent.pmSrcWidth = 11;
        uiTimeComponent.pmSrcHeight = 7;

        uiTimeComponent.numbersXOffset = 29;
        uiTimeComponent.numbersYOffset = 2;

        uiTimeComponent.currentHour = 0;
        clock.add(uiTimeComponent);

        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
        positionComponent.positionVector.x = renderX;
        positionComponent.positionVector.y = renderY;
        clock.add(positionComponent);

        engine.addEntity(clock);

        return clock;
    }

    private Entity createStatBar(int renderX, int renderY, int width, int height) {
        Entity statBar = new Entity();

        UIComponent uiComponent = engine.createComponent(UIComponent.class);
        uiComponent.type = UIComponentType.STATBAR;
        statBar.add(uiComponent);

        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        textureComponent.texture = new Texture("stats.png");
        textureComponent.srcStartX = 0;
        textureComponent.srcStartY = 0;
        textureComponent.width = width;
        textureComponent.height = height;
        statBar.add(textureComponent);

        StatBarComponent statBarComponent = engine.createComponent(StatBarComponent.class);
        statBarComponent.outerPartSrcX = 0;
        statBarComponent.outerPartSrcY = 0;
        statBarComponent.outerPartSrcWidth = 109;
        statBarComponent.outerPartSrcHeight = 19;

        statBarComponent.innerPartSrcX = 0;
        statBarComponent.innerPartSrcY = 20;
        statBarComponent.innerPartSrcWidth = 94;
        statBarComponent.innerPartSrcHeight = 17;

        statBarComponent.innerPartXOffset = 14;
        statBarComponent.innerPartYOffset = 1;

        statBarComponent.progress = 1;
        statBar.add(statBarComponent);

        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
        positionComponent.positionVector.x = renderX;
        positionComponent.positionVector.y = renderY;
        statBar.add(positionComponent);

        engine.addEntity(statBar);

        return statBar;
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
                activityComponent.timeChange = 1;
                break;
            case REC:
                activityComponent.energyChange = -10;
                activityComponent.studyChange = 0;
                activityComponent.timeChange = 1;
                break;
            case SLEEP:
                activityComponent.energyChange = 0;
                activityComponent.studyChange = 0;
                activityComponent.timeChange = 0;
                break;
            case STUDY:
                activityComponent.energyChange = -10;
                activityComponent.studyChange = 10;
                activityComponent.timeChange = 1;
                break;
        }
        building.add(activityComponent);

        GameObjectComponent gameObjectComponent = engine.createComponent(GameObjectComponent.class);
        gameObjectComponent.type = ObjectType.BUILDING;
        building.add(gameObjectComponent);

        engine.addEntity(building);

        return building;
    }

    private Entity createPlayer(int spawnX, int spawnY, int width, int height) {
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
        playerTexture.width = width;
        playerTexture.height = height;

        player.add(playerTexture);

        CollisionComponent collisionComponent = engine.createComponent(CollisionComponent.class);
        collisionComponent.collisionRectangle.x = spawnX;
        collisionComponent.collisionRectangle.y = spawnY;
        collisionComponent.collisionRectangle.width = width;
        collisionComponent.collisionRectangle.height = height;
        player.add(collisionComponent);

        PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);
        //playerComponent.activityCount = new int[ActivityType.values().length];
        playerComponent.activityCount = new EnumMap<>(ActivityType.class);
        for(ActivityType type : ActivityType.values()) {
            playerComponent.activityCount.put(type, 0);
        }

        playerComponent.energy = 100;
        playerComponent.study = 0;
        playerComponent.currentActivity = null;
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

        float playerXRenderPosition = camera.viewportWidth / 2.0f - playerTexture.width / 2.0f;
        float playerYRenderPosition = camera.viewportHeight / 2.0f - playerTexture.height / 2.0f;
        //float xRenderPosition = camera.viewportWidth / 2;
        //float yRenderPosition = camera.viewportHeight / 2;

        //float cameraXCenter = camera.position.x - camera.viewportWidth / 2;
        /*
        if (playerPosition.positionVector.x < map.getCameraBorder().x + camera.viewportWidth / 2) {
            xRenderPosition -= map.getCameraBorder().x
                    + camera.viewportWidth / 2
                    - playerPosition.positionVector.x
                    - playerTexture.width / 2.0f;
        }
        if (playerPosition.positionVector.x > map.getCameraBorder().width - camera.viewportWidth / 2) {
            xRenderPosition += playerPosition.positionVector.x
                    - (map.getCameraBorder().width - camera.viewportWidth / 2)
                    + playerTexture.width / 2.0f;
        }
        if (playerPosition.positionVector.y < map.getCameraBorder().x + camera.viewportHeight / 2) {
            yRenderPosition -= map.getCameraBorder().x
                    + camera.viewportHeight / 2
                    - playerPosition.positionVector.y
                    - playerTexture.width / 2.0f;
        }
        if (playerPosition.positionVector.y > map.getCameraBorder().height - camera.viewportHeight / 2) {
            yRenderPosition += playerPosition.positionVector.y
                    - (map.getCameraBorder().height - camera.viewportHeight / 2)
                    + playerTexture.width / 2.0f;
        }


         */

        PlayerCameraSystem playerCameraSystem = engine.getSystem(PlayerCameraSystem.class);

        if (playerCameraSystem.isCameraAtLeftBorder()) {
            float cameraCentreX = map.getCameraBorder().x + camera.viewportWidth / 2.0f;

            float playerXDifference = cameraCentreX - playerPosition.positionVector.x;

            playerXRenderPosition = cameraCentreX - playerXDifference;
        }
        if (playerCameraSystem.isCameraAtRightBorder()) {
            float cameraCentreX = map.getCameraBorder().x + map.getCameraBorder().width - camera.viewportWidth / 2.0f;

            float playerXDifference = playerPosition.positionVector.x - cameraCentreX;

            playerXRenderPosition = playerXRenderPosition + playerXDifference + playerTexture.width / 2.0f;
        }

        if (playerCameraSystem.isCameraAtBottomBorder()) {
            float cameraCentreY = map.getCameraBorder().y + camera.viewportHeight / 2.0f;

            float playerYDifference = cameraCentreY - playerPosition.positionVector.y;

            playerYRenderPosition = cameraCentreY - playerYDifference;
        }
        if (playerCameraSystem.isCameraAtTopBorder()) {
            float cameraCentreY = map.getCameraBorder().y + map.getCameraBorder().height - camera.viewportHeight / 2.0f;

            float playerYDifference = playerPosition.positionVector.y - cameraCentreY;

            playerYRenderPosition = playerYRenderPosition + playerYDifference + playerTexture.height / 2.0f;
        }


        //System.out.println(camera.position.x + map.getCameraBorder().width - camera.viewportWidth / 2);
        //System.out.println(playerPosition.positionVector.x);


        energyBar.getComponent(StatBarComponent.class).progress = player.getComponent(PlayerComponent.class).energy / 100f;
        timeUI.getComponent(UITimeComponent.class).currentHour = engine.getSystem(GameSystem.class).getHour() + 9;
        dayCounter.getComponent(UIDayComponent.class).currentDay = engine.getSystem(GameSystem.class).getDay();

        activityCounter.getComponent(UIActivityCountComponent.class).sleepCount = player.getComponent(PlayerComponent.class).activityCount.get(ActivityType.SLEEP);
        activityCounter.getComponent(UIActivityCountComponent.class).studyCount = player.getComponent(PlayerComponent.class).activityCount.get(ActivityType.STUDY);
        activityCounter.getComponent(UIActivityCountComponent.class).eatCount = player.getComponent(PlayerComponent.class).activityCount.get(ActivityType.EAT);
        activityCounter.getComponent(UIActivityCountComponent.class).recCount = player.getComponent(PlayerComponent.class).activityCount.get(ActivityType.REC);


        //energyBar.getComponent(TextureComponent.class).width++;
        //energyBar.getComponent(TextureComponent.class).height++;

        batch.begin();
        batch.draw(playerTexture.texture, playerXRenderPosition, playerYRenderPosition, playerTexture.srcStartX, playerTexture.srcStartY,
                   playerTexture.width, playerTexture.height);
        //batch.draw(statsTexure, 10, 10, 200, 200, 0, 0, 109, 56, false, false);
        engine.getSystem(UIRenderSystem.class).render(batch);
        batch.end();

        engine.update(v);

        if (engine.getSystem(GameSystem.class).isGameComplete()) {
            game.switchToGameOverScreen();
        }
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        game.getAudioManager().stopMusic();
    }

    @Override
    public void dispose() {

    }
}
