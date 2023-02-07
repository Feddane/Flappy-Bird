package com.flappybird.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class flappybird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture[] birds;

	int flapState = 0;
	float birdY = 0;
	int velocity = 0; //how fast the bird is moving

	int gameState = 0; //bird in the middle until it's touched

	float gravity = 2;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");

		birds = new Texture[2];
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");
		birdY = Gdx.graphics.getHeight()/2 - birds[flapState].getHeight() / 2;

	}

	@Override
	public void render () {

		if(gameState != 0 ) {
			if (Gdx.input.justTouched()){
				velocity = -30;
			}
			if (birdY > 0 || velocity < 0){
				velocity += gravity;
				birdY -= velocity;
			}
		}else{
			if (Gdx.input.justTouched()){
				gameState = 1;
			}
		}
		if (flapState == 0) {
			flapState = 1;
		} else {
			flapState = 0;
		}
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(birds[flapState], Gdx.graphics.getWidth() / 2 - birds[flapState].getWidth() / 2, birdY);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		birds[flapState].dispose();
	}
}
