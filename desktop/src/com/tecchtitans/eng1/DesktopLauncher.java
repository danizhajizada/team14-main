package com.tecchtitans.eng1;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.tecchtitans.eng1.ENGGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("ENG1-Project");
		config.setWindowedMode(1280, 720);
		config.setResizable(false);
		//config.setFullscreenMode();
		new Lwjgl3Application(new ENGGame(), config);
	}
}
