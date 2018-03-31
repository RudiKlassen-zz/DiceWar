package com.mopix.diewar.graphics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.mopix.diewar.graphics.level.LevelManager;
import com.mopix.diewar.graphics.level.TileSetThemes.Phantasy.TileSetPhantasy;

import static com.mopix.diewar.Config.*;

public class Renderer extends ApplicationAdapter {

    private OrthographicCamera mainCamera;

    private LevelManager levelManager;

    private MapRenderer mapRenderer;

    public Renderer() {
        TileSetPhantasy tileSetPhantasy = new TileSetPhantasy("hexagonTileset8x5.png", PHANTASY_TILE_WIDTH, PHANTASY_TILE_HEIGHT, PHANTASY_TILE_TEXTURE_WIDTH, PHANTASY_TILE_TEXTURE_HEIGHT);
        levelManager = new LevelManager(tileSetPhantasy, 1000, 1000);
        mapRenderer = new HexagonalTiledMapRenderer(levelManager.generateMap());
    }

    @Override
    public void create() {
        int levelWidth = 1000;
        int levelHeight = 1000;
        float hexagonSize = calculateHexagonSize(levelWidth, levelHeight);

        //  level = new Level(levelWidth, levelHeight, hexagonSize);
        mainCamera = new OrthographicCamera();
        mainCamera.setToOrtho(false, levelWidth, levelHeight);
    }

    /**
     * TODO
     *
     * @param levelWidth
     * @param levelHeight
     * @return
     */
    private float calculateHexagonSize(int levelWidth, int levelHeight) {
        int max = Math.max(levelHeight, levelWidth);
        return 1; //TODO correct when field selection is implemented
    }

    @Override
    public void render() {
        mapRenderer.setView(mainCamera);
        mapRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {

    }

    public void update() {

    }
}
