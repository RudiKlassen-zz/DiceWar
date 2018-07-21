package com.mopix.diewar.graphics.themes;

import com.mopix.diewar.graphics.level.TileSetThemes.Phantasy.TileSetPhantasy;

import static com.mopix.diewar.CONFIG.*;

public class TileThemes {

    public static TileSetPhantasy tileSetPhantasy = new TileSetPhantasy(//
            "hexagonTileset8x5.png", //
            PHANTASY_TILE_WIDTH, //
            PHANTASY_TILE_HEIGHT, //
            PHANTASY_TILE_TEXTURE_WIDTH, //
            PHANTASY_TILE_TEXTURE_HEIGHT);

    public static TileSetPhantasy getTileSetPhantasy() {
        return tileSetPhantasy;
    }

    public static void setTileSetPhantasy(TileSetPhantasy tileSetPhantasy) {
        TileThemes.tileSetPhantasy = tileSetPhantasy;
    }
}
