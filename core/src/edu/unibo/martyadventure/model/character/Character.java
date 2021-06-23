package edu.unibo.martyadventure.model.character;

import edu.unibo.martyadventure.model.weapon.Weapon;

/**
 * Abstract class for common functionality between characters.
 */
public abstract class Character {

    private String name;
    private int hp;
    private Weapon weapon;


    /**
     * @param name The name of the character.
     * @param hp   The health points of the character.
     * @param set  weapon the weapon of the character.
     */
    public Character(String name, int hp, Weapon weapon) {
        setName(name);
        setHp(hp);
        setWeapon(weapon);
    }

    /**
     * @return the character's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the character's name to set.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the character's health points.
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * @param hp the character's health points to set.
     */
    public void setHp(final int hp) {
        this.hp = hp;
    }

    /**
     * @return the characcter's weapon.
     */
    public Weapon getWeapon() {
        return this.weapon;
    }

    /**
     * @param weapon the character's weapon to set.
     */
    public void setWeapon(final Weapon weapon) {
        this.weapon = weapon;
    }
}
