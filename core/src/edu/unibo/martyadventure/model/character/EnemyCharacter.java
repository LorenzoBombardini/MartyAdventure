package edu.unibo.martyadventure.model.character;

import edu.unibo.martyadventure.model.weapon.Weapon;

/**
 * An enemy character with a droppable weapon.
 */
public class EnemyCharacter extends Character {

    // Weapon drop from enemy.
    private Weapon dropWeapon;


    /**
     * Instantiate a new enemy character.
     *
     * @param dropWeapon the weapon for the character to drop.
     * @param name       the character's in-game name.
     * @param hp         the character's health points.
     * @param weapon     the character's weapon.
     */
    public EnemyCharacter(final Weapon dropWeapon, final String name, final int hp, final Weapon weapon) {
        super(name, hp, weapon);
        this.dropWeapon = dropWeapon;
    }

    /**
     * @return the character's drop.
     */
    public Weapon getDropitem() {
        return this.dropWeapon;
    }

    /**
     * @param dropWeapon the weapon for the character to drop.
     */
    public void setDropitem(final Weapon dropWeapon) {
        this.dropWeapon = dropWeapon;
    }
}
