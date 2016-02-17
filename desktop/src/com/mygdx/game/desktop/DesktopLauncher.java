package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Game;

public class DesktopLauncher {
//	public static void main (String[] arg) {
//		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		TexturePacker.Settings settings = new TexturePacker.Settings();
//		settings.maxWidth = 512;
//		settings.maxHeight = 512;
//		TexturePacker.process(settings, "topack", "packedimages", "pack");
//		new LwjglApplication(new Game(), config);
//	}
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.width = 1280*3/4;
		config.height = 960*3/4;
		new LwjglApplication(new Game(), config);
	}
}
