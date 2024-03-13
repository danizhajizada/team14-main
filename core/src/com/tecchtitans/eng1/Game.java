package com.tecchtitans.eng1;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter {
	Engine engine = new Engine();

	Entity player;

	SpriteBatch batch;


	@Override
	public void create () {
		batch = new SpriteBatch();

		MovementSystem movementSystem = new MovementSystem();
		engine.addSystem(movementSystem);

		InputSystem inputSystem = new InputSystem();
		engine.addSystem(inputSystem);

		PlayerSystem playerSystem = new PlayerSystem();
		engine.addSystem(playerSystem);

		player = createPlayer();
	}

	private Entity createPlayer() {
		Entity player = new Entity();

		player.add(new PlayerComponent());
		player.add(new PositionComponent());
		player.add(new VelocityComponent());
		player.add(new InputComponent());
		player.add(new TextureComponent());

		player.getComponent(TextureComponent.class).texture = new Texture("badlogic.jpg");

		engine.addEntity(player);

		return player;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 1, 0, 1);

		Texture playerTexture = player.getComponent(TextureComponent.class).texture;
		PositionComponent playerPosition = player.getComponent(PositionComponent.class);

		batch.begin();
		batch.draw(player.getComponent(TextureComponent.class).texture, playerPosition.x, playerPosition.y);
		batch.end();

		engine.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
