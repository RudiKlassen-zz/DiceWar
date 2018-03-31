package com.mopix.diewar.graphics.level;

import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class LevelManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(LevelManager.class);

    private Level level;

    public LevelManager(int levelWidth, int levelHeight) {
        level = new Level(levelWidth, levelHeight);
    }

    private void generateMap(){
        throw new NotImplementedException();
    }


}
