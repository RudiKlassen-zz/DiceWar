package com.mopix.diewar.graphics.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

public class LevelChunk {

    static final int VERTEXSIZE = 10;
    private static final Vector3 NORMAL_Y_POSITIVE = new Vector3(0, 0, 1);
    private final int fromX;
    private final int fromY;

    private final int width;
    private final int height;

    private final int size = 12;
    private final int half = 6;
    private final int tripple = 4;

    private final Vector3 position1 = new Vector3(half, -size, 0);
    private final Vector3 position2 = new Vector3(size, -8, 0);
    private final Vector3 position3 = new Vector3(size, -4, 0);
    private final Vector3 position4 = new Vector3(half, 0, 0);
    private final Vector3 position5 = new Vector3(0, -4, 0);
    private final Vector3 position6 = new Vector3(0, -8, 0);

    private final Color hexagonMap[][];
    Color[] colors = {Color.GREEN, Color.YELLOW, Color.RED};
    private boolean modified = true;

    LevelChunk(int fromX, int fromY, int toX, int toY, int hexagonSize) {
        this.fromX = fromX;
        this.fromY = fromY;

        this.width = toX - fromX;
        this.height = toY - fromY;

        //TODO rukl init hexagonSize and positions one to six
        hexagonMap = new Color[width][height];
    }

    public int build(float[] vertices) {

        int vertexOffset = 0;

        for (int x = 0; x < hexagonMap.length; x++) {
            for (int y = 0; y < hexagonMap[0].length; y++) {
                Color hexagonColor = hexagonMap[x][y];

                if (hexagonColor == null) {
                    // continue;
                }

                int startPositionX = (fromX + x) * size;
                int startPositionY = (fromY + y) * size;
                int startPositionZ = 0;
                vertexOffset = addHexagon(vertices, vertexOffset, startPositionX, startPositionY, startPositionZ, colors[(int) (Math.random() * colors.length)]);
            }
        }

        modified = false;
        return vertexOffset / 10;
    }


    private int addHexagon(float[] vertices, int vertexOffset, int x, int y, int z, Color color) {

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
