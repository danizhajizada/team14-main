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

public class PlayScreen implements Screen {
    ENGGame game;
    Map map;
    SpriteBatch batch;

    Entity player;

    public PlayScreen(ENGGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        map = new Map("testmap3.tmx", 1024, 1024);

        batch = new SpriteBatch();

        player = createPlayer(0, 0);
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
        batch.draw(playerTexture.texture, playerPosition.x, playerPosition.y, playerTexture.srcStartX, playerTexture.srcStartY,
                playerTexture.width, playerTexture.height);
        batch.end();

        game.getEngine().update(v);
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

    }

    @Override
    public void dispose() {

    }
}
