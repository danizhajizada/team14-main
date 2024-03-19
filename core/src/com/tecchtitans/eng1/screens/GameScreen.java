package com.tecchtitans.eng1.screens;

import com.badlogic.gdx.Screen;
import com.tecchtitans.eng1.ENGGame;
import com.tecchtitans.eng1.Map;

public abstract class GameScreen implements Screen {
    ENGGame game;
    Map map;

    // could add hide method to stop music for every screen when hidden.
}
