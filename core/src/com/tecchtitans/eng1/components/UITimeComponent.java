package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;

/**
 * Stores relevant information about the UI time display.
 */
public class UITimeComponent implements Component {
    /**
     * Stores the starting X pixel in the asset image for the outer part.
     */
    public int outerPartSrcX;
    /**
     * Stores the starting Y pixel in the asset image for the outer part.
     */
    public int outerPartSrcY;

    /**
     * Stores the width of the asset image in number of pixels for the outer part.
     */
    public int outerPartSrcWidth;
    /**
     * Stores the height of the asset image in number of pixels for the outer part.
     */
    public int outerPartSrcHeight;

    /**
     * Stores the starting X pixel in the asset image for the numbers to be displayed.
     */
    public int numbersSrcX;
    /**
     * Stores the starting Y pixel in the asset image for the numbers to be displayed.
     */
    public int numbersSrcY;

    /**
     * Stores the width of each number that is to be displayed in pixels.
     */
    public int numbersSrcWidth;
    /**
     * Stores the height of each number that is to be displayed in pixels.
     */
    public int numbersSrcHeight;

    /**
     * Stores the starting X pixel in the asset image for the am image to be displayed.
     */
    public int amSrcX;
    /**
     * Stores the starting Y pixel in the asset image for the am image to be displayed.
     */
    public int amSrcY;

    /**
     * Stores the width of the asset for the am image in pixels.
     */
    public int amSrcWidth;
    /**
     * Stores the height of the asset for the am image in pixels.
     */
    public int amSrcHeight;

    /**
     * Stores the starting X pixel in the asset image for the pm image to be displayed.
     */
    public int pmSrcX;
    /**
     * Stores the starting Y pixel in the asset image for the pm image to be displayed.
     */
    public int pmSrcY;

    /**
     * Stores the width of the asset for the pm image in pixels.
     */
    public int pmSrcWidth;
    /**
     * Stores the height of the asset for the pm image in pixels.
     */
    public int pmSrcHeight;

    /**
     * Stores the X offset where the numbers should be displayed compared to the outer part in pixels.
     */
    public int numbersXOffset;
    /**
     * Stores the Y offset where the numbers should be displayed compared to the outer part in pixels.
     */
    public int numbersYOffset;

    /**
     * Stores the current hour to be displayed.
     */
    public int currentHour = 0;
}
