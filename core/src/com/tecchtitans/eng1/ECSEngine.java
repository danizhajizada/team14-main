package com.tecchtitans.eng1;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.tecchtitans.eng1.components.*;

import java.util.EnumMap;

/**
 * Extends the Engine class to add a currentMap field.
 //* @see com.badlogic.ashley.core.Engine
 */
public class ECSEngine extends Engine {
    Map currentMap;

    /**
     * Instantiates an ECS
     */
    public ECSEngine() {
        super();
    }

    /**
     * Updates the current map in the ECSEngine to a desired map.
     * @param map - Desired map given as a Map type.
     */
    public void updateMap(Map map) {
        this.currentMap = map;
    }

    public Entity createUIActivityCounter(int renderX, int renderY, int width, int height) {
        Entity activityCounter = new Entity();

        UIComponent uiComponent = createComponent(UIComponent.class);
        uiComponent.type = UIComponentType.ACTCOUNT;
        activityCounter.add(uiComponent);

        TextureComponent textureComponent = createComponent(TextureComponent.class);
        textureComponent.texture = new Texture("stats.png");
        textureComponent.srcStartX = 0;
        textureComponent.srcStartY = 86;
        textureComponent.width = width;
        textureComponent.height = height;
        activityCounter.add(textureComponent);

        UIActivityCountComponent uiActivityCountComponent = createComponent(UIActivityCountComponent.class);
        uiActivityCountComponent.outerPartSrcX = 0;
        uiActivityCountComponent.outerPartSrcY = 86;
        uiActivityCountComponent.outerPartSrcWidth = 49;
        uiActivityCountComponent.outerPartSrcHeight = 49;

        uiActivityCountComponent.numbersSrcX = 0;
        uiActivityCountComponent.numbersSrcY = 50;
        uiActivityCountComponent.numbersSrcWidth = 5;
        uiActivityCountComponent.numbersSrcHeight = 7;

        uiActivityCountComponent.sleepNumberXOffset = 36;
        uiActivityCountComponent.sleepNumberYOffset = 39;

        uiActivityCountComponent.studyNumberXOffset = 36;
        uiActivityCountComponent.studyNumberYOffset = 27;

        uiActivityCountComponent.eatNumberXOffset = 36;
        uiActivityCountComponent.eatNumberYOffset = 15;

        uiActivityCountComponent.recNumberXOffset = 36;
        uiActivityCountComponent.recNumberYOffset = 3;
        activityCounter.add(uiActivityCountComponent);

        PositionComponent positionComponent = createComponent(PositionComponent.class);
        positionComponent.positionVector.x = renderX;
        positionComponent.positionVector.y = renderY;
        activityCounter.add(positionComponent);

        addEntity(activityCounter);

        return activityCounter;
    }

    public Entity createUIDayCounter(int renderX, int renderY, int width, int height) {
        Entity dayCounter = new Entity();

        UIComponent uiComponent = createComponent(UIComponent.class);
        uiComponent.type = UIComponentType.DAY;
        dayCounter.add(uiComponent);

        TextureComponent textureComponent = createComponent(TextureComponent.class);
        textureComponent.texture = new Texture("stats.png");
        textureComponent.srcStartX = 0;
        textureComponent.srcStartY = 74;
        textureComponent.width = width;
        textureComponent.height = height;
        dayCounter.add(textureComponent);

        UIDayComponent uiDayComponent = createComponent(UIDayComponent.class);
        uiDayComponent.outerPartSrcX = 0;
        uiDayComponent.outerPartSrcY = 74;
        uiDayComponent.outerPartSrcWidth = 31;
        uiDayComponent.outerPartSrcHeight = 11;

        uiDayComponent.numbersSrcX = 0;
        uiDayComponent.numbersSrcY = 50;
        uiDayComponent.numbersSrcWidth = 5;
        uiDayComponent.numbersSrcHeight = 7;

        uiDayComponent.numberXOffset = 24;
        uiDayComponent.numberYOffset = 2;

        uiDayComponent.currentDay = 0;
        dayCounter.add(uiDayComponent);

        PositionComponent positionComponent = createComponent(PositionComponent.class);
        positionComponent.positionVector.x = renderX;
        positionComponent.positionVector.y = renderY;
        dayCounter.add(positionComponent);

        addEntity(dayCounter);

        return dayCounter;
    }

