package com.tecchtitans.eng1.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.tecchtitans.eng1.components.ComponentMappers;
import com.tecchtitans.eng1.components.InputComponent;
import com.tecchtitans.eng1.components.PlayerComponent;
import com.tecchtitans.eng1.components.VelocityComponent;

public class GameSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PlayerComponent.class).get());
    }

    public void update(float deltaTime) {
        for (Entity entity : entities) {
            PlayerComponent playerComponent = ComponentMappers.player.get(entity);

            if(playerComponent.currentActivity != null){
                switch (playerComponent.currentActivity) {
                    case STUDY:
                        break;
                    case SLEEP:
                        break;
                    case REC:
                        break;
                    case EAT:
                        break;
                    default:
                        break;
                }
                System.out.println(playerComponent.currentActivity);
                playerComponent.currentActivity = null;
            }
        }
    }
}
