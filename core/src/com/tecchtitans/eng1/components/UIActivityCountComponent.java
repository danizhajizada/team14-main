package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;

public class UIActivityCountComponent implements Component {
    public int outerPartSrcX, outerPartSrcY;
    public int outerPartSrcWidth, outerPartSrcHeight;
    public int numbersSrcX, numbersSrcY;
    public int numbersSrcWidth, numbersSrcHeight;
    public int sleepNumberXOffset, sleepNumberYOffset;
    public int studyNumberXOffset, studyNumberYOffset;
    public int eatNumberXOffset, eatNumberYOffset;
    public int recNumberXOffset, recNumberYOffset;

    public int sleepCount = 0;
    public int studyCount = 0;
    public int eatCount = 0;
    public int recCount = 0;
}
