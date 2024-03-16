package com.tecchtitans.eng1.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.tecchtitans.eng1.*;
import com.tecchtitans.eng1.components.*;
import com.badlogic.ashley.core.Entity;
import com.tecchtitans.eng1.components.GameObjectComponent.ObjectType;
import com.tecchtitans.eng1.systems.PlayerCameraSystem;
import com.tecchtitans.eng1.systems.PlayerCollisionSystem;
import com.tecchtitans.eng1.systems.PlayerMovementSystem;

public class PlayScreen implements Screen {
    ENGGame game;
    Map map;
    SpriteBatch batch;

    Entity player;

    float xRatio;
    float yRatio;

    public PlayScreen(ENGGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        map = new Map("testmap3.tmx", 1024, 1024);

        batch = new SpriteBatch();

        player = createPlayer(100, 100);

        game.getEngine().getSystem(PlayerMovementSystem.class).updateMap(map);
        game.getEngine().getSystem(PlayerCameraSystem.class).updateCamera(map.getCamera());

        game.getEngine().getSystem(PlayerCameraSystem.class).updateCameraBorder(map.getCameraBorder());

        map.getCamera().setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //building = createBuilding(100, 100, 50, 50);

        xRatio =  (float)Gdx.graphics.getWidth() / (float)map.getWidth();
        yRatio =  (float)Gdx.graphics.getHeight() / (float)map.getHeight();
    }

    private Entity createBuilding(int spawnX, int spawnY, int width, int height) {
        Entity building = new Entity();

        building.add(new PositionComponent());

        CollisionComponent collisionComponent = new CollisionComponent();
        collisionComponent.collisionRectangle.x = spawnX;
        collisionComponent.collisionRectangle.y = spawnY;
        collisionComponent.collisionRectangle.width = width;
        collisionComponent.collisionRectangle.height = height;
        building.add(collisionComponent);

        GameObjectComponent gameObjectComponent = new GameObjectComponent();
        gameObjectComponent.type = ObjectType.BUILDING;
        building.add(gameObjectComponent);

        game.getEngine().addEntity(building);

        return building;
    }

    private Entity createPlayer(int spawnX, int spawnY) {
        Entity player = new Entity();

        player.add(new PlayerComponent());

        PositionComponent positionComponent = new PositionComponent();
        positionComponent.x = spawnX;
        positionComponent.y = spawnY;
        player.add(positionComponent);

        player.add(new VelocityComponent());
        player.add(new InputComponent());

        TextureComponent playerTexture = new TextureComponent();
        playerTexture.texture = new Texture("spacesoldier.png");
        playerTexture.srcStartX = 8;
        playerTexture.srcStartY = 11;
        playerTexture.width = 50;
        playerTexture.height = 50;

        player.add(playerTexture);

        CollisionComponent collisionComponent = new CollisionComponent();
        collisionComponent.collisionRectangle.x = spawnX;
        collisionComponent.collisionRectangle.y = spawnY;
        collisionComponent.collisionRectangle.width = 50;
        collisionComponent.collisionRectangle.height = 50;
        player.add(collisionComponent);

        game.getEngine().addEntity(player);

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

        batch.begin();
        batch.draw(playerTexture.texture, playerPosition.x * xRatio, playerPosition.y * yRatio, playerTexture.srcStartX, playerTexture.srcStartY,
                (int)(playerTexture.width * xRatio), (int)(playerTexture.height * yRatio));
        batch.end();

        game.getEngine().update(v);
    }

    @Override
    public void resize(int i, int i1) {
        if (i < map.getWidth() && i1 < map.getHeight()) {
            map.getCamera().setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
