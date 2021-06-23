package test.edu.unibo.martyadventure.model.weapon;

import java.util.ArrayList;
import java.util.List;
import edu.unibo.martyadventure.model.weapon.*;
import edu.unibo.martyadventure.model.weapon.Weapon.WeaponType;
import edu.unibo.martyadventure.view.MapManager;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestWeaponFactory {

    String name = "Weapon1";
    WeaponType type = WeaponType.MELEE;
    int damageMultiplier = 10;
    List<Move> moveList = new ArrayList<>(List.of(Move.UPPERCUT, Move.HOOK, Move.JAB, Move.SUPERMANPUNCH));
    Weapon weaponTest = WeaponFactory.newWeapon(name, type, damageMultiplier, moveList);


    @Test
    void testNewWeapon() {
        weaponTest = WeaponFactory.newWeapon(name, type, damageMultiplier, moveList);
        assertEquals(name, weaponTest.getName());
        assertEquals(type, weaponTest.getType());
        assertEquals(damageMultiplier, weaponTest.getDamageMultiplier());
        assertEquals(moveList, weaponTest.getMoveList());
    }

    @Test
    void testSetWeaponMove() {
        weaponTest = WeaponFactory.setWeaponMove(weaponTest, Move.UPPERCUT, Move.HOOK, Move.JAB, Move.SUPERMANPUNCH);
        assertEquals(moveList, weaponTest.getMoveList());
    }

    @Test
    void testCreateRandomWeapon() {
        weaponTest = WeaponFactory.createWeapon(weaponTest.getName(), weaponTest.getDamageMultiplier(),
                weaponTest.getType());
        checkDuplicateItemsInMoveList();
    }

    @Test
    void testCreateRandomMeleeWeapon() {
        weaponTest = WeaponFactory.createRandomMeleeWeapon(weaponTest.getName(), weaponTest.getDamageMultiplier());
        checkDuplicateItemsInMoveList();
        // Check if the items of moveList are all melee MOVE
        for (int i = 0; i < 4; i++) {
            assertEquals(weaponTest.getMoveList().get(i).getType(), Move.MoveType.MELEE);
        }
    }

    @Test
    void testCreateRandomRangedWeapon() {
        weaponTest = WeaponFactory.createRandomRangedWeapon(weaponTest.getName(), weaponTest.getDamageMultiplier());
        checkDuplicateItemsInMoveList();
        // Check if the items of moveList are all ranged MOVE
        for (int i = 1; i < 4; i++) {
            assertEquals(weaponTest.getMoveList().get(i).getType(), Move.MoveType.RANGED);
        }
    }

    @Test
    void stressTest() {
        int n = 500;
        for (int i = 0; i < n; i++) {
            testRandomWeaponLevel();
        }
    }

    @Test
    void testRandomWeaponLevel() {
        weaponTest = WeaponFactory.createRandomWeaponLevel(weaponTest.getName(), MapManager.Maps.MAP1,
                Weapon.WeaponType.MELEE);
        assertTrue(weaponTest
                .getDamageMultiplier() >= WeaponFactory.MIN_DAMAGE_MULTIPLIER * WeaponFactory.LEVEL1
                && weaponTest.getDamageMultiplier() <= WeaponFactory.MAX_DAMAGE_MULTIPLIER
                        * WeaponFactory.LEVEL1);

        weaponTest = WeaponFactory.createRandomWeaponLevel(weaponTest.getName(), MapManager.Maps.MAP2,
                Weapon.WeaponType.MELEE);
        assertTrue(weaponTest
                .getDamageMultiplier() >= WeaponFactory.MIN_DAMAGE_MULTIPLIER * WeaponFactory.LEVEL2
                && weaponTest.getDamageMultiplier() <= WeaponFactory.MAX_DAMAGE_MULTIPLIER
                        * WeaponFactory.LEVEL2);

        weaponTest = WeaponFactory.createRandomWeaponLevel(weaponTest.getName(), MapManager.Maps.MAP3,
                Weapon.WeaponType.MELEE);
        assertTrue(weaponTest
                .getDamageMultiplier() >= WeaponFactory.MIN_DAMAGE_MULTIPLIER * WeaponFactory.LEVEL3
                && weaponTest.getDamageMultiplier() <= WeaponFactory.MAX_DAMAGE_MULTIPLIER
                        * WeaponFactory.LEVEL3);
    }

    // Function to check if there is a duplicate items in a moveList
    void checkDuplicateItemsInMoveList() {
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                assertNotEquals(weaponTest.getMoveList().get(i), weaponTest.getMoveList().get(j));
            }
        }
    }
}
