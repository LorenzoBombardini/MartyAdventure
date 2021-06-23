package edu.unibo.martyadventure.model.character;

import edu.unibo.martyadventure.model.weapon.Weapon;

/**
 * A character for the player to play as.
 */
public class PlayerCharacter extends Character {

    /**
     * Instantiate a new player character.
     *
     * @param name   the character's in-game name.
     * @param hp     the character's health points.
     * @param weapon the character's weapon.
     */
    public PlayerCharacter(final String name, final int hp, final Weapon weapon) {
        super(name, hp, weapon);
    }
}
