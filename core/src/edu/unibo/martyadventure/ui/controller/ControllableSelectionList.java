package edu.unibo.martyadventure.ui.controller;

import java.util.Optional;

public interface ControllableSelectionList {
    /**
     * @return the number of possible selections indexed from 0.
     */
    int getSelectionsCount();

    /**
     * @return the current selection.
     */
    Optional<Integer> getCurrentSelection();

    /**
     * @param the selection at the index has been chosen.
     */
    void selection(int selection);
}
