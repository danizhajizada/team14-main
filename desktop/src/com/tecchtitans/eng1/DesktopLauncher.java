package com.tecchtitans.eng1;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.tecchtitans.eng1.ENGGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument

/**
 * Launches the program for desktop PCs.
 */
public class DesktopLauncher {
	/**
	 * Sets technical settings for the game. Resolution is set to 1280x720. FPS is set to 60.
	 * Title is set to ENG1-Project. Window is set to not be resizeable.
	 * @param arg - args for main method.
	 */
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Heslington Hustle");
		config.setWindowedMode(1280, 720);
		config.setResizable(false);
		//config.setFullscreenMode();
		new Lwjgl3Application(new ENGGame(), config);
	}
}
