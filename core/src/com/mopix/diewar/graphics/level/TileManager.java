package com.mopix.diewar.graphics.level;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.mopix.diewar.graphics.level.TileSetThemes.CellType;
import com.mopix.diewar.graphics.level.TileSetThemes.TileSetTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Kennt und verwaltet alle Tiles eines Chunk.
 */
public class TileManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TileManager.class);

    public static final int TILE_WIDTH = 32;

    public static final int TILE_HEIGHT = 30;

    public static final TiledMapTileLayer.Cell EMPTY_CELL = null;

    private TileSetTheme tileSetTheme;

    private TiledMap tiledMap;

    private final TiledMapTileLayer tiledMapTileLayer;

    private final int levelWidth;

    private final int levelHeight;

    private final TiledMapTileLayer.Cell cell;

    public TileManager(TileSetTheme tileSetTheme, int levelWidth, int levelHeight) {
        this.tileSetTheme = tileSetTheme;
        this.tiledMap = new TiledMap();
        tiledMapTileLayer = createTileMapTileLayer(levelWidth, levelHeight);
        this.levelWidth = 53;
        this.levelHeight = 29;

        cell = new TiledMapTileLayer.Cell();
        cell.setTile(new StaticTiledMapTile(tileSetTheme.getCell(CellType.GRASSLAND)));
        tiledMap.getLayers().add(tiledMapTileLayer);
    }

    /**
     * Generiert für die angegebene Breite und Höhe eine passende Tile-Layer ({@link TiledMapTileLayer})
     *
     * @param levelWidth  Breite in Pixel
     * @param levelHeight Höhe in Pixel
     * @return TiledMapTileLayer
     */
    private TiledMapTileLayer createTileMapTileLayer(int levelWidth, int levelHeight) {
        return new TiledMapTileLayer(levelWidth, levelHeight, TILE_WIDTH, TILE_HEIGHT);
    }

    /**
     * Entfernt eine Zelle {@link TiledMapTileLayer.Cell} an der angegebenen Position, sofern sich an dieser Stelle eine Zelle befindet.
     * Wenn sich die angegeben Position außerhalb der Tile-Map Grenzen befindet, wird nichts getan.
     *
     * @param positionX Position in der Breite
     * @param positionY Position in der Höhe
     */
    public void removeTileOn(int positionX, int positionY) {
        if (!positionInMap(positionX, positionY)) {
            return;
        }

        tiledMapTileLayer.setCell(positionX, positionY, EMPTY_CELL);
    }

    /**
     * Fügt eine Zelle {@link TiledMapTileLayer.Cell} an der angegebenen Position hinzu, sofern diese sich innerhalb der Tile-Map Grenzen bewegt.
     * Andernfalls wird nichts gemacht.
     *
     * @param positionX Position in der Breite
     * @param positionY Position in der Höhe
     */
    public void addTileOn(int positionX, int positionY) {
        if (!positionInMap(positionX, positionY)) {
            return;
        }

        tiledMapTileLayer.setCell(positionX, positionY, cell);
    }

    /**
     * Prüft ob sich die angegebene Position innerhalb der Tile-Map Grenzen befindet.
     *
     * @param positionX Position in der Breite
     * @param positionY Position in der Höhe
     * @return True wenn ja, andernfalls false
     */
    private boolean positionInMap(int positionX, int positionY) {
        if (positionX >= 0 && positionX < levelWidth) {
            return positionY >= 0 && positionY < levelHeight;
        }

        return false;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public int getLevelWidth() {
        return levelWidth;
    }

    public int getLevelHeight() {
        return levelHeight;
    }
}
