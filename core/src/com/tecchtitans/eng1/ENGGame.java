package com.tecchtitans.eng1;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.maps.tiled.TideMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tecchtitans.eng1.screens.MainMenu;
import com.tecchtitans.eng1.systems.*;

public class ENGGame extends Game {
	ECSEngine engine;
	AudioManager audioManager;

	public ECSEngine getEngine()
	{
		return engine;
	}
	public AudioManager getAudioManager() { return audioManager; }

	@Override
	public void create () {
		engine = new ECSEngine();

		//MovementSystem movementSystem = new MovementSystem();
		//engine.addSystem(movementSystem);

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
		
		// This engine will be called when needed so processing is not needed.
		UIRenderSystem uiRenderSystem = new UIRenderSystem();
		uiRenderSystem.setProcessing(false);
		engine.addSystem(uiRenderSystem);

		GameSystem gameSystem = new GameSystem();
		engine.addSystem(gameSystem);

		//initialize audio manager

		audioManager = new AudioManager();

		//player = createPlayer();

		//setScreen(new MainMenu(this));

		//camera = new OrthographicCamera();
		//camera.setToOrtho(false, 2268, 1200);

		//map = new TmxMapLoader().load("mainmenu_sample.tmx");

		//renderer = new OrthogonalTiledMapRenderer(map);

		//mapRenderer.setView(camera);

		setScreen(new MainMenu(this));

		//Gdx.graphics.wait();
	}

	@Override
	public void render () {
		//Gdx.gl.glClearColor(1, 0, 0, 1);
		//Gdx.gl.glBlendFunc(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);
		//Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
		//camera.update();
		//renderer.setView(camera);
		//renderer.render();

		super.render();

		//engine.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		//assetManager.dispose();
	}
}
