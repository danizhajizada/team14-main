package com.tecchtitans.eng1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {

    private Music currentMusic;
    public AudioManager() {
        currentMusic = null;
    }

    public void playMusic(String name) {
        currentMusic = Gdx.audio.newMusic(Gdx.files.internal(name));
        currentMusic.setLooping(true);
        currentMusic.play();
    }

    public void stopMusic() {
        currentMusic.stop();
    }

    public void playSound(String name) {
        Sound soundEffect = Gdx.audio.newSound(Gdx.files.internal(name));
        soundEffect.play();
    }
}
