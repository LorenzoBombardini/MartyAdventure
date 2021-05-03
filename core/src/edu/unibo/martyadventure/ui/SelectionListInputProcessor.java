package edu.unibo.martyadventure.ui;

import java.util.Optional;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

/**
 * Allows to choose a selection from an indexed list of elements via UP/DOWN
 * arrow keys and numeric pad.
 */
public class SelectionListInputProcessor implements InputProcessor {

    private final ControllableSelectionList selectionList;
    private final int directionMultiplier;
    private int currentSelection;

    private boolean tryChangeSelection(final int value) {
        if (value >= 0 && value < this.selectionList.getSelectionsCount()) {
            this.currentSelection = value;
            return true;
        }
        return false;
    }

    /**
     * Instances a new input processor for the given selection list.
     * 
     * @param selectionList the list to choose a selection from
     * @param topdown inverts the UP/DOWN keys relatively to the list indexes if true
     * 
     * @throws IndexOutOfBoundsException if the current selection is present by
     *                                   bellow 0 or beyond the list length.
     */
    public SelectionListInputProcessor(final ControllableSelectionList selectionList, final boolean topdown) {
        this.selectionList = selectionList;
        this.directionMultiplier = topdown ? -1 : 1;

        Optional<Integer> defaultSelection = selectionList.getCurrentSelection();
        if (defaultSelection.isPresent()) {
            int selection = defaultSelection.get();
            if (selection < 0 || selection >= selectionList.getSelectionsCount()) {
                throw new IndexOutOfBoundsException("Invalid default selection");
            } else {
                this.currentSelection = selection;
            }
        } else {
            this.currentSelection = 0;
        }
    }

    /**
     * @return the current selection, which may not be confirmed.
     */
    public int getCurrentSelection() {
        return this.currentSelection;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.UP) {
            return tryChangeSelection(this.currentSelection - 1 * this.directionMultiplier);
        } else if (keycode == Keys.DOWN) {
            return tryChangeSelection(this.currentSelection + 1 * this.directionMultiplier);
        }

        // Triggers the selection confirm.
        if (keycode == Keys.INSERT) {
            this.selectionList.selection(this.currentSelection);
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // unused
        return false;
    }

    /**
     * Attempts to parse the given character to a valid selection index and use it
     * to set the current selection.
     * 
     * @return if the character was a valid selection.
     */
    @Override
    public boolean keyTyped(char character) {
        // Get the unicode/ASCII value and subtract the offset.
        int value = Character.getNumericValue(character) - '0';
        // If it is a single digit within the selection list range
        return tryChangeSelection(value);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // unused
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // unused
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // unused
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // unused
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // unused
        return false;
    }

}
