package com.mopix.diewar.graphics.level.hexagon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;

public class WorldController {

    private final Pixmap pixmap;
    private int width, height;

    private boolean modified;

    private Hexagon[][] config;

    private PolygonSprite[][] sprites;


    public WorldController(int width, int height) {
        this.width = width;
        this.height = height;
        config = new Hexagon[width][height];
        sprites = new PolygonSprite[width][height];

        this.pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fillRectangle(0, 0, 32, 32);

        for (int x = 0; x < config.length; x++) {
            for (int y = 0; y < config[0].length; y++) {
                sprites[x][y] = createPolygonSprite(x * 100, y * 100);
                modified = true;
            }
        }
    }

    public void setColorOn(int positionX, int positionY, Color color) {

        if (!checkPositionIsValid(positionX, positionY)) {
            return;
        }

        Hexagon hexagon = config[positionX][positionY];
        if (hexagon != null) {
            hexagon.setColor(color);
            modified = true;
        }
    }

    public void add(int positionX, int positionY, Color color) {

        if (!checkPositionIsValid(positionX, positionY)) {
            return;
        }

        Hexagon hexagon = config[positionX][positionY];
        if (hexagon != null) {
            hexagon.setColor(color);
        } else {
            config[positionX][positionY] = new Hexagon(color);
        }
        modified = true;
    }

    public void remove(int positionX, int positionY) {

        if (!checkPositionIsValid(positionX, positionY)) {
            return;
        }

        config[positionX][positionY] = null;
        modified = true;
    }

    private boolean checkPositionIsValid(int positionX, int positionY) {
        return positionX > 0 && positionX < width && positionY > 0 && positionY < height;
    }

    Color[] colors = {Color.RED, Color.GOLD, Color.GRAY, Color.GREEN, Color.BLACK};
    public void update() {

        int xx = 0 + (int) (Math.random() * 100);
        int yx = 0 + (int) (Math.random() * 100);
        int c = 0 + (int) (Math.random() * colors.length);

        add(xx, yx,colors[c]);

        xx = 0 + (int) (Math.random() * 100);
        yx = 0 + (int) (Math.random() * 100);
        remove(xx, yx);

        for (int x = 0; x < config.length; x++) {
            for (int y = 0; y< config[0].length; y++) {
                Hexagon hexagon = config[x][y];

                if (hexagon == null) {
                    sprites[x][y] = null;
                    continue;
                }

                if (sprites[x][y] == null) {
                    sprites[x][y] = createPolygonSprite(x * 30, y * 30);
                }

                sprites[x][y].setColor(hexagon.getColor());
            }
        }
        modified = false;
    }

    private PolygonSprite createPolygonSprite(int positionX, int positionY) {

        int hexWidth = 32;
        int hexHeight = 32;

        float[] vertices = {
                positionX + hexWidth / 2, positionY,
                positionX + hexWidth, positionY + hexHeight * .25f,
                positionX + hexWidth, positionY + hexHeight * .75f,
                positionX + hexWidth / 2, positionY + hexHeight,
                positionX, positionY + hexHeight * .75f,
                positionX, positionY + hexHeight * .25f
        };

        Texture texture = new Texture(pixmap);
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        PolygonRegion polygonRegion = new PolygonRegion(
                new TextureRegion(new Texture(pixmap)),
                vertices,
                new EarClippingTriangulator().computeTriangles(vertices).toArray());
        PolygonSprite polygonSprite = new PolygonSprite(polygonRegion);
        polygonSprite.setOrigin(positionX + 100 / 2, positionY + 100 / 2);
        return polygonSprite;
    }

    public PolygonSprite[][] getSprites() {
        return sprites;
    }

}
