package com.tecchtitans.eng1.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.tecchtitans.eng1.ActivityType;
import com.tecchtitans.eng1.components.*;

public class GameSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private int hour;
    private int day;
    private boolean gameComplete;
    public void addedToEngine(Engine engine) {
        hour = 0;
        day = 1;
        gameComplete = false;
        entities = engine.getEntitiesFor(Family.all(PlayerComponent.class).get());
    }

    public int getHour() {
        return hour;
    }

    public int getDay() {
        return day;
    }

    public void update(float deltaTime) {
        for (Entity entity : entities) {
            PlayerComponent playerComponent = ComponentMappers.player.get(entity);
            ActivityComponent currentActivity = playerComponent.currentActivity;

            if(currentActivity != null){
                if(gameComplete) {
                    System.out.println("Game finished. Stats:");
                    System.out.printf("SLEEP: %s, STUDY: %s, REC: %s, EAT: %s%n",
                            playerComponent.activityCount.get(ActivityType.SLEEP),
                            playerComponent.activityCount.get(ActivityType.STUDY),
                            playerComponent.activityCount.get(ActivityType.REC),
                            playerComponent.activityCount.get(ActivityType.EAT)
                    );
                    System.out.printf("Final study level: %s%n", playerComponent.study);
                }
                else {
                    System.out.println("Activity type: " + currentActivity.type);
                    System.out.println("Current time: " + hour);
                    System.out.println("Current day: " + day);
                    System.out.println("Current energy: " + playerComponent.energy);
                    System.out.println("Current study: " + playerComponent.study);

                    //dont like how long this line is, split
                    if(hour + currentActivity.timeChange <= 16 && playerComponent.energy + currentActivity.energyChange >= 0) {
                        playerComponent.activityCount.put(
                                currentActivity.type,
                                playerComponent.activityCount.get(currentActivity.type) + 1);
                        System.out.println("Activity performed");
                        if(currentActivity.type == ActivityType.SLEEP) {
                            day++;
                            hour = 0;
                            playerComponent.energy = 100;
                            if(day >  7) {
                                gameComplete = true;
                            }
                        }
                        else{
                            hour += currentActivity.timeChange;
                            playerComponent.energy += currentActivity.energyChange;
                            playerComponent.study += currentActivity.studyChange;
                        }

                        System.out.println("New day: " + day);
                        System.out.println("New hour: " + hour);
                        System.out.println("New energy: " + playerComponent.energy);
                        System.out.println("New study: " + playerComponent.study);
                    }
                    else {
                        System.out.println("Activity not performed");
                    }
                }


                playerComponent.currentActivity = null;
                System.out.println("");
            }
        }
    }
}
