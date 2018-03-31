package com.mopix.diewar.graphics.level.TileSetThemes.Phantasy;

import com.badlogic.gdx.graphics.Color;
import com.mopix.diewar.graphics.level.TileSetThemes.CellType;

public class LevelField {

    private CellType type;

    private float opacity;

    private boolean visible;

    private Color color;

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