    public Entity createUIClock(int renderX, int renderY, int width, int height) {
        Entity clock = new Entity();

        UIComponent uiComponent = createComponent(UIComponent.class);
        uiComponent.type = UIComponentType.TIME;
        clock.add(uiComponent);

        TextureComponent textureComponent = createComponent(TextureComponent.class);
        textureComponent.texture = new Texture("stats.png");
        textureComponent.srcStartX = 0;
        textureComponent.srcStartY = 38;
        textureComponent.width = width;
        textureComponent.height = height;
        //textureComponent.width = 100;
        //textureComponent.height = 100;
        clock.add(textureComponent);

        UITimeComponent uiTimeComponent = createComponent(UITimeComponent.class);
        uiTimeComponent.outerPartSrcX = 0;
        uiTimeComponent.outerPartSrcY = 38;
        uiTimeComponent.outerPartSrcWidth = 54;
        uiTimeComponent.outerPartSrcHeight = 11;

        uiTimeComponent.numbersSrcX = 0;
        uiTimeComponent.numbersSrcY = 50;
        uiTimeComponent.numbersSrcWidth = 5;
        uiTimeComponent.numbersSrcHeight = 7;

        uiTimeComponent.amSrcX = 0;
        uiTimeComponent.amSrcY = 58;
        uiTimeComponent.amSrcWidth = 11;
        uiTimeComponent.amSrcHeight = 7;

        uiTimeComponent.pmSrcX = 0;
        uiTimeComponent.pmSrcY = 66;
        uiTimeComponent.pmSrcWidth = 11;
        uiTimeComponent.pmSrcHeight = 7;

        uiTimeComponent.numbersXOffset = 29;
        uiTimeComponent.numbersYOffset = 2;

        uiTimeComponent.currentHour = 0;
        clock.add(uiTimeComponent);

        PositionComponent positionComponent = createComponent(PositionComponent.class);
        positionComponent.positionVector.x = renderX;
        positionComponent.positionVector.y = renderY;
        clock.add(positionComponent);

        addEntity(clock);

        return clock;
    }

    public Entity createStatBar(int renderX, int renderY, int width, int height) {
        Entity statBar = new Entity();

        UIComponent uiComponent = createComponent(UIComponent.class);
        uiComponent.type = UIComponentType.STATBAR;
        statBar.add(uiComponent);

        TextureComponent textureComponent = createComponent(TextureComponent.class);
        textureComponent.texture = new Texture("stats.png");
        textureComponent.srcStartX = 0;
        textureComponent.srcStartY = 0;
        textureComponent.width = width;
        textureComponent.height = height;
        statBar.add(textureComponent);

        StatBarComponent statBarComponent = createComponent(StatBarComponent.class);
        statBarComponent.outerPartSrcX = 0;
        statBarComponent.outerPartSrcY = 0;
        statBarComponent.outerPartSrcWidth = 109;
        statBarComponent.outerPartSrcHeight = 19;

        statBarComponent.innerPartSrcX = 0;
        statBarComponent.innerPartSrcY = 20;
        statBarComponent.innerPartSrcWidth = 94;
        statBarComponent.innerPartSrcHeight = 17;

        statBarComponent.innerPartXOffset = 14;
        statBarComponent.innerPartYOffset = 1;

        statBarComponent.progress = 1;
        statBar.add(statBarComponent);

        PositionComponent positionComponent = createComponent(PositionComponent.class);
        positionComponent.positionVector.x = renderX;
        positionComponent.positionVector.y = renderY;
        statBar.add(positionComponent);

        addEntity(statBar);

        return statBar;
    }

