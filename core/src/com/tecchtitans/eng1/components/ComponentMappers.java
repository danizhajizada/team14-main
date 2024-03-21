package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.ComponentMapper;

/**
 * An abstract class that stores component maps to efficiently receive an entities components.
 */
public abstract class ComponentMappers {
    /**
     * Mapper for position component.
     */
    public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
    /**
     * Mapper for velocity component.
     */
    public static final ComponentMapper<VelocityComponent> velocity = ComponentMapper.getFor(VelocityComponent.class);
    /**
     * Mapper for input component.
     */
    public static final ComponentMapper<InputComponent> input = ComponentMapper.getFor(InputComponent.class);
    /**
     * Mapper for player component.
     */
    public static final ComponentMapper<PlayerComponent> player = ComponentMapper.getFor(PlayerComponent.class);
    /**
     * Mapper for collision component.
     */
    public static final ComponentMapper<CollisionComponent> collision = ComponentMapper.getFor(CollisionComponent.class);
    /**
     * Mapper for game object component.
     */
    public static final ComponentMapper<GameObjectComponent> gameObject = ComponentMapper.getFor(GameObjectComponent.class);
    /**
     * Mapper for activity component.
     */
    public static final ComponentMapper<ActivityComponent> activity = ComponentMapper.getFor(ActivityComponent.class);
    /**
     * Mapper for texture component.
     */
    public static final ComponentMapper<TextureComponent> texture = ComponentMapper.getFor(TextureComponent.class);
    /**
     * Mapper for UI component.
     */
    public static final ComponentMapper<UIComponent> ui = ComponentMapper.getFor(UIComponent.class);
    /**
     * Mapper for statistic bar component.
     */
    public static final ComponentMapper<StatBarComponent> statBar = ComponentMapper.getFor(StatBarComponent.class);
    /**
     * Mapper for UI time component.
     */
    public static final ComponentMapper<UITimeComponent> time = ComponentMapper.getFor(UITimeComponent.class);
    /**
     * Mapper for UI day component.
     */
    public static final ComponentMapper<UIDayComponent> day = ComponentMapper.getFor(UIDayComponent.class);
    /**
     * Mapper for UI activity component.
     */
    public static final ComponentMapper<UIActivityCountComponent> activityCount = ComponentMapper.getFor(UIActivityCountComponent.class);
}
