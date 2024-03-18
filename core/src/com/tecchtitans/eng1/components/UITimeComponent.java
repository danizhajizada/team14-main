package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;

public class UITimeComponent implements Component {
    public int outerPartSrcX, outerPartSrcY;
    public int outerPartSrcWidth, outerPartSrcHeight;
    public int numbersSrcX, numbersSrcY;
    public int numbersSrcWidth, numbersSrcHeight;
    public int amSrcX, amSrcY;
    public int amSrcWidth, amSrcHeight;
    public int pmSrcX, pmSrcY;
    public int pmSrcWidth, pmSrcHeight;
    public int numbersXOffset, numbersYOffset;
    public int currentHour = 0;
}
