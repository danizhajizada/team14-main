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

public class ENGGame extends Game {
	Engine engine = new Engine();

	TiledMap map;
	OrthographicCamera camera;
	OrthogonalTiledMapRenderer renderer;

	public Engine getEngine()
	{
		return engine;
	}

	@Override
	public void create () {
		MovementSystem movementSystem = new MovementSystem();
		engine.addSystem(movementSystem);

		InputSystem inputSystem = new InputSystem();
		engine.addSystem(inputSystem);

		PlayerSystem playerSystem = new PlayerSystem();
		engine.addSystem(playerSystem);

		//player = createPlayer();

		//setScreen(new MainMenu(this));

		//camera = new OrthographicCamera();
		//camera.setToOrtho(false, 2268, 1200);

		//map = new TmxMapLoader().load("mainmenu_sample.tmx");

		//renderer = new OrthogonalTiledMapRenderer(map);

		//mapRenderer.setView(camera);

		setScreen(new PlayScreen(this));

		//Gdx.graphics.wait();
	}

	private Entity createPlayer() {
		Entity player = new Entity();

		player.add(new PlayerComponent());
		player.add(new PositionComponent());
		player.add(new VelocityComponent());
		player.add(new InputComponent());
		//player.add(new TextureComponent("spacesoldier.png"));

		engine.addEntity(player);

		return player;
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

		engine.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		//assetManager.dispose();
	}
}
