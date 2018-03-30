package com.mopix.diewar.graphics.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

public class LevelChunk {

    static final int VERTEXSIZE = 10;
    private static final Vector3 NORMAL_Y_POSITIVE = new Vector3(0, 0, -1);//TODO check if this is correct, correct naming
    private final int fromX;
    private final int fromY;

    private final int width;
    private final int height;


    private float hexagonWidht = 2;
    private float hexagonWidhtHalf = 1;

    private float hexagonHeight = 3;
    private float hexagonHeightTripple = 1;


    //TODO static
    private  Vector3 position1 = new Vector3(hexagonWidhtHalf, 0, 0);
    private  Vector3 position2 = new Vector3(hexagonWidht, hexagonHeightTripple, 0);
    private  Vector3 position3 = new Vector3(hexagonWidht, 2 * hexagonHeightTripple, 0);
    private  Vector3 position4 = new Vector3(hexagonWidhtHalf, hexagonHeight, 0);
    private  Vector3 position5 = new Vector3(0, 2 * hexagonHeightTripple, 0);
    private  Vector3 position6 = new Vector3(0, hexagonHeightTripple, 0);

    private final Color hexagonMap[][];
    Color[] colors = {Color.GREEN, Color.YELLOW, Color.RED};
    private boolean modified = true;

    LevelChunk(int fromX, int fromY, int toX, int toY, float hexagonSize) {

        calculateHexagonSize(hexagonSize);
        this.fromX = fromX;
        this.fromY = fromY;

        this.width = toX - fromX;
        this.height = toY - fromY;

        //TODO rukl init hexagonSize and positions one to six
        hexagonMap = new Color[width][height];
    }

    private void calculateHexagonSize(float hexagonSize) {

         int APPLICATION_WIDTH = 100;
         int APPLICATION_HEIGHT = 50;

        float aspectRatio = (float) APPLICATION_WIDTH / (float) APPLICATION_HEIGHT;
        hexagonWidht = hexagonSize * aspectRatio;
         hexagonWidhtHalf = hexagonWidht / 2;

       hexagonHeight = hexagonWidhtHalf * 2;
        hexagonHeightTripple = hexagonWidhtHalf;

         position1 = new Vector3(hexagonWidhtHalf, 0, 0);
       position2 = new Vector3(hexagonWidht, hexagonHeightTripple, 0);
        position3 = new Vector3(hexagonWidht, 2 * hexagonHeightTripple, 0);
        position4 = new Vector3(hexagonWidhtHalf, hexagonHeight, 0);
       position5 = new Vector3(0, 2 * hexagonHeightTripple, 0);
         position6 = new Vector3(0, hexagonHeightTripple, 0);

    }


    public int build(float[] vertices) {

        int vertexOffset = 0;

        for (int x = 0; x < hexagonMap.length; x++) {
            for (int y = 0; y < hexagonMap[0].length; y++) {
                Color hexagonColor = hexagonMap[x][y];

                if (hexagonColor == null) {
                    // continue;
                }

                Color color = colors[(int) (Math.random() * colors.length)];
                if (y == 0 || y == (hexagonMap[0].length - 1)) {
                    color = Color.DARK_GRAY;
                }

                if (x == 0 || x == (hexagonMap.length - 1)) {
                    color = Color.PINK;
                }
                float startPositionX = (fromX + x) * hexagonWidht;
                float startPositionY = (fromY + y) * hexagonHeight;
                int startPositionZ = 0;
                vertexOffset = addHexagon(vertices, vertexOffset, startPositionX, startPositionY, startPositionZ, color);
            }
        }

        modified = false;
        return vertexOffset / 10;
    }

    private int addHexagon(float[] vertices, int vertexOffset, float x, float y, float z, Color color) {

        vertexOffset = addVertex(vertices, vertexOffset, new Vector3(position6.x + x, position6.y + y, position6.z + z), color);
        vertexOffset = addVertex(vertices, vertexOffset, new Vector3(position5.x + x, position5.y + y, position5.z + z), color);
        vertexOffset = addVertex(vertices, vertexOffset, new Vector3(position4.x + x, position4.y + y, position4.z + z), color);
        vertexOffset = addVertex(vertices, vertexOffset, new Vector3(position3.x + x, position3.y + y, position3.z + z), color);
        vertexOffset = addVertex(vertices, vertexOffset, new Vector3(position2.x + x, position2.y + y, position2.z + z), color);
        vertexOffset = addVertex(vertices, vertexOffset, new Vector3(position1.x + x, position1.y + y, position1.z + z), color);
        return vertexOffset;
    }

    private int addVertex(float[] vertices, int vertexOffset, Vector3 position, Color color) {
        vertices[vertexOffset++] = position.x;
        vertices[vertexOffset++] = position.y;
        vertices[vertexOffset++] = position.z;
        vertices[vertexOffset++] = NORMAL_Y_POSITIVE.x;
        vertices[vertexOffset++] = NORMAL_Y_POSITIVE.y;
        vertices[vertexOffset++] = NORMAL_Y_POSITIVE.z;
        vertices[vertexOffset++] = color.r;
        vertices[vertexOffset++] = color.g;
        vertices[vertexOffset++] = color.b;
        vertices[vertexOffset++] = color.a;
        return vertexOffset;
    }

    public boolean isModified() {
        return modified;
    }
}
