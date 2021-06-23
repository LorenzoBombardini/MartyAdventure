package edu.unibo.martyadventure.desktop;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import edu.unibo.martyadventure.MartyAdventureGame;

/**
 * Handles the game startup.
 */
public class DesktopLauncher {

    private static final double BASE_SCALING = 0.8;
    private static final double SECONDARY_SCALING = 0.5625; // 9/16


    /**
     * Handle vertical and ultrawide displays by selecting the smallest dimension.
     */
    private static int getHorizontalScale() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        // Get the smallest dimension and scale it.
        final double base = Math.min(screen.width, screen.width) * BASE_SCALING;
        return (int) Math.round(base);
    }

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Marty's Adventure");
        // Set the game window to the scaled ratio of 3/4.
        final int base = getHorizontalScale();
        config.setWindowedMode(base, (int) Math.round(base * SECONDARY_SCALING));
        config.setWindowSizeLimits(600, 600, 5120, 2880);
        config.setWindowIcon("Icons/icon.png");

        config.setForegroundFPS(60);
        new Lwjgl3Application(new MartyAdventureGame(), config);
    }
}
