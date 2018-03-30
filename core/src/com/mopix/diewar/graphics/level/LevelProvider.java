package com.mopix.diewar.graphics.level;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.RenderableProvider;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class LevelProvider implements RenderableProvider {

    private LevelChunk levelChunk;

    private float[] vertices;

    private int facesCount;

    private Mesh mesh;

    private Material material;

    //TODO das hier mal ordentlich auf eine hexagon struktur mappen
    LevelProvider(int fromX, int fromY, int width, int height, float hexagonSize) {
        levelChunk = new LevelChunk(fromX, fromY, width, height, hexagonSize);

        int depth = 1;
        int len = width * height * depth *12;
        vertices = new float[width * height * depth *6 *10];
        short[] indices = new short[len];

        for (int i = 0, j = 0; i < (len); i += 12, j += 6) {
            indices[i] = (short) j;
            indices[i + 1] = (short) (j + 1);
            indices[i + 2] = (short) (j + 2);
            indices[i + 3] = (short) (j + 2);
            indices[i + 4] = (short) (j + 3);
            indices[i + 5] = (short) j;
            indices[i + 6] = (short) j;
            indices[i + 7] = (short) (j + 3);
            indices[i + 8] = (short) (j + 4);
            indices[i + 9] = (short) (j + 4);
            indices[i + 10] = (short) (j + 5);
            indices[i + 11] = (short) j;
        }
        mesh = new Mesh(true, width * height * depth * 6, len, VertexAttribute.Position(),
                VertexAttribute.Normal(), VertexAttribute.ColorUnpacked());
        mesh.setIndices(indices);

        material = new Material(new ColorAttribute(ColorAttribute.Diffuse, 1f, 1f, 1f, 1f));
     //   material.set(new IntAttribute(IntAttribute.CullFace, 0));
    }

    @Override
    public void getRenderables(Array<Renderable> renderables, Pool<Renderable> pool) {
        if (levelChunk.isModified()) {
            int vertexCount = levelChunk.build(vertices);
            facesCount = (vertexCount / 6);
            mesh.setVertices(vertices, 0, vertexCount * LevelChunk.VERTEXSIZE);
        }

        if (facesCount != 0) {
            Renderable renderable = pool.obtain();
            renderable.material = material;
            renderable.meshPart.mesh = mesh;
            renderable.meshPart.offset = 0;
            renderable.meshPart.size = facesCount;
            renderable.meshPart.primitiveType = GL20.GL_TRIANGLES;
            renderables.add(renderable);
        }
    }
}
