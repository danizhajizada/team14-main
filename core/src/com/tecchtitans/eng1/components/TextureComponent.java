package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

public class TextureComponent implements Component {
    public Texture texture;
    public int srcStartX, srcStartY;
    public int width, height;
}
