package com.mopix.diewar.game;

import com.badlogic.gdx.utils.Disposable;
import com.mopix.diewar.graphics.Renderer;
import com.mopix.diewar.graphics.TileManagerI;

public class GameMaster implements Disposable{

    private TileManagerI tileManager;

    public GameMaster(TileManagerI tileManager) {
        this.tileManager = tileManager;

    }

    @Override
    public void dispose() {

    }

}
