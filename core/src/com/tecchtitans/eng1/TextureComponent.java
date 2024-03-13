package com.tecchtitans.eng1;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Null;

public class TextureComponent implements Component {
    public Texture texture;

    public TextureComponent(String path) {
        texture = new Texture(path);
    }
}
