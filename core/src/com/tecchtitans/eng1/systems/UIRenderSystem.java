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

            if (uiComponent.type == UIComponentType.STATBAR) {
                StatBarComponent statBarComponent = ComponentMappers.statBar.get(entity);

                Texture texture = textureComponent.texture;
                int renderWidth = textureComponent.width;
                int renderHeight = textureComponent.height;

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



                int renderLocationX = (int)positionComponent.positionVector.x;
                int renderLocationY = (int)positionComponent.positionVector.y;

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

                int innerXOffset = (int)(statBarComponent.innerPartXOffset * totalWidthRatio);
                int innerYOffset = (int)(statBarComponent.innerPartYOffset * totalHeightRatio);

                int innerSrcWidth = statBarComponent.innerPartSrcWidth;
                int innerSrcHeight = statBarComponent.innerPartSrcHeight;

                int innerRenderWidth = (int)(innerSrcWidth * totalWidthRatio * progress);
                int innerRenderHeight = (int)(innerSrcHeight * totalHeightRatio);

                // Draw inner bar
                batch.draw(texture, renderLocationX + innerXOffset, renderLocationY + innerYOffset, innerRenderWidth, innerRenderHeight, innerSrcX, innerSrcY, innerSrcWidth, innerSrcHeight, false, false);
            }
        }
    }
}
