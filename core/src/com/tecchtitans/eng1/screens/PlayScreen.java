package com.tecchtitans.eng1.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.tecchtitans.eng1.*;
import com.tecchtitans.eng1.components.*;
import com.badlogic.ashley.core.Entity;
import com.tecchtitans.eng1.systems.GameSystem;
import com.tecchtitans.eng1.systems.PlayerCameraSystem;
import com.tecchtitans.eng1.systems.PlayerMovementSystem;
import com.tecchtitans.eng1.systems.UIRenderSystem;
import java.util.ArrayList;

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

    /**
     * Runs when the screen is first shown. Initialises map and systems.
     */
    @Override
    public void show() {
        map = new Map("mainmap5.tmx", 4800, 3200);

        batch = new SpriteBatch();

        player = engine.createPlayer((int)map.getPlayerSpawnPoint().x , (int)map.getPlayerSpawnPoint().y, 26, 44);

        // Find building objects from the map, then create entities for them and add to the engine.
        buildings = new ArrayList<>();
        for(RectangleMapObject building : map.getBuildingObjects()) {
            buildings.add(engine.createBuilding(building));
        }

        // Update relevant systems with the map that has just been loaded.
        engine.getSystem(PlayerMovementSystem.class).updateMap(map);
        engine.getSystem(PlayerCameraSystem.class).updateCamera(map.getCamera());
        engine.getSystem(PlayerCameraSystem.class).updateCameraBorder(map.getCameraBorder());

        map.getCamera().setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        game.getAudioManager().playMusic("audio/bgmusic.mp3");

        // Create UI entities
        energyBar = engine.createStatBar(50, Gdx.graphics.getHeight() - 100, 250, 50);
        timeUI = engine.createUIClock(350, Gdx.graphics.getHeight() - 100, 150, 50);
        dayCounter = engine.createUIDayCounter(550, Gdx.graphics.getHeight() - 100, 150, 50);
        activityCounter = engine.createUIActivityCounter(50, 50, 100, 100);
    }

    /**
     * Renders the map, the player and UI entities on the screen.
     * @param v Time since last frame. (deltaTime)
     */
    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);

        // Render map
        map.render();

        // Draw player
        TextureComponent playerTexture = player.getComponent(TextureComponent.class);
        PositionComponent playerPosition = player.getComponent(PositionComponent.class);

        Camera camera = map.getCamera();

        float playerXRenderPosition = camera.viewportWidth / 2.0f - playerTexture.width / 2.0f;
        float playerYRenderPosition = camera.viewportHeight / 2.0f - playerTexture.height / 2.0f;

        PlayerCameraSystem playerCameraSystem = engine.getSystem(PlayerCameraSystem.class);

        // If the player extends the camera border
        // then calculate offsets and render player location accordingly
        if (playerCameraSystem.isCameraAtLeftBorder()) {
            float cameraCentreX = camera.viewportWidth / 2.0f;

            float playerXDifference = map.getCameraBorder().x + cameraCentreX - playerPosition.positionVector.x;

            playerXRenderPosition = cameraCentreX - playerXDifference;
        }
        if (playerCameraSystem.isCameraAtRightBorder()) {
            float cameraCentreX = map.getCameraBorder().x + map.getCameraBorder().width - camera.viewportWidth / 2.0f;

            float playerXDifference = playerPosition.positionVector.x - cameraCentreX;

            playerXRenderPosition = playerXRenderPosition + playerXDifference + playerTexture.width / 2.0f;
        }

        if (playerCameraSystem.isCameraAtBottomBorder()) {
            float cameraCentreY = camera.viewportHeight / 2.0f;

            float playerYDifference = map.getCameraBorder().y + cameraCentreY - playerPosition.positionVector.y;

            playerYRenderPosition = cameraCentreY - playerYDifference;
        }
        if (playerCameraSystem.isCameraAtTopBorder()) {
            float cameraCentreY = map.getCameraBorder().y + map.getCameraBorder().height - camera.viewportHeight / 2.0f;

            float playerYDifference = playerPosition.positionVector.y - cameraCentreY;

            playerYRenderPosition = playerYRenderPosition + playerYDifference + playerTexture.height / 2.0f;
        }

        // Set UI entity values
        energyBar.getComponent(StatBarComponent.class).progress = player.getComponent(PlayerComponent.class).energy / 100f;
        timeUI.getComponent(UITimeComponent.class).currentHour = engine.getSystem(GameSystem.class).getHour() + 9;
        dayCounter.getComponent(UIDayComponent.class).currentDay = engine.getSystem(GameSystem.class).getDay();

        activityCounter.getComponent(UIActivityCountComponent.class).sleepCount = player.getComponent(PlayerComponent.class).activityCount.get(ActivityType.SLEEP);
        activityCounter.getComponent(UIActivityCountComponent.class).studyCount = player.getComponent(PlayerComponent.class).activityCount.get(ActivityType.STUDY);
        activityCounter.getComponent(UIActivityCountComponent.class).eatCount = player.getComponent(PlayerComponent.class).activityCount.get(ActivityType.EAT);
        activityCounter.getComponent(UIActivityCountComponent.class).recCount = player.getComponent(PlayerComponent.class).activityCount.get(ActivityType.REC);

        // Render player and UI components
        batch.begin();
        batch.draw(playerTexture.texture, playerXRenderPosition, playerYRenderPosition, playerTexture.srcStartX, playerTexture.srcStartY,
                   playerTexture.width, playerTexture.height);
        engine.getSystem(UIRenderSystem.class).render(batch);
        batch.end();

        engine.update(v);

        if (engine.getSystem(GameSystem.class).isGameComplete()) {
            game.switchToGameOverScreen();
        }
    }
}
