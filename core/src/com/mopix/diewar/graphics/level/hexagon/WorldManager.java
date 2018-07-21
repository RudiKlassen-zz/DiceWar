package com.mopix.diewar.graphics.level.hexagon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.math.Vector3;


public class WorldManager {

    private final int viewportWidth;
    private final int viewportHeight;
    private OrthographicCamera orthographicCamera;
    private PolygonSpriteBatch polygonSpriteBatch = new PolygonSpriteBatch();
    private WorldController worldController;

    private Environment environment = new Environment();

    public WorldManager(int viewportWidth, int viewportHeight) {
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
        worldController = new WorldController(40, 30);

        initializeCamera();
        initializeEnvironment();
    }

    private void initializeEnvironment() {

        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1.0f));
        environment.set(new ColorAttribute(ColorAttribute.Fog, 0.13f, 0.13f, 0.13f, 1f));

        Vector3 position = new Vector3(10, 15, 10);
        Color pointLightColor = new Color(0xe5ee94);
        environment.add(new PointLight().set(pointLightColor, position, 70f));

        Color directionalLightColor1 = new Color(0x8cc8b6ff);
        environment.add(new DirectionalLight().set(directionalLightColor1.r, directionalLightColor1.g, directionalLightColor1.b, 0.6f, -0.8f, 0.4f));
    }

    private void initializeCamera() {
        orthographicCamera = new OrthographicCamera(1280, 900);
        orthographicCamera.setToOrtho(true);
        orthographicCamera.position.set(orthographicCamera.viewportWidth / 2f, orthographicCamera.viewportHeight / 2f, 0);
        orthographicCamera.update();
    }

    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 1, 1, 1);

        orthographicCamera.update();
        polygonSpriteBatch.setProjectionMatrix(orthographicCamera.combined);

        worldController.update();
        PolygonSprite[][] sprites = worldController.getSprites();
        polygonSpriteBatch.begin();
        for (int x = 0; x < sprites.length; x++) {
            for (int y = 0; y < sprites[0].length; y++) {
                if (sprites[x][y] != null) {
                    sprites[x][y].draw(polygonSpriteBatch);
                }
            }
        }
        polygonSpriteBatch.end();
    }

    public void resize() {
        initializeCamera();
    }

    public void dispose() {

    }

}
