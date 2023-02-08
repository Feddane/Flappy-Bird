package com.flappybird.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class flappybird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture[] birds;
	Texture toptube, bottomtube;

	int flapState = 0;
	float birdY = 0;
	int velocity = 0; //how fast the bird is moving

	int gameState = 0; //bird in the middle until it's touched

	float gravity = 2;

	float maxTubeOffset;

	float gap = 400;

	Random randomGenerator;

	float tubeVelocity = 4;

	int numberOfTubes = 4;

	float[] tubeX = new float[numberOfTubes];

	float[] tubeOffset = new float[numberOfTubes];

	float distanceBetweenTubes;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");

		toptube = new Texture("toptube.png");
		bottomtube = new Texture("bottomtube.png");
		maxTubeOffset = Gdx.graphics.getHeight() / 2 - gap / 2 - 100;
		randomGenerator = new Random();
		distanceBetweenTubes = Gdx.graphics.getWidth() * 3 / 4;

		birds = new Texture[2];
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");
		birdY = Gdx.graphics.getHeight()/2 - birds[flapState].getHeight() / 2;

		for (int i = 0; i < numberOfTubes; i++){
			tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);

			tubeX[i] = Gdx.graphics.getWidth() / 2 - toptube.getWidth() / 2 + i * distanceBetweenTubes;
		}

	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		if(gameState != 0 ) {

			if (Gdx.input.justTouched()){
				velocity = -30;

			}

			for (int i = 0; i < numberOfTubes; i++) {

				if (tubeX[i] < -toptube.getWidth()){

					tubeX[i] += numberOfTubes * distanceBetweenTubes;
				}else{

				tubeX[i] -= tubeVelocity;
				}
				batch.draw(toptube, tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i]);
				batch.draw(bottomtube, tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomtube.getHeight() + tubeOffset[i]);

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
		batch.draw(birds[flapState], Gdx.graphics.getWidth() / 2 - birds[flapState].getWidth() / 2, birdY);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		birds[flapState].dispose();
		toptube.dispose();
		bottomtube.dispose();
	}
}
