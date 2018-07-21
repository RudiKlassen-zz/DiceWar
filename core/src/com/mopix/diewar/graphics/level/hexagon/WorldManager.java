package com.mopix.diewar.graphics.hexagon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mopix.diewar.CONFIG;


public class WorldManager {

    private WorldService worldService = WorldService.getInstance();

    private OrthographicCamera diceWarsCamera;

    private ModelBatch modelBatch = new ModelBatch();

    private Environment environment = new Environment();

    private float stepSize;

    private int maxSteps;

    private Color waterColorFlowTop = new Color(13f / 255f, 25f / 255f, 51f / 255f, 1f);

    private Color waterColorFlowBottom = new Color(13f / 255f, 25f / 255f, 51f / 255f, 1f);

    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    public WorldManager() {

        initializeCamera();
        initializeEnvironment();

        //TODO rukl
//        this.stepSize = CONFIG.HEXAGON_SIZE / CONFIG.STEP_SIZE_DEVIDER;
//        this.maxSteps = CONFIG.MAX_STEPS_FOR_LEVELFIELD_SELECTION;
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
        float screenWidthHeightRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        diceWarsCamera = new OrthographicCamera(CONFIG.CAMERAUNIT_WIDTH, CONFIG.CAMERAUNIT_HEIGHT * screenWidthHeightRatio);
        diceWarsCamera.position.set(new Vector3(CONFIG.CAMERAPOSITION_X, CONFIG.CAMERAPOSITION_Y, CONFIG.CAMERAPOSITION_Z));
        diceWarsCamera.direction.set(1, -1, 1);
        diceWarsCamera.near = (0);
        diceWarsCamera.far = (CONFIG.FARPLANE);
        diceWarsCamera.update();
    }

    public void draw() {
        modelBatch.begin(diceWarsCamera);
        modelBatch.render(worldService.getHexagonWorld(), environment);
        modelBatch.end();
    }

    public boolean isCubeLevelfieldCube(int x, int y) {
        return worldService.isPositionAvailable(x, y);
    }

    public boolean isPositionWithinCubes(int x, int y, int z) {
        HexagonWorld world = worldService.getHexagonWorld();
        if (x >= 0 && y >= 0 && z >= 0) {
            if (x < world.getWidth() && y < world.getHeight()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Berechnet anhand der Screen-Position x und y
     */
    public Vector2 screenPositionToLevelPosition(int screenX, int screenY) {
        Vector3 position = diceWarsCamera.unproject(new Vector3(screenX, screenY, 0));// unproject nearplane
        return screenPositionToLevelPosition(position, diceWarsCamera.direction);
    }

    public Vector2 screenPositionToLevelPosition(Vector3 position, Vector3 direction) {
        //TODO rukl

//        Vector3 endpoint;
//        int resultPositionX;
//        int resultPositionZ;
//        int steps = 0;
//
//        do {
//            endpoint = position.mulAdd(direction, stepSize);
//            if (isPositionWithinCubes((int) endpoint.x, (int) endpoint.y, (int) endpoint.z)) {
//                if (isCubeLevelfieldCube((int) endpoint.x, (int) endpoint.y, (int) endpoint.z)) {
//                    resultPositionX = (int) endpoint.x;
//                    resultPositionZ = (int) endpoint.z;
//                    return new Vector2(resultPositionX, resultPositionZ);
//                }
//            }
//            steps++;
//        } while (endpoint.y > 0 && steps < maxSteps);

        return null;
    }

    public void resize() {
        initializeCamera();
    }

    public void dispose() {
        worldService.dispose();
    }

}
