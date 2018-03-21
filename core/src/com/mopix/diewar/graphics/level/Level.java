package com.mopix.diewar.graphics.level;

public class Level {

    private int width;

    private int height;

    private LevelProvider levelProvider;

    //TODO rukl private Environment environment = new Environment();

    public Level(int width, int height, int hexagonSize) {
        this.width = width;
        this.height = height;
        levelProvider = new LevelProvider(0, 0, width, height, hexagonSize);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public LevelProvider getLevelProvider() {
        return levelProvider;
    }
}
