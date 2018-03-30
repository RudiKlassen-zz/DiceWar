package com.mopix.diewar;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO doc
 */
public class Utility {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utility.class);

    private static final AssetManager assetManager = new AssetManager();

    private static InternalFileHandleResolver filePathResolver = new InternalFileHandleResolver();

    /**
     * TODO doc and test
     *
     * @param textureFilenamePath
     */
    public static void loadTextureAsset(String textureFilenamePath) {

        if (StringUtils.isEmpty(textureFilenamePath)) {
            return;
        }

        if (!filePathResolver.resolve(textureFilenamePath).exists()) {
            LOGGER.debug("Texture does not exist: " + textureFilenamePath);
            return;
        }

        //load asset
        assetManager.setLoader(Texture.class, new TextureLoader(filePathResolver));
        assetManager.load(textureFilenamePath, Texture.class);
        //Until we add loading screen,
        //just block until we load the map
        assetManager.finishLoadingAsset(textureFilenamePath);
    }

    /**
     * TODO doc and test
     *
     * @param assetFilenamePath
     */
    public static void unloadAsset(String assetFilenamePath) {

        if (!assetManager.isLoaded(assetFilenamePath)) {
            LOGGER.debug("Asset is not loaded; Nothing to unload: " + assetFilenamePath);
            return;
        }

        // once the asset manager is done loading
        assetManager.unload(assetFilenamePath);
    }

    /**
     * TODO doc and test
     *
     * @param textureFilenamePath
     * @return
     */
    public static Texture getTextureAsset(String textureFilenamePath) {

        if (!assetManager.isLoaded(textureFilenamePath)) {
            LOGGER.debug("Texture is not loaded: " + textureFilenamePath);
            return null;
        }

        return assetManager.get(textureFilenamePath, Texture.class);
    }
}
