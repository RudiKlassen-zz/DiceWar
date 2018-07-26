package com.mopix.diewar.graphics.level.hexagon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Vector2;

public class WorldController {

    private final Pixmap pixmap;
    private int width, height;
    private int hexagonWidth = 32, hexagonHeight = 32;

    private boolean modified;

    private Hexagon[][] config;

    private PolygonSprite[][] sprites;

    public WorldController(int width, int height) {
        this.width = width;
        this.height = height;
        config = new Hexagon[width][height];
        sprites = new PolygonSprite[width][height];

        this.pixmap = new Pixmap(hexagonWidth, hexagonHeight, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fillRectangle(0, 0, hexagonWidth, hexagonHeight);

        for (int x = 0; x < config.length; x++) {
            for (int y = 0; y < config[0].length; y++) {
                sprites[x][y] = createPolygonSprite(calcPosition(x, y));
                modified = true;
            }
        }
    }

    private Vector2 calcPosition(int x, int y) {

//
//        float xOffset = evenNumberX ? 0 : hexagonWidth * 0.5f;
//        float positionX = (hexagonWidth * x) + xOffset;
//
//        boolean evenNumberY = (y % 2) == 0;
//        float yOffset = evenNumberY ? 0 : hexagonHeight * 0.75f;
//        float positionY = (hexagonHeight * y) + yOffset;

        boolean evenNumberX = (x % 2) == 0;
        // Is the row an odd number?

       float columnOffset = x * 16;
        float positionX = (int)(x + columnOffset)/1; //switch + to - to align the grid the other way

        float asdOffset = y * 16;
        float positionY = (int)(y + asdOffset)/1; //switch + to - to align the grid the other way


        return new Vector2(positionX, positionY);
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

        //TODO just for tests
        int xx = 0 + (int) (Math.random() * 100);
        int yx = 0 + (int) (Math.random() * 100);
        int c = 0 + (int) (Math.random() * colors.length);

        add(xx, yx, colors[c]);

//        xx = 0 + (int) (Math.random() * 100);
//        yx = 0 + (int) (Math.random() * 100);
//        remove(xx, yx);
        ////

        if (!modified) {
            return;
        }

        for (int x = 0; x < config.length; x++) {
            for (int y = 0; y < config[0].length; y++) {
                Hexagon hexagon = config[x][y];

                if (hexagon == null) {
                    sprites[x][y] = null;
                    continue;
                }

                if (sprites[x][y] == null) {
                    sprites[x][y] = createPolygonSprite(calcPosition(x, y));
                }

                sprites[x][y].setColor(hexagon.getColor());
            }
        }
        modified = false;
    }

    private PolygonSprite createPolygonSprite(Vector2 position) {

        float[] vertices = {
                position.x + hexagonWidth / 2, position.y,
                position.x + hexagonWidth, position.y + hexagonHeight * .25f,
                position.x + hexagonWidth, position.y + hexagonHeight * .75f,
                position.x + hexagonWidth / 2, position.y + hexagonHeight,
                position.x, position.y + hexagonHeight * .75f,
                position.x, position.y + hexagonHeight * .25f
        };

        Texture texture = new Texture(pixmap);
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        PolygonRegion polygonRegion = new PolygonRegion(
                new TextureRegion(new Texture(pixmap)),
                vertices,
                new EarClippingTriangulator().computeTriangles(vertices).toArray());
        PolygonSprite polygonSprite = new PolygonSprite(polygonRegion);
        polygonSprite.setOrigin(position.x, position.y);
        return polygonSprite;
    }

    public PolygonSprite[][] getSprites() {
        return sprites;
    }

}
