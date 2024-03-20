package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;

/**
 * Stores relevant information about statistic bars.
 * Outer refers to the casing of the statistic
 */
public class StatBarComponent implements Component {
    /**
     * Stores the starting X pixel for the outer
     */
    public int outerPartSrcX;
    public int outerPartSrcY;
    public int outerPartSrcWidth, outerPartSrcHeight;
    public int innerPartSrcX, innerPartSrcY;
    public int innerPartSrcWidth, innerPartSrcHeight;
    public int innerPartXOffset, innerPartYOffset;

    // A number from 0 to 1.
    public float progress;
}
