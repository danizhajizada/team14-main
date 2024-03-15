package com.tecchtitans.eng1;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputComponent implements Component {
    public int keysPressed = 0b0000;
}
