package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.ComponentMapper;
import org.w3c.dom.Text;

public class ComponentMappers {
    public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<VelocityComponent> velocity = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<InputComponent> input = ComponentMapper.getFor(InputComponent.class);
    public static final ComponentMapper<PlayerComponent> player = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<CollisionComponent> collision = ComponentMapper.getFor(CollisionComponent.class);
    public static final ComponentMapper<GameObjectComponent> gameObject = ComponentMapper.getFor(GameObjectComponent.class);
    public static final ComponentMapper<ActivityComponent> activity = ComponentMapper.getFor(ActivityComponent.class);
    public static final ComponentMapper<TextureComponent> texture = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<UIComponent> ui = ComponentMapper.getFor(UIComponent.class);
    public static final ComponentMapper<StatBarComponent> statBar = ComponentMapper.getFor(StatBarComponent.class);
    public static final ComponentMapper<UITimeComponent> time = ComponentMapper.getFor(UITimeComponent.class);
    public static final ComponentMapper<UIDayComponent> day = ComponentMapper.getFor(UIDayComponent.class);
    public static final ComponentMapper<UIActivityCountComponent> activityCount = ComponentMapper.getFor(UIActivityCountComponent.class);
}
