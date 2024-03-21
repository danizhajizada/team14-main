package com.tecchtitans.eng1.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.tecchtitans.eng1.ENGGame;
import com.tecchtitans.eng1.Map;

/**
 * Initial main menu screen that can be used to exit the game or start a new game.
 */
public class MainMenu extends GameScreen {
    /**
     * Initialises the main menu screen with a reference to the current game instance.
     * @param game - Currently running game instance.
     */
    public MainMenu(ENGGame game) {
        this.game = game;
    }

    /**
     * Code that runs upon the map being initially shown. Sets the map to be shown
     * and any potential game audio if desired.
     */
    @Override
    public void show() {
        map = new Map("mainmenu_final.tmx", 2288, 1200);
    }

    /**
     * Renders the main menu map on the screen. Checks for user input on the screen's button
     * and performs the correct operation based upon the button pressed.
     * @param v - Time passed since last frame in seconds. (delta time)
     */
    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);

        map.render();

        MapLayer buttonLayer = map.getTiledMap().getLayers().get("buttons");

        Array<RectangleMapObject> buttons = buttonLayer.getObjects().getByType(RectangleMapObject.class);

        if (Gdx.input.justTouched()) {
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            Vector3 relMouseLoc = map.getCamera().unproject(touch);

            for (RectangleMapObject button : buttons) {
                if (button.getRectangle().contains(relMouseLoc.x, relMouseLoc.y)) {
                    switch (button.getName()) {
                        case "start":
                            game.switchToPlayScreen();
                            break;
                        case "instructions":
                            break;
                        case "exit":
                            Gdx.app.exit();
                            break;
                        default:
                            break;
                    }

                }
            }
        }
    }
}
