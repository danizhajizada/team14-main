package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;

public class StatBarComponent implements Component {

    /*
    This component stores values for drawing the stat bar.
    Outer refers to the "casing" of the bar and inner refers to the actual bar itself.
     */

    public int outerPartSrcX, outerPartSrcY;
    public int outerPartSrcWidth, outerPartSrcHeight;
    public int innerPartSrcX, innerPartSrcY;
    public int innerPartSrcWidth, innerPartSrcHeight;
    public int intersectSrcX, intersectSrcY;

    // A number from 0 to 1
    public float progress;
}
