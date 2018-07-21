package com.mopix.diewar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mopix.diewar.graphics.Renderer;

public class DiceWar extends ApplicationAdapter {

    private Renderer tileManager;

    public void create() {
        tileManager = new Renderer(CONFIG.APPLICATION_WIDTH, CONFIG.APPLICATION_HEIGHT);
        tileManager.create();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tileManager.update();
        tileManager.render();
    }

    @Override
    public void dispose() {
        //TODO rukl
    }
}
