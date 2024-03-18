package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;

public class UIDayComponent implements Component {
    public int outerPartSrcX, outerPartSrcY;
    public int outerPartSrcWidth, outerPartSrcHeight;
    public int numbersSrcX, numbersSrcY;
    public int numbersSrcWidth, numbersSrcHeight;
    public int numberXOffset, numberYOffset;
    public int currentDay = 0;
}
