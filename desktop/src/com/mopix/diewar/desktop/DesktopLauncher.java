package com.mopix.diewar.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mopix.diewar.CONFIG;
import com.mopix.diewar.DiceWar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DesktopLauncher {

	private static final Logger LOGGER = LoggerFactory.getLogger(DesktopLauncher.class);

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "DiceWar";
		config.fullscreen = CONFIG.FULLSCREEN;
		config.vSyncEnabled = CONFIG.V_SYNC_ENABLED;
		config.backgroundFPS = CONFIG.BACKGROUND_FPS;
		config.foregroundFPS = CONFIG.FOREGROUND_FPS;
		config.samples = CONFIG.SAMPLES;
		config.width = CONFIG.APPLICATION_WIDTH;
		config.height = CONFIG.APPLICATION_HEIGHT;
		config.addIcon("icon.png", Files.FileType.Internal);

		new LwjglApplication(new DiceWar(), config);
		Gdx.app.setLogLevel(CONFIG.LOG_LEVEL);
		LOGGER.info("Desktop launch started");
	}
}
