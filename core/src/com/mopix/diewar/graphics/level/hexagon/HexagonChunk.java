package com.mopix.diewar.graphics.level.hexagon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Fast ein Anzahl von Hexagons in einem Array zusammen.
 */
public class HexagonChunk {

    /**
     * Anzahl Eckpunkte eines Hexagons
     */
    public static final int VERTEXCOUNT = 6;
    private final int hexagonWidth, hexagonHeight;
    private final int fromX, fromY;
    private final int width, height;

    /**
     * Speichert die Farbe jedes Hexagon im Chunk
     */
    private Color[][] colorMap;

    /**
     * Zeigt an, ob es Änderungem im Chunk gegeben hat (hinzugefügt oder gelöschte Hexagons)
     */
    private boolean modified;

    public HexagonChunk(int fromX, int fromY, int toX, int toY, int hexagonWidth, int hexagonHeight) {
        this.fromX = fromX;
        this.fromY = fromY;

        this.width = toX - fromX;
        this.height = toY - fromY;
        this.hexagonWidth = hexagonWidth;
        this.hexagonHeight = hexagonHeight;

        colorMap = new Color[width][height];

        for (int x = 0; x < colorMap.length; x++) {
            for (int y = 0; y < colorMap[0].length; y++) {
                colorMap[x][y] = Color.RED;
            }
        }

        modified = true;
    }

    public void addOnPosition(int x, int y, Color color) {
        if (colorMap[x][y] != null) {
            return; // Position already taken
        }

        colorMap[x][y] = color;
        modified = true;
    }

    public void removeOnPosition(int x, int y, int z) {
        if (colorMap[x][y] == null) {
            return; // already empty
        }

        colorMap[x][y] = null;
        modified = true;
    }

    public void updateColor(int x, int y, Color color) {
        if (colorMap[x][y] == null) {
            return;
        }

        colorMap[x][y] = color;
        modified = true;
    }

    /**
     * Prüft, ob is die Position (x, y) innerhalb des Chunks befindet
     *
     * @param x Position in der Breite
     * @param y Position in der Höhe
     * @return true, wenn die Position innerhalb des Chunks
     * false, wenn die Position außerhalb des Chunks
     */
    public boolean isPositionAvailable(int x, int y) {
        return x > 0 && y > 0 && x < width && y < height;
    }


    public   List<PolygonSprite> build2() {

        List<PolygonSprite> polygonSprites = new ArrayList<PolygonSprite>();

        for (int x = 0; x < colorMap.length; x++) {
            for (int y = 0; y < colorMap[0].length; y++) {

                Color color = colorMap[x][y];
                if (color == null) {
                    continue;
                }

                int startPositionX = (fromX + x) * hexagonWidth;
                int startPositionY = (fromY + y) * hexagonWidth;
                Vector2 pos = new Vector2(startPositionX, startPositionY);

                float[] asdf =  {
                        pos.x + hexagonWidth / 2, pos.y,
                        pos.x + hexagonWidth, pos.y + hexagonHeight * .25f,
                        pos.x + hexagonWidth, pos.y + hexagonHeight * .75f,
                        pos.x + hexagonWidth / 2, pos.y + hexagonHeight,
                        pos.x, pos.y + hexagonHeight * .75f,
                        pos.x, pos.y + hexagonHeight * .25f
                };



                Texture texture = new Texture(Gdx.files.internal("icon.png"));
                texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

                PolygonRegion polygonRegion = new PolygonRegion(//
                        new TextureRegion(texture),//
                        asdf,//
                        new EarClippingTriangulator().computeTriangles(asdf).toArray());
                PolygonSprite polygonSprite = new PolygonSprite(polygonRegion);
                polygonSprite.setOrigin(pos.x + width / 2, pos.y + height / 2);
                polygonSprites.add(polygonSprite);
            }
        }

        return polygonSprites;

    }

    public int build(float[] vertices) {

        int vertexOffset = 0;

        for (int x = 0; x < colorMap.length; x++) {
            for (int y = 0; y < colorMap[0].length; y++) {

                Color color = colorMap[x][y];
                if (color == null) {
                    continue;
                }

                int startPositionX = (fromX + x) * hexagonWidth;
                int startPositionY = (fromY + y) * hexagonWidth;

                vertexOffset = createTopFace(new Vector2(startPositionX, startPositionY), vertices, vertexOffset, color);
            }
        }

        modified = false;
        return vertexOffset / VERTEXCOUNT;
    }

    private int createTopFace(Vector2 position, float[] vertices, int vertexOffset, Color color) {

        vertices[vertexOffset++] = position.x + hexagonWidth / 2;
        vertices[vertexOffset++] = position.y;
        vertices[vertexOffset++] = 0;

        vertices[vertexOffset++] = position.x + hexagonWidth;
        vertices[vertexOffset++] = position.y + hexagonHeight * 0.25f;
        vertices[vertexOffset++] = 0;

        vertices[vertexOffset++] = position.x + hexagonWidth;
        vertices[vertexOffset++] = position.y + hexagonHeight * 0.75f;
        vertices[vertexOffset++] = 0;

        vertices[vertexOffset++] = position.x + hexagonWidth / 2;
        vertices[vertexOffset++] = position.y + hexagonHeight;
        vertices[vertexOffset++] = 0;

        vertices[vertexOffset++] = position.x;
        vertices[vertexOffset++] = position.y + hexagonHeight * 0.75f;
        vertices[vertexOffset++] = 0;

        vertices[vertexOffset++] = position.x;
        vertices[vertexOffset++] = position.y + hexagonHeight * 0.25f;
        vertices[vertexOffset++] = 0;

        return vertexOffset;
    }

    public boolean isModified() {
        return modified;
    }

    public Color getColorOn(int x, int y) {
        return colorMap[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
