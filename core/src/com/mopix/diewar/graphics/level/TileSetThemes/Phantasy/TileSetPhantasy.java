package com.mopix.diewar.graphics.level.TileSetThemes.Phantasy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mopix.diewar.Utility;
import com.mopix.diewar.graphics.level.TileSetThemes.CellType;
import com.mopix.diewar.graphics.level.TileSetThemes.TileSetTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.mopix.diewar.graphics.level.TileSetThemes.CellType.*;

public class TileSetPhantasy extends TileSetTheme {

    private final static Logger LOGGER = LoggerFactory.getLogger(TileSetPhantasy.class);

    public TileSetPhantasy(String textureFilePath, int tileWidth, int tileHeight, int tileTextureWidth, int tileTextureHeight) {
        super(textureFilePath, tileWidth, tileHeight, tileTextureWidth, tileTextureHeight);
        Utility.loadTextureAsset(textureFilePath);
        initTextureRegions();
    }

    private void initTextureRegions() {
        //init textureregions
        Texture textureAsset = Utility.getTextureAsset(textureFilePath);
        if (textureAsset == null) {
            throw new IllegalStateException("TileSetPhantasy Asset is not loaded");
        }
        addTextureRegion(tileMap, GRASSLAND, textureAsset, 0, 0);
        addTextureRegion(tileMap, WOOD_LIGHT, textureAsset, 1, 0);
        addTextureRegion(tileMap, WOOD, textureAsset, 2, 0);
    }

    void addTextureRegion(Map<CellType, TextureRegion> tileMap, CellType cellType, Texture texture, int x, int y) {
        if (tileMap.containsKey(cellType)) {
            LOGGER.debug("Tilemap already contains key" + cellType);
            return;
        }

        checkCoordinatesWithinTextureSize(texture, y, x);

        tileMap.put(cellType, new TextureRegion(texture, (x * tileWidth), (y * tileHeight), tileTextureWidth, tileTextureHeight));
    }

    void checkCoordinatesWithinTextureSize(Texture texture, int y, int x) {
        if (texture.getHeight() >= (y * tileTextureHeight) &&
                texture.getWidth() >= (x * tileTextureWidth)) {
            return;
        }

        throw new IllegalStateException("Coordinate of Tile is outside the texture");
    }

    @Override
    public void dispose() {
        Utility.unloadAsset(textureFilePath);
    }
}
