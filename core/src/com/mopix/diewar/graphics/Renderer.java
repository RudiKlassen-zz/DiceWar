package com.mopix.diewar.graphics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector3;
import com.mopix.diewar.Config;
import com.mopix.diewar.Utility;
import com.mopix.diewar.graphics.level.Level;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Renderer extends ApplicationAdapter {


    private Level level;

    MapRenderer sdf;

    private OrthographicCamera mainCamera;

    public Renderer() {
        Utility.loadTextureAsset("hexagonTileset8x5.png");
        Texture textureAsset = Utility.getTextureAsset("hexagonTileset8x5.png");
        TextureRegion texture_region = new TextureRegion(textureAsset, 32,0,32, 48);


        TiledMap map = new TiledMap();
        MapLayers layers = map.getLayers();

        TiledMapTileLayer layer1 = new TiledMapTileLayer(1000, 1000, 32, 30);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();

        cell.setTile(new StaticTiledMapTile(texture_region));
        layer1.setCell(2, 1, cell);
        layer1.setCell(1, 1, cell);
        layer1.setCell(3, 1, cell);
        layer1.setCell(3, 2, cell);

        layers.add(layer1);

        sdf = new HexagonalTiledMapRenderer(map);

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
        sdf.setView(mainCamera);
        sdf.render();
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
