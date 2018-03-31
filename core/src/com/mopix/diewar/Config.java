package com.mopix.diewar;

import com.badlogic.gdx.Application;

public class Config {

    /*Desktop Application*/
    public static final int APPLICATION_WIDTH = 1280;
    public static final int APPLICATION_HEIGHT = 900;
    public static final int LOG_LEVEL = Application.LOG_INFO;
    public static final int BACKGROUND_FPS = 60;
    public static final int FOREGROUND_FPS = 60;
    public static final int SAMPLES = 4;
    /**
     * number of samples for MSAA
     **/
    public static final boolean FULLSCREEN = false;
    public static final boolean V_SYNC_ENABLED = false;

    /*Gamesettings*/
    public static final int HEXAGON_SIZE = 1;

    /* Cell Themes */
    /* Phantasy */
    public static final String PHANTASY_TEXTURE_FILE_PATH = "hexagonTileset8x5.png";
    public static final int PHANTASY_TILE_WIDTH = 32;
    public static final int PHANTASY_TILE_HEIGHT = 30;
    public static final int PHANTASY_TILE_TEXTURE_WIDTH = 32;
    public static final int PHANTASY_TILE_TEXTURE_HEIGHT = 48;
}
