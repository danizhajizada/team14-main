package com.tecchtitans.eng1.components;

import com.badlogic.ashley.core.Component;

/**
 * A component that stores relevant information about input.
 */
public class InputComponent implements Component {
    /**
     * Stores a binary number where each bit corresponds to a key that is currently being pressed.
     */
    public int keysPressed = 0b00000;
}
