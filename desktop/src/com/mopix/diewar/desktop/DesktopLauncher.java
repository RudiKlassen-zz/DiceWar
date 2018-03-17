package com.mopix.diewar.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mopix.diewar.DiceWar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DesktopLauncher {

	private static final Logger LOGGER = LoggerFactory.getLogger(DesktopLauncher.class);

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new DiceWar(), config);
	}
}
