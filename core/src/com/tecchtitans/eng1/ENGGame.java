package com.tecchtitans.eng1;

import com.badlogic.gdx.Game;
import com.tecchtitans.eng1.screens.GameOverScreen;
import com.tecchtitans.eng1.screens.MainMenu;
import com.tecchtitans.eng1.screens.PlayScreen;
import com.tecchtitans.eng1.systems.*;

/**
 * Extends the Game class. Contains the ECS engine that is used for running most
 * game logic, along with other aspects of the program such as the audio manager
 * and the screens that can be displayed in the program, along with being able
 * to switch the currently displayed screen.
 //* @see com.badlogic.gdx.Game
 */
public class ENGGame extends Game {
	ECSEngine engine;
	AudioManager audioManager;

	MainMenu mainMenu;
	PlayScreen playScreen;
	GameOverScreen gameOverScreen;

	/**
	 * Returns the respective ECSEngine the game is using.
	 * @return engine as an ECSEngine.
	 */
	public ECSEngine getEngine()
	{
		return engine;
	}

	/**
	 * Returns the respective AudioManager the game is using.
	 * @return audio manager as an AudioManager.
	 */
	public AudioManager getAudioManager() { return audioManager; }

	/**
	 * Switches the currently displayed screen to the main menu.
	 */
	public void switchToMainMenu() {
		setScreen(mainMenu);
	}

	/**
	 * Switches the currently displayed screen to the play screen.
	 */
	public void switchToPlayScreen() {
		setScreen(playScreen);
	}

	/**
	 * Switches the currently displayed screen to the game over screen.
	 */
	public void switchToGameOverScreen() {
		setScreen(gameOverScreen);
	}

	/**
	 * Called when application is first created. Initialises the audio manager,
	 * main menu screen, play screen, and game over screen. Also sets up the
	 * ECS engine. Finally, it will set the current screen to the main menu.
	 */
	@Override
	public void create () {

		//initialize audio manager
		audioManager = new AudioManager();
		reset();

		switchToMainMenu();
	}

	/**
	 * Initialises the ECSEngine with the systems required for the game to function.
	 * These include the InputSystem, PlayerSystem, CollisionSystem, PlayerMovementSystem,
	 * PlayerCameraSystem, UIRenderSystem, and GameSystem.
	 */
	public void setupEngine() {
		engine = new ECSEngine();

		InputSystem inputSystem = new InputSystem();
		engine.addSystem(inputSystem);

		PlayerSystem playerSystem = new PlayerSystem();
		engine.addSystem(playerSystem);

		CollisionSystem collisionSystem = new CollisionSystem();
		engine.addSystem(collisionSystem);

		PlayerMovementSystem playerMovementSystem = new PlayerMovementSystem();
		engine.addSystem(playerMovementSystem);

		PlayerCameraSystem playerCameraSystem = new PlayerCameraSystem();
		engine.addSystem(playerCameraSystem);

		// This system will be called when needed so processing is not needed.
		UIRenderSystem uiRenderSystem = new UIRenderSystem();
		uiRenderSystem.setProcessing(false);
		engine.addSystem(uiRenderSystem);

		GameSystem gameSystem = new GameSystem();
		engine.addSystem(gameSystem);
	}

	/**
	 * Re-initialises all screens and calls setupEngine to re-initialise engine.
	 * Also stops any music that may be playing in the audio manager.
	 */
	public void reset() {
		setupEngine();

		audioManager.stopMusic();

		mainMenu = new MainMenu(this);
		playScreen = new PlayScreen(this);
		gameOverScreen = new GameOverScreen(this);
	}

	/**
	 * Calls the render method in the parent class Game.
	 //* @see Game
	 */
	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		//assetManager.dispose();
	}
}
