package com.mopix.diewar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mopix.diewar.game.GameMaster;

public class DiceWar extends ApplicationAdapter{

	private GameMaster gameMaster;

	public void create () {
		gameMaster = new GameMaster();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameMaster.render();
	}

	@Override
	public void dispose () {
	}
}
