package com.mopix.diewar.graphics.level;

/**
 * TODO doc and tests
 */
public class Level {

    private int width;

    private int height;

    private LevelType[][] levelMap;


    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        levelMap = new LevelType[width][height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
