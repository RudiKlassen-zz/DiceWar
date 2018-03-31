package com.mopix.diewar.graphics.level;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.mopix.diewar.graphics.level.TileSetThemes.CellType;
import com.mopix.diewar.graphics.level.TileSetThemes.TileSetTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class LevelManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(LevelManager.class);

    private TileSetTheme tileSetTheme;
    private final int levelWidth;
    private final int levelHeight;

    private MapRenderer mapRenderer;

    private TiledMap tiledMap;

    public LevelManager(TileSetTheme tileSetTheme, int levelWidth, int levelHeight) {
        this.tileSetTheme = tileSetTheme;
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        generateMap();
    }

    /**
     * TODO implement doc and tests
     * @return
     */
    public TiledMap generateMap(){

        tiledMap  = new TiledMap();
        TiledMapTileLayer layer1 = new TiledMapTileLayer(levelWidth, levelHeight, 32, 30);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        cell.setTile(new StaticTiledMapTile(tileSetTheme.getCell(CellType.GRASSLAND)));

        layer1.setCell(2, 1, cell);
        layer1.setCell(1, 1, cell);
        layer1.setCell(3, 1, cell);
        layer1.setCell(3, 2, cell);
        tiledMap.getLayers().add(layer1);
        return tiledMap;
    }
}
