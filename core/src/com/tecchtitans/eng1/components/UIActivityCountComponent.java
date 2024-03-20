package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;

/**
 * A component that stores relevant information about
 * the UI activity count.
 *
 * Outer and Inner refer to the two parts of the UI entity to be rendered.
 */
public class UIActivityCountComponent implements Component {
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
     * Stores the X offset where the sleep numbers should be displayed compared to the outer part in pixels.
     */
    public int sleepNumberXOffset;
    /**
     * Stores the Y offset where the sleep numbers should be displayed compared to the outer part in pixels.
     */
    public int sleepNumberYOffset;

    /**
     * Stores the X offset where the study numbers should be displayed compared to the outer part in pixels.
     */
    public int studyNumberXOffset;
    /**
     * Stores the Y offset where the study numbers should be displayed compared to the outer part in pixels.
     */
    public int studyNumberYOffset;

    /**
     * Stores the X offset where the eat numbers should be displayed compared to the outer part in pixels.
     */
    public int eatNumberXOffset;
    /**
     * Stores the Y offset where the eat numbers should be displayed compared to the outer part in pixels.
     */
    public int eatNumberYOffset;

    /**
     * Stores the X offset where the recreational numbers should be displayed compared to the outer part in pixels.
     */
    public int recNumberXOffset;
    /**
     * Stores the Y offset where the recreational numbers should be displayed compared to the outer part in pixels.
     */
    public int recNumberYOffset;

    /**
     * Stores the number that should be displayed for sleep.
     */
    public int sleepCount = 0;
    /**
     * Stores the number that should be displayed for sleep.
     */
    public int studyCount = 0;
    /**
     * Stores the number that should be displayed for eat.
     */
    public int eatCount = 0;
    /**
     * Stores the number that should be displayed for recreational.
     */
    public int recCount = 0;
}
