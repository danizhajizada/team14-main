package com.tecchtitans.eng1;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.tecchtitans.eng1.screens.MainMenu;
import com.tecchtitans.eng1.systems.CollisionSystem;
import com.tecchtitans.eng1.systems.InputSystem;
import com.tecchtitans.eng1.systems.MovementSystem;

public class ENGGame extends Game {
	Engine engine = new Engine();

	public Engine getEngine()
	{
		return engine;
	}

	@Override
	public void create () {
<<<<<<< HEAD
		MovementSystem movementSystem = new MovementSystem();
		engine.addSystem(movementSystem);
=======
		engine = new ECSEngine();

		//MovementSystem movementSystem = new MovementSystem();
		//engine.addSystem(movementSystem);
>>>>>>> joel-worldborder

		InputSystem inputSystem = new InputSystem();
		engine.addSystem(inputSystem);

		PlayerSystem playerSystem = new PlayerSystem();
		engine.addSystem(playerSystem);

		CollisionSystem collisionSystem = new CollisionSystem();
		engine.addSystem(collisionSystem);

<<<<<<< HEAD
=======
		PlayerCollisionSystem playerCollisionSystem =  new PlayerCollisionSystem();
		engine.addSystem(playerCollisionSystem);

		PlayerMovementSystem playerMovementSystem = new PlayerMovementSystem();
		engine.addSystem(playerMovementSystem);

		PlayerCameraSystem playerCameraSystem = new PlayerCameraSystem();
		engine.addSystem(playerCameraSystem);

>>>>>>> joel-worldborder
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
