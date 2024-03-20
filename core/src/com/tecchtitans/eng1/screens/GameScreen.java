package com.tecchtitans.eng1.screens;

import com.badlogic.gdx.Screen;
import com.tecchtitans.eng1.ENGGame;
import com.tecchtitans.eng1.Map;

/**
 * Represents the base class for any game screen implementation. Includes necessary private fields
 * for game screen logic.
 */
public abstract class GameScreen implements Screen {
    /**
     * The game instance that the screen will be displaying information about.
     */
    ENGGame game;
    /**
     * The map to be displayed upon the screen.
     */
    Map map;

    // could add hide method to stop music for every screen when hidden.

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    /**
     * Runs when the screen is hidden. Ensures that the currently playing background
     * music for this screen is stopped.
     */
    @Override
    public void hide() {
        game.getAudioManager().stopMusic();
    }

    @Override
    public void dispose() {

    }
}
