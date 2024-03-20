package com.tecchtitans.eng1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Manages music and sound effects that are to be played in the game.
 */
public class AudioManager {

    private Music currentMusic;

    /**
     * Instantiates AudioManager, with the currentMusic being null.
     */
    public AudioManager() {
        currentMusic = null;
    }

    /**
     * Plays the desired music from a given internal file path, and sets the music to
     * loop indefinitely.
     * @param name - Internal file path of music to be played, given as a String.
     */
    public void playMusic(String name) {
        currentMusic = Gdx.audio.newMusic(Gdx.files.internal(name));
        currentMusic.setLooping(true);
        currentMusic.play();
    }

    /**
     * Stops the currently playing music, if there is any.
     */
    public void stopMusic() {
        currentMusic.stop();
    }

    /**
     * Plays desired sound effect from a given internal file path.
     * @param name - Internal file path of sound to be played, given as a String.
     */
    public void playSound(String name) {
        Sound soundEffect = Gdx.audio.newSound(Gdx.files.internal(name));
        soundEffect.play();
    }
}
