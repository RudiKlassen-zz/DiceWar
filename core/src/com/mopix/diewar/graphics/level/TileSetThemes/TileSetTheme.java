package com.mopix.diewar.graphics.level.TileSetThemes;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;

public abstract class TileSetTheme implements Disposable {

    protected final String textureFilePath;
    protected final int tileWidth;
    protected final int tileHeight;
    protected final int tileTextureWidth;
    protected final int tileTextureHeight;

    protected Map<CellType, TextureRegion> tileMap = new HashMap<CellType, TextureRegion>();

    public TileSetTheme(String textureFilePath, int tileWidth, int tileHeight, int tileTextureWidth, int tileTextureHeight) {
        this.textureFilePath = textureFilePath;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tileTextureWidth = tileTextureWidth;
        this.tileTextureHeight = tileTextureHeight;
    }

    public TextureRegion getCell(CellType cellType) {
        return tileMap.get(cellType);
    }
}
