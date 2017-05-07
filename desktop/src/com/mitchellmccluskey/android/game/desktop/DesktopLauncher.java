package com.mitchellmccluskey.android.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mitchellmccluskey.android.game.ScalaGame;
import com.mitchellmccluskey.android.game.ScalaGame$;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = ScalaGame$.MODULE$.Width();
		config.height = ScalaGame$.MODULE$.Height();
		config.title = ScalaGame$.MODULE$.Title();
		new LwjglApplication(new ScalaGame(), config);
	}
}
