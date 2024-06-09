package com.coffeerice.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.coffeerice.MainGame;

public class DesktopLauncher {

    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Puzzle177147";
        cfg.width = 480;
        cfg.height = 800;

        new LwjglApplication(new MainGame(), cfg);
    }
}
