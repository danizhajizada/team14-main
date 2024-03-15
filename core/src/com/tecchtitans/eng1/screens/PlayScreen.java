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
import com.badlogic.ashley.core.Entity;

public class PlayScreen implements Screen {
    ENGGame game;
    TiledMap map;
    OrthographicCamera camera;
    OrthogonalTiledMapRenderer renderer;
    SpriteBatch batch;

    Entity player;

    public PlayScreen(ENGGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 1024);

        map = new TmxMapLoader().load("testmap3.tmx");

        renderer = new OrthogonalTiledMapRenderer(map);

        batch = new SpriteBatch();

        player = createPlayer();
    }

    private Entity createPlayer() {
        Entity player = new Entity();

        player.add(new PlayerComponent());
        player.add(new PositionComponent());
        player.add(new VelocityComponent());
        player.add(new InputComponent());
        player.add(new TextureComponent("spacesoldier.png"));

        game.getEngine().addEntity(player);

        return player;
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);

        camera.update();
        renderer.setView(camera);
        renderer.render();

        // Draw player
        Texture playerTexture = player.getComponent(TextureComponent.class).texture;
        PositionComponent playerPosition = player.getComponent(PositionComponent.class);

        batch.begin();
        batch.draw(playerTexture, playerPosition.x, playerPosition.y, 8, 11, 50, 50);
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
