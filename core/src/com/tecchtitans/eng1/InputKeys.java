package com.tecchtitans.eng1;

/**
 * Each key that needs to be detected as an input is encoded as a static one-hot value.
 */
public abstract class InputKeys {
    /**
     * One-hot encoding for no key input.
     */
    public static final int NONE  = 0b00000;
    /**
     * One-hot encoding for the up arrow key.
     */
    public static final int UP    = 0b01000;
    /**
     * One-hot encoding for the down arrow key.
     */
    public static final int DOWN  = 0b00100;
    /**
     * One-hot encoding for the left arrow key.
     */
    public static final int LEFT  = 0b00010;
    /**
     * One-hot encoding for the right arrow key.
     */
    public static final int RIGHT = 0b00001;
    /**
     * One-hot encoding for the space key.
     */
    public static final int SPACE = 0b10000;
}
