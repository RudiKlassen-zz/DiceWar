package com.mopix.diewar.graphics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mopix.diewar.Config;
import com.mopix.diewar.graphics.level.Level;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Renderer extends ApplicationAdapter {

    private ModelBatch modelBatch = new ModelBatch();

    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    private Environment environment = new Environment();

    private Color waterColorFlowTop = new Color(13f / 255f, 25f / 255f, 51f / 255f, 1f);

    private Color waterColorFlowBottom = new Color(13f / 255f, 25f / 255f, 51f / 255f, 1f);

    private Level level;

    private OrthographicCamera mainCamera;

    @Override
    public void create() {
        level = new Level(100, 100, Config.HEXAGON_SIZE);
        mainCamera = new OrthographicCamera();
        mainCamera.setToOrtho(true, level.getWidth(), level.getHeight());
    }

    @Override
    public void render() {
        modelBatch.begin(mainCamera);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(Color.RED);
        // bottom_left, bottom_right, top_right, top_left
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), waterColorFlowTop, waterColorFlowTop, waterColorFlowBottom,
                waterColorFlowBottom);
        shapeRenderer.end();

        modelBatch.render(level.getLevelProvider());
        modelBatch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {

    }

    public void update() {

    }
}
