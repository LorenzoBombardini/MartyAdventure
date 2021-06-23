package edu.unibo.martyadventure;

import com.badlogic.gdx.Game;

import edu.unibo.martyadventure.view.screen.ScreenManager;

/**
 * Starts and manages a "Marty's Adventure" game session.
 */
public class MartyAdventureGame extends Game {

    private ScreenManager manager;

    @Override
    public void create() {
        manager = new ScreenManager();
        manager.loadMenuScreen();
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
