package com.mopix.diewar.game;

import com.badlogic.gdx.utils.Disposable;
import com.mopix.diewar.graphics.Renderer;

public class GameMaster implements Disposable{

    private Renderer renderer;

    public GameMaster(){
        renderer = new Renderer();
        renderer.create();
    }

    public void render(){
        renderer.update();
        renderer.render();
    }

    @Override
    public void dispose() {

    }

}
