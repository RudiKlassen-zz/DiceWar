package com.mopix.diewar.graphics.level.hexagon;

import com.badlogic.gdx.graphics.Color;

class Hexagon {

    private Color color;

    Hexagon(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}