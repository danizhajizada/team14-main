package com.tecchtitans.eng1;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
	Engine engine;
	OrthographicCamera camera;
	FitViewport viewport;
	TiledMap map;
	OrthogonalTiledMapRenderer mapRenderer;

	// Systems
	MovementSystem movementSystem;
	InputSystem inputSystem;
	PlayerSystem playerSystem;

	// Entities
	Entity player;


	public Engine getEngine()
	{
		return engine;
	}

	@Override
	public void create () {
		engine = new Engine();

		movementSystem = new MovementSystem();
		engine.addSystem(movementSystem);

		inputSystem = new InputSystem();
		engine.addSystem(inputSystem);

		playerSystem = new PlayerSystem();
		engine.addSystem(playerSystem);

		player = createPlayer();

		//setScreen(new MainMenu(this));

		//camera = new OrthographicCamera(2320, 1280);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 2320, 1280);

		map = new TmxMapLoader().load("mainmenu_sample.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		//viewport = new FitViewport(2320, 1280, camera);

		//mapRenderer.setView(camera);
	}

	private Entity createPlayer() {
		Entity player = new Entity();


		/*player.add(new PlayerComponent());
		player.add(new PositionComponent());
		player.add(new VelocityComponent());
		player.add(new InputComponent());
		player.add(new TextureComponent("spacesoldier.png"));*/

		player.add(engine.createComponent(PlayerComponent.class));
		player.add(engine.createComponent(PositionComponent.class));
		player.add(engine.createComponent(VelocityComponent.class));
		player.add(engine.createComponent(InputComponent.class));
		TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
		textureComponent.texture = new Texture("spacesoldier.png");
		player.add(textureComponent);

		engine.addEntity(player);

		return player;
	}

	@Override
	public void render () {
		//ScreenUtils.clear(0, 0, 0.2f, 1);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
		camera.update();
		mapRenderer.setView(camera);
		mapRenderer.render();

		engine.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		//batch.dispose();
	}
}
