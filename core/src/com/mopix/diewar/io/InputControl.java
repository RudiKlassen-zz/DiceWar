package com.mopix.diewar.io;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class InputControl extends InputAdapter {

    public static boolean SPACE;

    public static boolean UP, LEFT, DOWN, RIGHT;

    public static boolean MOUSE_CLICKED_LEFT;

    public boolean keyDown(int k) {
        if (k == Input.Keys.LEFT) {
            LEFT = true;
            RIGHT = UP = DOWN = false;
        }
        if (k == Input.Keys.RIGHT) {
            RIGHT = true;
            LEFT = UP = DOWN = false;
        }
        if (k == Input.Keys.UP) {
            UP = true;
            RIGHT = LEFT = DOWN = false;
        }
        if (k == Input.Keys.DOWN) {
            DOWN = true;
            RIGHT = UP = LEFT = false;
        }
        if (k == Input.Keys.SPACE) {
            SPACE = true;
        }

        return true;
    }

    public boolean keyUp(int k) {
        if (k == Input.Keys.LEFT) {
            LEFT = false;
        }
        if (k == Input.Keys.RIGHT) {
            RIGHT = false;
        }
        if (k == Input.Keys.UP) {
            UP = false;
        }
        if (k == Input.Keys.DOWN) {
            DOWN = false;
        }
        if (k == Input.Keys.SPACE) {
            SPACE = false;
        }

        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //TODO rukl hier weitermachen, sobald Maus-Klick benötigt
//        MOUSE_CLICKED_LEFT = true;
//        if(button == Input.Buttons.LEFT){
//            posX = screenX - sprite.getWidth()/2;
//            posY = Gdx.graphics.getHeight() - screenY - sprite.getHeight()/2;
//        }
//        if(button == Buttons.RIGHT){
//            posX = Gdx.graphics.getWidth()/2 - sprite.getWidth()/2;
//            posY = Gdx.graphics.getHeight()/2 - sprite.getHeight()/2;
//        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        MOUSE_CLICKED_LEFT = false;
        return super.touchUp(screenX, screenY, pointer, button);

    }
}