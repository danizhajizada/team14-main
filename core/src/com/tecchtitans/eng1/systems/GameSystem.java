package com.tecchtitans.eng1.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.tecchtitans.eng1.ActivityType;
import com.tecchtitans.eng1.components.*;

/**
 * System that handles all logic required with the function of the game, such as the
 * current day or time. Handles the appropriate actions to make to a player and the
 * game once a certain activity is performed, for instance progressing the time/day
 * and reducing the player's energy level.
 */
public class GameSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private int hour;
    private int day;
    private boolean gameComplete;

    /**
     * When the system is added to the engine, the hours passed in the day is set to 0,
     * the current day is set to 1, and gameComplete is set to false. All player entities
     * in the engine are added to the system.
     * @param engine - The Engine this system was added to.
     */
    public void addedToEngine(Engine engine) {
        hour = 0;
        day = 1;
        gameComplete = false;
        entities = engine.getEntitiesFor(Family.all(PlayerComponent.class).get());
    }

    /**
     * Returns the boolean value determining whether the game has complete.
     * @return boolean where true represents the game being complete, and false where
     *         it is not.
     */
    public boolean isGameComplete() {
        return gameComplete;
    }

    /**
     * Retrieves how many hours have passed since the start of the day.
     * @return integer value of number of hours passed.
     */
    public int getHour() {
        return hour;
    }

    /**
     * Retrieves the current day.
     * @return integer value of the current day in the game.
     */
    public int getDay() {
        return day;
    }

    /**
     * This is called every tick. This checks every player entity in the system if
     * they have an activity to be performed. If they do, the activity type to be
     * performed is checked and the correct actions are made to the PlayerComponent
     * for the player entity, and the day and hour in this game instance are progressed
     * as determined by the activity in the ActivityComponent. For instance, a STUDY
     * activity would reduce the player entity's energy level, progress the time by the
     * time change decided in ActivityComponent, but not progress to the next day.
     * <p>
     * If the game is complete, no activity will be performed no matter what.
     * @param deltaTime - Time in seconds passed since the last frame.
     */
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
