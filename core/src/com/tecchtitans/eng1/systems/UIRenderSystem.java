package com.tecchtitans.eng1.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.tecchtitans.eng1.components.*;

public class UIRenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    public UIRenderSystem() {};

    public void addedToEngine(Engine engine)
    {
        entities = engine.getEntitiesFor(Family.all(TextureComponent.class, UIComponent.class, PositionComponent.class).get());
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);

            UIComponent uiComponent = ComponentMappers.ui.get(entity);
            TextureComponent textureComponent = ComponentMappers.texture.get(entity);
            PositionComponent positionComponent = ComponentMappers.position.get(entity);
            Texture texture = textureComponent.texture;

            int renderLocationX = (int)positionComponent.positionVector.x;
            int renderLocationY = (int)positionComponent.positionVector.y;

            int renderWidth = textureComponent.width;
            int renderHeight = textureComponent.height;

            if (uiComponent.type == UIComponentType.STATBAR) {
                StatBarComponent statBarComponent = ComponentMappers.statBar.get(entity);
                /*
                float outerWidthRatio = renderWidth / (float)statBarComponent.outerPartSrcWidth;
                float outerHeightRatio = renderHeight / (float)statBarComponent.outerPartSrcHeight;

                int innerSrcWidth = (int)(statBarComponent.innerPartSrcWidth * statBarComponent.progress);
                int innerRenderWidth = (int)(renderWidth * statBarComponent.progress);

                batch.draw(texture, positionComponent.positionVector.x, positionComponent.positionVector.y,
                        renderWidth, renderHeight, statBarComponent.outerPartSrcX, statBarComponent.outerPartSrcY,
                        statBarComponent.outerPartSrcWidth, statBarComponent.outerPartSrcHeight,
                        false, false);

                batch.draw(texture, positionComponent.positionVector.x + statBarComponent.innerPartXOffset,
                        positionComponent.positionVector.y + statBarComponent.innerPartYOffset, innerRenderWidth * outerWidthRatio - statBarComponent.innerPartXOffset - 1, statBarComponent.innerPartSrcHeight,
                        statBarComponent.innerPartSrcX, statBarComponent.innerPartSrcY,
                        innerSrcWidth, statBarComponent.innerPartSrcHeight,
                        false, false);



                 */



                int outerSrcX = statBarComponent.outerPartSrcX;
                int outerSrcY = statBarComponent.outerPartSrcY;

                int outerSrcWidth = statBarComponent.outerPartSrcWidth;
                int outerSrcHeight = statBarComponent.outerPartSrcHeight;

                // Draw outer part of bar.
                batch.draw(texture, renderLocationX, renderLocationY, renderWidth, renderHeight, outerSrcX, outerSrcY, outerSrcWidth, outerSrcHeight, false, false);

                float progress = statBarComponent.progress;

                float totalWidthRatio = textureComponent.width / (float)outerSrcWidth;
                float totalHeightRatio = textureComponent.height / (float)outerSrcHeight;

                int innerSrcX = statBarComponent.innerPartSrcX;
                int innerSrcY = statBarComponent.innerPartSrcY;

                int innerXOffset = Math.round(statBarComponent.innerPartXOffset * totalWidthRatio);
                int innerYOffset = Math.round(statBarComponent.innerPartYOffset * totalHeightRatio);

                int innerSrcWidth = statBarComponent.innerPartSrcWidth;
                int innerSrcHeight = statBarComponent.innerPartSrcHeight;

                int innerRenderWidth = Math.round(innerSrcWidth * totalWidthRatio * progress);
                int innerRenderHeight = Math.round(innerSrcHeight * totalHeightRatio);

                // Draw inner bar
                batch.draw(texture, renderLocationX + innerXOffset, renderLocationY + innerYOffset, innerRenderWidth, innerRenderHeight, innerSrcX, innerSrcY, innerSrcWidth, innerSrcHeight, false, false);
            } else if (uiComponent.type == UIComponentType.TIME) {
                UITimeComponent uiTimeComponent = ComponentMappers.time.get(entity);



                int outerSrcX = uiTimeComponent.outerPartSrcX;
                int outerSrcY = uiTimeComponent.outerPartSrcY;

                int outerSrcWidth = uiTimeComponent.outerPartSrcWidth;
                int outerSrcHeight = uiTimeComponent.outerPartSrcHeight;

                // Draw outer part of bar.
                batch.draw(texture, renderLocationX, renderLocationY, renderWidth, renderHeight, outerSrcX, outerSrcY, outerSrcWidth, outerSrcHeight, false, false);

                int currentHour = uiTimeComponent.currentHour;
                int hourFirstNum = currentHour / 10;
                int hourSecondNum = currentHour % 10;

                float totalWidthRatio = textureComponent.width / (float)outerSrcWidth;
                float totalHeightRatio = textureComponent.height / (float)outerSrcHeight;

                int numbersSrcWidth = uiTimeComponent.numbersSrcWidth;
                int numbersSrcHeight = uiTimeComponent.numbersSrcHeight;

                int firstNumberSrcX = uiTimeComponent.numbersSrcX + ((numbersSrcWidth + 1) * hourFirstNum);
                int firstNumberSrcY = uiTimeComponent.numbersSrcY;

                int numbersXOffset = Math.round(uiTimeComponent.numbersXOffset * totalWidthRatio);
                int numbersYOffset = Math.round(uiTimeComponent.numbersYOffset * totalHeightRatio);

                int numberRenderWidth = Math.round(numbersSrcWidth * totalWidthRatio);
                int numberRenderHeight = Math.round(numbersSrcHeight * totalHeightRatio);

                // Draw first number
                batch.draw(texture, renderLocationX + numbersXOffset, renderLocationY + numbersYOffset, numberRenderWidth, numberRenderHeight, firstNumberSrcX, firstNumberSrcY, numbersSrcWidth, numbersSrcHeight, false, false);

                int secondNumberSrcX = uiTimeComponent.numbersSrcX + ((numbersSrcWidth + 1) * hourSecondNum);
                int secondNumberSrcY = uiTimeComponent.numbersSrcY;

                int secondNumberXOffset = Math.round((uiTimeComponent.numbersXOffset + numbersSrcWidth + 1) * totalWidthRatio);

                // Draw second number
                batch.draw(texture, renderLocationX + secondNumberXOffset, renderLocationY + numbersYOffset, numberRenderWidth, numberRenderHeight, secondNumberSrcX, secondNumberSrcY, numbersSrcWidth, numbersSrcHeight, false, false);

                int ampmXOffset = Math.round((uiTimeComponent.numbersXOffset + (numbersSrcWidth + 1) * 2) * totalWidthRatio);

                if (currentHour < 12) {
                    int amRenderWidth = Math.round(uiTimeComponent.amSrcWidth * totalWidthRatio);
                    int amRenderHeight = Math.round(uiTimeComponent.amSrcHeight * totalHeightRatio);

                    int amSrcX = uiTimeComponent.amSrcX;
                    int amSrcY = uiTimeComponent.amSrcY;

                    int amSrcWidth = uiTimeComponent.amSrcWidth;
                    int amSrcHeight = uiTimeComponent.amSrcHeight;

                    //Draw am
                    batch.draw(texture, renderLocationX + ampmXOffset, renderLocationY + numbersYOffset, amRenderWidth, amRenderHeight, amSrcX, amSrcY, amSrcWidth, amSrcHeight, false, false);
                } else {
                    int pmRenderWidth = Math.round(uiTimeComponent.pmSrcWidth * totalWidthRatio);
                    int pmRenderHeight = Math.round(uiTimeComponent.pmSrcHeight * totalHeightRatio);

                    int pmSrcX = uiTimeComponent.pmSrcX;
                    int pmSrcY = uiTimeComponent.pmSrcY;

                    int pmSrcWidth = uiTimeComponent.pmSrcWidth;
                    int pmSrcHeight = uiTimeComponent.pmSrcHeight;

                    //Draw pm
                    batch.draw(texture, renderLocationX + ampmXOffset, renderLocationY + numbersYOffset, pmRenderWidth, pmRenderHeight, pmSrcX, pmSrcY, pmSrcWidth, pmSrcHeight, false, false);
                }
            } else if (uiComponent.type == UIComponentType.DAY) {
                UIDayComponent uiDayComponent = ComponentMappers.day.get(entity);

                int currentDay = uiDayComponent.currentDay;

                int outerSrcX = uiDayComponent.outerPartSrcX;
                int outerSrcY = uiDayComponent.outerPartSrcY;

                int outerSrcWidth = uiDayComponent.outerPartSrcWidth;
                int outerSrcHeight = uiDayComponent.outerPartSrcHeight;

                int numbersSrcX = uiDayComponent.numbersSrcX;
                int numbersSrcY = uiDayComponent.numbersSrcY;

                int numbersSrcWidth = uiDayComponent.numbersSrcWidth;
                int numbersSrcHeight = uiDayComponent.numbersSrcHeight;

                float totalWidthRatio = textureComponent.width / (float)outerSrcWidth;
                float totalHeightRatio = textureComponent.height / (float)outerSrcHeight;

                int numberXOffset = Math.round(uiDayComponent.numberXOffset * totalWidthRatio);
                int numberYOffset = Math.round(uiDayComponent.numberYOffset * totalHeightRatio);

                // Draw outer
                batch.draw(texture,
                        renderLocationX,
                        renderLocationY,
                        renderWidth,
                        renderHeight,
                        outerSrcX,
                        outerSrcY,
                        outerSrcWidth,
                        outerSrcHeight,
                        false,
                        false
                );

                int numberRenderWidth = Math.round(numbersSrcWidth * totalWidthRatio);
                int numberRenderHeight = Math.round(numbersSrcHeight * totalHeightRatio);

                int numberSrcX = uiDayComponent.numbersSrcX + ((numbersSrcWidth + 1) * currentDay);

                // Draw number
                batch.draw(
                        texture,
                        renderLocationX + numberXOffset,
                        renderLocationY + numberYOffset,
                        numberRenderWidth,
                        numberRenderHeight,
                        numberSrcX,
                        numbersSrcY,
                        numbersSrcWidth,
                        numbersSrcHeight,
                        false,
                        false
                );
            } else if (uiComponent.type == UIComponentType.ACTCOUNT) {
                UIActivityCountComponent uiActivityCountComponent = ComponentMappers.activityCount.get(entity);

                int outerSrcX = uiActivityCountComponent.outerPartSrcX;
                int outerSrcY = uiActivityCountComponent.outerPartSrcY;

                int outerSrcWidth = uiActivityCountComponent.outerPartSrcWidth;
                int outerSrcHeight = uiActivityCountComponent.outerPartSrcHeight;

                float totalWidthRatio = textureComponent.width / (float)outerSrcWidth;
                float totalHeightRatio = textureComponent.height / (float)outerSrcHeight;

                // Draw outer part
                batch.draw(texture, renderLocationX, renderLocationY, renderWidth, renderHeight, outerSrcX, outerSrcY, outerSrcWidth, outerSrcHeight, false, false);

                int sleepCountFirstNum = uiActivityCountComponent.sleepCount / 10;
                int sleepCountSecondNum = uiActivityCountComponent.sleepCount % 10;

                int numbersSrcWidth = uiActivityCountComponent.numbersSrcWidth;
                int numbersSrcHeight = uiActivityCountComponent.numbersSrcHeight;

                int sleepFirstNumberSrcX = uiActivityCountComponent.numbersSrcX + ((numbersSrcWidth + 1) * sleepCountFirstNum);
                int sleepFirstNumberSrcY = uiActivityCountComponent.numbersSrcY;

                int sleepSecondNumberSrcX = uiActivityCountComponent.numbersSrcX + ((numbersSrcWidth + 1) * sleepCountSecondNum);
                int sleepSecondNumberSrcY = uiActivityCountComponent.numbersSrcY;

                int sleepFirstNumberXOffset = Math.round(uiActivityCountComponent.sleepNumberXOffset * totalWidthRatio);
                int sleepFirstNumberYOffset = Math.round(uiActivityCountComponent.sleepNumberYOffset * totalHeightRatio);

                int sleepSecondNumberXOffset = Math.round((uiActivityCountComponent.sleepNumberXOffset + numbersSrcWidth + 1) * totalWidthRatio);
                int sleepSecondNumberYOffset = Math.round(uiActivityCountComponent.sleepNumberYOffset * totalHeightRatio);

                int sleepNumberRenderWidth = Math.round(numbersSrcWidth * totalWidthRatio);
                int sleepNumberRenderHeight = Math.round(numbersSrcHeight * totalHeightRatio);

                // Draw sleep numbers
                batch.draw(texture, renderLocationX + sleepFirstNumberXOffset, renderLocationY + sleepFirstNumberYOffset, sleepNumberRenderWidth, sleepNumberRenderHeight, sleepFirstNumberSrcX, sleepFirstNumberSrcY, numbersSrcWidth, numbersSrcHeight, false, false);
                batch.draw(texture, renderLocationX + sleepSecondNumberXOffset, renderLocationY + sleepSecondNumberYOffset, sleepNumberRenderWidth, sleepNumberRenderHeight, sleepSecondNumberSrcX, sleepSecondNumberSrcY, numbersSrcWidth, numbersSrcHeight, false, false);

                int studyCountFirstNum = uiActivityCountComponent.studyCount / 10;
                int studyCountSecondNum = uiActivityCountComponent.studyCount % 10;

                int studyFirstNumberSrcX = uiActivityCountComponent.numbersSrcX + ((numbersSrcWidth + 1) * studyCountFirstNum);
                int studyFirstNumberSrcY = uiActivityCountComponent.numbersSrcY;

                int studySecondNumberSrcX = uiActivityCountComponent.numbersSrcX + ((numbersSrcWidth + 1) * studyCountSecondNum);
                int studySecondNumberSrcY = uiActivityCountComponent.numbersSrcY;

                int studyFirstNumberXOffset = Math.round(uiActivityCountComponent.studyNumberXOffset * totalWidthRatio);
                int studyFirstNumberYOffset = Math.round(uiActivityCountComponent.studyNumberYOffset * totalHeightRatio);

                int studySecondNumberXOffset = Math.round((uiActivityCountComponent.studyNumberXOffset + numbersSrcWidth + 1) * totalWidthRatio);
                int studySecondNumberYOffset = Math.round(uiActivityCountComponent.studyNumberYOffset * totalHeightRatio);

                int studyNumberRenderWidth = Math.round(numbersSrcWidth * totalWidthRatio);
                int studyNumberRenderHeight = Math.round(numbersSrcHeight * totalHeightRatio);

                // Draw study numbers
                batch.draw(texture, renderLocationX + studyFirstNumberXOffset, renderLocationY + studyFirstNumberYOffset, studyNumberRenderWidth, studyNumberRenderHeight, studyFirstNumberSrcX, studyFirstNumberSrcY, numbersSrcWidth, numbersSrcHeight, false, false);
                batch.draw(texture, renderLocationX + studySecondNumberXOffset, renderLocationY + studySecondNumberYOffset, studyNumberRenderWidth, studyNumberRenderHeight, studySecondNumberSrcX, studySecondNumberSrcY, numbersSrcWidth, numbersSrcHeight, false, false);

                int eatCountFirstNum = uiActivityCountComponent.eatCount / 10;
                int eatCountSecondNum = uiActivityCountComponent.eatCount % 10;

                int eatFirstNumberSrcX = uiActivityCountComponent.numbersSrcX + ((numbersSrcWidth + 1) * eatCountFirstNum);
                int eatFirstNumberSrcY = uiActivityCountComponent.numbersSrcY;

                int eatSecondNumberSrcX = uiActivityCountComponent.numbersSrcX + ((numbersSrcWidth + 1) * eatCountSecondNum);
                int eatSecondNumberSrcY = uiActivityCountComponent.numbersSrcY;

                int eatFirstNumberXOffset = Math.round(uiActivityCountComponent.eatNumberXOffset * totalWidthRatio);
                int eatFirstNumberYOffset = Math.round(uiActivityCountComponent.eatNumberYOffset * totalHeightRatio);

                int eatSecondNumberXOffset = Math.round((uiActivityCountComponent.eatNumberXOffset + numbersSrcWidth + 1) * totalWidthRatio);
                int eatSecondNumberYOffset = Math.round(uiActivityCountComponent.eatNumberYOffset * totalHeightRatio);

                int eatNumberRenderWidth = Math.round(numbersSrcWidth * totalWidthRatio);
                int eatNumberRenderHeight = Math.round(numbersSrcHeight * totalHeightRatio);

                // Draw eat numbers
                batch.draw(texture, renderLocationX + eatFirstNumberXOffset, renderLocationY + eatFirstNumberYOffset, eatNumberRenderWidth, eatNumberRenderHeight, eatFirstNumberSrcX, eatFirstNumberSrcY, numbersSrcWidth, numbersSrcHeight, false, false);
                batch.draw(texture, renderLocationX + eatSecondNumberXOffset, renderLocationY + eatSecondNumberYOffset, eatNumberRenderWidth, eatNumberRenderHeight, eatSecondNumberSrcX, eatSecondNumberSrcY, numbersSrcWidth, numbersSrcHeight, false, false);

                int recCountFirstNum = uiActivityCountComponent.recCount / 10;
                int recCountSecondNum = uiActivityCountComponent.recCount % 10;

                int recFirstNumberSrcX = uiActivityCountComponent.numbersSrcX + ((numbersSrcWidth + 1) * recCountFirstNum);
                int recFirstNumberSrcY = uiActivityCountComponent.numbersSrcY;

                int recSecondNumberSrcX = uiActivityCountComponent.numbersSrcX + ((numbersSrcWidth + 1) * recCountSecondNum);
                int recSecondNumberSrcY = uiActivityCountComponent.numbersSrcY;

                int recFirstNumberXOffset = Math.round(uiActivityCountComponent.recNumberXOffset * totalWidthRatio);
                int recFirstNumberYOffset = Math.round(uiActivityCountComponent.recNumberYOffset * totalHeightRatio);

                int recSecondNumberXOffset = Math.round((uiActivityCountComponent.recNumberXOffset + numbersSrcWidth + 1) * totalWidthRatio);
                int recSecondNumberYOffset = Math.round(uiActivityCountComponent.recNumberYOffset * totalHeightRatio);

                int recNumberRenderWidth = Math.round(numbersSrcWidth * totalWidthRatio);
                int recNumberRenderHeight = Math.round(numbersSrcHeight * totalHeightRatio);

                // Draw rec numbers
                batch.draw(texture, renderLocationX + recFirstNumberXOffset, renderLocationY + recFirstNumberYOffset, recNumberRenderWidth, recNumberRenderHeight, recFirstNumberSrcX, recFirstNumberSrcY, numbersSrcWidth, numbersSrcHeight, false, false);
                batch.draw(texture, renderLocationX + recSecondNumberXOffset, renderLocationY + recSecondNumberYOffset, recNumberRenderWidth, recNumberRenderHeight, recSecondNumberSrcX, recSecondNumberSrcY, numbersSrcWidth, numbersSrcHeight, false, false);
            }
        }
    }
}
