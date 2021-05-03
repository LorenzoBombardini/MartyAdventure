package edu.unibo.martyadventure.entity.controller;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Optional;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import edu.unibo.martyadventure.entity.EntityDirection;
import edu.unibo.martyadventure.entity.EntityState;

/*
 * Handles the player input
 */
public class PlayerInputProcessor implements InputProcessor {

    /*
     * Creating this ordered array to impose a stable preference in the entity's
     * direction, otherwise at each run the hashcode of the EntityDirection's
     * singletons may change, resulting in an inconsistent orientation across runs
     * when the player presses more than a single key.
     */
    private static EntityDirection[] orderedDirections = { EntityDirection.UP, EntityDirection.DOWN,
            EntityDirection.LEFT, EntityDirection.RIGHT };

    private final ControllableEntity playerEntity;
    private final HashMap<EntityDirection, Boolean> directionsMap;

    private HashMap<EntityDirection, Boolean> getDirectionsMap() {
        final HashMap<EntityDirection, Boolean> map = new HashMap<EntityDirection, Boolean>();
        for (EntityDirection direction : EntityDirection.values()) {
            map.put(direction, false);
        }

        return map;
    }

    /*
     * Sets the given keycode to the given value
     * 
     * @return true if the keycode was valid and the value has been set, false
     * otherwise.
     */
    private boolean setSelectedDirection(final int keycode, final boolean value) {
        Optional<EntityDirection> pressedKey = matchKeycode(keycode);
        if (pressedKey.isPresent()) {
            this.directionsMap.put(pressedKey.get(), value);
            return true;
        }
        return false;
    }

    /*
     * @return the matching key to the given keycode, if valid.
     */
    private Optional<EntityDirection> matchKeycode(final int keycode) {
        switch (keycode) {
        case Input.Keys.A:
            return Optional.of(EntityDirection.LEFT);
        case Input.Keys.D:
            return Optional.of(EntityDirection.RIGHT);
        case Input.Keys.W:
            return Optional.of(EntityDirection.UP);
        case Input.Keys.S:
            return Optional.of(EntityDirection.DOWN);
        default:
            return Optional.empty();
        }
    }

    /*
     * Update the player entity state and set the next position
     */
    private void processInput(final float delta) {
        boolean anyDirection = false;
        for (EntityDirection direction : PlayerInputProcessor.orderedDirections) {
            if (this.directionsMap.get(direction)) {
                this.playerEntity.setState(EntityState.WALKING);
                this.playerEntity.setDirection(direction);
                this.playerEntity.calculateNextPosition(direction, delta);

                anyDirection = true;
                break;
            }
        }

        if (!anyDirection) {
            this.playerEntity.setState(EntityState.IDLE);
        }
    }

    public PlayerInputProcessor(ControllableEntity player) {
        this.playerEntity = player;
        this.directionsMap = getDirectionsMap();
    }

    public void update(float delta) {
        processInput(delta);
    }

    @Override
    public boolean keyDown(int keycode) {
        return setSelectedDirection(keycode, true);
    }

    @Override
    public boolean keyUp(int keycode) {
        return setSelectedDirection(keycode, false);
    }

    @Override
    public boolean keyTyped(char character) {
        // Unused
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Unused
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // Unused
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // Unused
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // Unused
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // Unused
        return false;
    }
}
