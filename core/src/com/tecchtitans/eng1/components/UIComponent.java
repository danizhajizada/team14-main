package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;

/**
 * Stores relevant information for UI Components
 */
public class UIComponent implements Component {
    /**
     * Stores the type of UI Component the entity is.
     */
    public UIComponentType type = UIComponentType.NULL;
}