    public Entity createBuilding(int spawnX, int spawnY, int width, int height) {
        Entity building = createEntity();

        building.add(createComponent(PositionComponent.class));

        CollisionComponent collisionComponent = createComponent(CollisionComponent.class);
        collisionComponent.collisionRectangle.x = spawnX;
        collisionComponent.collisionRectangle.y = spawnY;
        collisionComponent.collisionRectangle.width = width;
        collisionComponent.collisionRectangle.height = height;
        building.add(collisionComponent);

        GameObjectComponent gameObjectComponent = createComponent(GameObjectComponent.class);
        gameObjectComponent.type = GameObjectComponent.ObjectType.BUILDING;
        building.add(gameObjectComponent);

        addEntity(building);

        return building;
    }

    // adding entity assuming building already on map
    public Entity createBuilding(RectangleMapObject buildingObject) {
        Entity building = createEntity();

        CollisionComponent collisionComponent = createComponent(CollisionComponent.class);
        collisionComponent.collisionRectangle = buildingObject.getRectangle();
        building.add(collisionComponent);

        ActivityComponent activityComponent = createComponent(ActivityComponent.class);
        String test = (String)buildingObject.getProperties().get("type");
        activityComponent.type = ActivityType.valueOf((String)buildingObject.getProperties().get("type"));
        switch(activityComponent.type) {
            case EAT:
                activityComponent.energyChange = 0;
                activityComponent.studyChange = 0;
                activityComponent.timeChange = 1;
                break;
            case REC:
                activityComponent.energyChange = -10;
                activityComponent.studyChange = 0;
                activityComponent.timeChange = 1;
                break;
            case SLEEP:
                activityComponent.energyChange = 0;
                activityComponent.studyChange = 0;
                activityComponent.timeChange = 0;
                break;
            case STUDY:
                activityComponent.energyChange = -10;
                activityComponent.studyChange = 10;
                activityComponent.timeChange = 1;
                break;
        }
        building.add(activityComponent);

        GameObjectComponent gameObjectComponent = createComponent(GameObjectComponent.class);
        gameObjectComponent.type = GameObjectComponent.ObjectType.BUILDING;
        building.add(gameObjectComponent);

        addEntity(building);

        return building;
    }

    public Entity createPlayer(int spawnX, int spawnY, int width, int height) {
        Entity player = new Entity();

        player.add(createComponent(PlayerComponent.class));

        PositionComponent positionComponent = createComponent(PositionComponent.class);
        positionComponent.positionVector.x = spawnX;
        positionComponent.positionVector.y = spawnY;
        player.add(positionComponent);

        player.add(createComponent(VelocityComponent.class));
        player.add(createComponent(InputComponent.class));

        TextureComponent playerTexture = createComponent(TextureComponent.class);
        playerTexture.texture = new Texture("spacesoldier.png");
        playerTexture.srcStartX = 8;
        playerTexture.srcStartY = 11;
        playerTexture.width = width;
        playerTexture.height = height;

        player.add(playerTexture);

        CollisionComponent collisionComponent = createComponent(CollisionComponent.class);
        collisionComponent.collisionRectangle.x = spawnX;
        collisionComponent.collisionRectangle.y = spawnY;
        collisionComponent.collisionRectangle.width = width;
        collisionComponent.collisionRectangle.height = height;
        player.add(collisionComponent);

        PlayerComponent playerComponent = createComponent(PlayerComponent.class);
        //playerComponent.activityCount = new int[ActivityType.values().length];
        playerComponent.activityCount = new EnumMap<>(ActivityType.class);
        for(ActivityType type : ActivityType.values()) {
            playerComponent.activityCount.put(type, 0);
        }

        playerComponent.energy = 100;
        playerComponent.study = 0;
        playerComponent.currentActivity = null;
        player.add(playerComponent);


        addEntity(player);

        return player;
    }
}
