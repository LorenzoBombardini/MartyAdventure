package edu.unibo.martyadventure.model.weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import edu.unibo.martyadventure.model.weapon.Weapon.WeaponType;
import edu.unibo.martyadventure.view.MapManager;

/**
 * Factory class to create Weapon
 */

public class WeaponFactory {

    public static final float LEVEL1 = 1;
    public static final float LEVEL2 = 2;
    public static final float LEVEL3 = 3;
    public final static float MIN_DAMAGE_MULTIPLIER = 0.5f;
    public final static float MAX_DAMAGE_MULTIPLIER = 1.5f;

    /**
     * Weapon public constructor
     * 
     * @return The new weapon
     */
    public static Weapon newWeapon(String name, Weapon.WeaponType type, double damageMultiplier, List<Move> moveList) {
        Weapon weapon = new Weapon(name, type, damageMultiplier, moveList);
        return weapon;
    }

    /**
     * Set an input moveList on a specific Weapon
     * 
     * @param weapon      The weapon that will be modify
     * @param move1/2/3/4 The moves that compose the new moveList
     * @return The new weapon
     */
    public static Weapon setWeaponMove(Weapon weapon, Move move1, Move move2, Move move3, Move move4) {
        List<Move> moveList = new ArrayList<>(List.of(move1, move2, move3, move4));
        weapon.setMoveList(moveList);
        return weapon;
    }

    /**
     * Used to create random weapon
     * 
     * @param name             The name of the weapon
     * @param damageMultiplier The damageMultiplier of the weapon
     * @param type             The type of the weapon
     * @return The weapon
     */
    public static Weapon createWeapon(String name, double damageMultiplier, Weapon.WeaponType type) {
        Weapon weapon = new Weapon(name, 0);
        List<Move> moveList = new ArrayList<>();
        int i = 0;
        Move move;
        do {
            if (type == WeaponType.MELEE) {
                move = Move.getRandomMeleeMove();
            } else {
                move = Move.getRandomRangedMove();
            }

            if (!moveList.contains(move)) {
                moveList.add(i, move);
                i++;
            }
        } while (i < Weapon.MOVE_LIST_SIZE);
        weapon.setDamageMultiplier(damageMultiplier);
        weapon.setMoveList(moveList);
        weapon.setType(type);
        return weapon;
    }

    /**
     * Set a random RANGED moveList on a specific Weapon composed of 4 ranged move
     * 
     * @param name The weapon name that will be created
     * @return The new weapon with random MELEE moveList
     */
    public static Weapon createRandomRangedWeapon(String name, double damageMultiplier) {
        return createWeapon(name, damageMultiplier, Weapon.WeaponType.RANGED);
    }

    /**
     * Set a random MELEE moveList on a specific Weapon composed of 4 melee move
     * 
     * @param name The weapon name that will be created
     * @return The new weapon with random MELEE moveList
     */
    public static Weapon createRandomMeleeWeapon(String name, double damageMultiplier) {
        return createWeapon(name, damageMultiplier, Weapon.WeaponType.MELEE);
    }

    /**
     * Create a new Weapon based on level
     * 
     * @param name The weapon name that will be created
     * @param map  The map in which the weapon will be created
     * @param type The type of the weapon
     * @return The new random weapon based on level
     */
    public static Weapon createRandomWeaponLevel(String name, MapManager.Maps map, Weapon.WeaponType type) {
        switch (map) {
        case MAP1:
            return createWeapon(name, (double) (randomDamageMultiplier() * LEVEL1), type);
        case MAP2:
            return createWeapon(name, (double) (randomDamageMultiplier() * LEVEL2), type);
        case MAP3:
            return createWeapon(name, (double) (randomDamageMultiplier() * LEVEL3), type);
        default:
            throw new IllegalArgumentException("Wrong Map");
        }
    }

    /**
     * Get random damange multiplier
     * 
     * @return Random double between MIN and MAX
     */
    public static float randomDamageMultiplier() {
        return ((ThreadLocalRandom.current().nextFloat() % MAX_DAMAGE_MULTIPLIER) + MIN_DAMAGE_MULTIPLIER);
    }

    /**
     * Private constructor
     */
    private WeaponFactory() {

    }
}
