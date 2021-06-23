package test.edu.unibo.martyadventure.model.character;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.unibo.martyadventure.model.character.EnemyCharacter;
import edu.unibo.martyadventure.model.weapon.*;
import test.edu.unibo.martyadventure.model.TestCharacterFactory;

public class TestEnemyCharacter extends TestCharacter {

    @Test
    void testLoadingEnemyCharacter() {
        final EnemyCharacter character = TestCharacterFactory.getEnemyCharacter();
        assertEquals(TestCharacterFactory.DROP_ITEM, character.getDropitem());
        testLoadingCharacter(character, TestCharacterFactory.ENEMY_NAME);
    }

    @Test
    void testsetDropitem() {
        final EnemyCharacter character = TestCharacterFactory.getEnemyCharacter();
        final Weapon weapon2 = WeaponFactory.createWeapon("Test random weapon 2", 2, Weapon.WeaponType.MELEE);
        character.setDropitem(weapon2);
        assertEquals(weapon2, character.getDropitem());
    }
}
