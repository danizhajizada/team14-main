package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

/**
 * Stores relevant information about an entities texture.
 */
public class TextureComponent implements Component {
    /**
     * The texture to be rendered.
     */
    public Texture texture;

    /**
     * Stores the starting X pixel for the texture asset image.
     */
    public int srcStartX;
    /**
     * Stores the starting Y pixel for the texture asset image.
     */
    public int srcStartY;
    /**
     * Stores the width in pixels of the texture asset image.
     */
    public int width;
    /**
     * Stores the height in pixels of the texture asset image.
     */
    public int height;
}
