package com.tecchtitans.eng1.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.tecchtitans.eng1.components.*;

public class UIRenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    public UIRenderSystem() {};

    public void addedToEngine(Engine engine)
    {
        entities = engine.getEntitiesFor(Family.all(TextureComponent.class, UIComponent.class, PositionComponent.class, StatBarComponent.class).get());
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

                batch.draw(texture, positionComponent.positionVector.x, positionComponent.positionVector.y,
                        renderWidth, renderHeight, statBarComponent.outerPartSrcX, statBarComponent.outerPartSrcY,
                        statBarComponent.outerPartSrcWidth, statBarComponent.outerPartSrcHeight, false, false);

                batch.draw(texture, positionComponent.positionVector.x + statBarComponent.intersectSrcX, positionComponent.positionVector.y - statBarComponent.intersectSrcY,
                        100, 0, renderWidth, renderHeight, 1, 1, 0, statBarComponent.innerPartSrcX, statBarComponent.innerPartSrcY,
                        statBarComponent.innerPartSrcWidth, statBarComponent.innerPartSrcHeight - 5, false, false);

                

            }
        }
    }
}