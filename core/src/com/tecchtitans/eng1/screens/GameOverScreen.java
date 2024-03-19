package com.tecchtitans.eng1.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.tecchtitans.eng1.ENGGame;
import com.tecchtitans.eng1.Map;

public class GameOverScreen extends GameScreen {

    public GameOverScreen(ENGGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        map = new Map("gameover.tmx", 1280, 960);

        //game.getAudioManager().playSound("audio/snoring.mp3");
        game.getAudioManager().playMusic("audio/snoring.mp3");
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);

        map.render();

        if (Gdx.input.justTouched()) {
            MapLayer buttonLayer = map.getTiledMap().getLayers().get("button");

            if (buttonLayer != null) {
                Array<RectangleMapObject> buttons = buttonLayer.getObjects().getByType(RectangleMapObject.class);

                Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                Vector3 relMouseLoc = map.getCamera().unproject(touch);

                for (RectangleMapObject button : buttons) {
                    if (button.getRectangle().contains(relMouseLoc.x, relMouseLoc.y)) {
                        if (button.getName().equals("playAgain")) {
                            game.reset();
                            game.switchToPlayScreen();
                        }
                    }
                }
            }
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

    }

    @Override
    public void dispose() {

    }
}
