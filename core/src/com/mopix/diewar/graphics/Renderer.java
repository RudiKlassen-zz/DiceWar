package com.mopix.diewar.graphics;

import com.badlogic.gdx.ApplicationAdapter;
import com.mopix.diewar.graphics.level.hexagon.WorldManager;

//TODO rukl Interaceimplementierung für die Gamerlogic
public class Renderer extends ApplicationAdapter {

    private final int viewportWidth;

    private final int viewportHeight;

    private WorldManager worldManager;

    public Renderer(int displayWidth, int displayHeight) {
        this.viewportWidth = displayWidth;
        this.viewportHeight = displayHeight;
    }

    @Override
    public void create() {
        worldManager = new WorldManager(viewportWidth, viewportHeight);
    }

    @Override
    public void resize(int width, int height) {
        worldManager.resize();
    }

    public void update() {
    }

    @Override
    public void render() {
        worldManager.render();
    }

    @Override
    public void dispose() {
        worldManager.dispose();
    }

}
