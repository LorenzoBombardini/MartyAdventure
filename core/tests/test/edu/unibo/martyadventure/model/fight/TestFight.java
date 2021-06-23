package test.edu.unibo.martyadventure.model.fight;

import edu.unibo.martyadventure.model.character.*;
import edu.unibo.martyadventure.model.fight.Fight;
import edu.unibo.martyadventure.model.weapon.Move;
import test.edu.unibo.martyadventure.model.TestCharacterFactory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestFight {

    @Test
    void testStartFight() {
        final PlayerCharacter player = TestCharacterFactory.getPlayerCharacter();
        final EnemyCharacter enemy = TestCharacterFactory.getEnemyCharacter();
        final Fight testFight = new Fight(player, enemy);
        assertEquals(player, testFight.getPlayer());
        assertEquals(enemy, testFight.getEnemy());
        assertEquals(1, testFight.getTurnCount());
    }

    @Test
    void testIsPlayerDead() {
        final PlayerCharacter player = TestCharacterFactory.getPlayerCharacter();
        final EnemyCharacter enemy = TestCharacterFactory.getEnemyCharacter();
        final Fight testFight = new Fight(player, enemy);

        assertFalse(testFight.isDead(10, player.getHp()));
        assertTrue(testFight.isDead(player.getHp(), player.getHp()));
    }

    @Test
    void testPlayerAttack() {
        final PlayerCharacter player = TestCharacterFactory.getPlayerCharacter();
        final EnemyCharacter enemy = TestCharacterFactory.getEnemyCharacter();
        final Fight testFight = new Fight(player, enemy);

        int turnCount = testFight.getTurnCount() + 2;
        int enemyHp = testFight.getEnemy().getHp();
        int damage = (int) (testFight.getPlayer().getWeapon().getDamageMultiplier()
                * player.getWeapon().getMoveList().get(0).getDamage());

        testFight.playerAttack(player.getWeapon().getMoveList().get(0));
        if (enemyHp != enemy.getHp()) {
            assertEquals(enemyHp - damage, enemy.getHp());
        }
        assertEquals(turnCount, testFight.getTurnCount());
    }

    @Test
    void testIsMoveUsable() {
        final PlayerCharacter player = TestCharacterFactory.getPlayerCharacter();
        player.getWeapon().getMoveList().set(0, Move.HEADSHOT);
        final EnemyCharacter enemy = TestCharacterFactory.getEnemyCharacter();
        final Fight testFight = new Fight(player, enemy);
        assertTrue(testFight.isMoveUsable(player, Move.HEADSHOT));
        testFight.setLastUse(player, Move.HEADSHOT, 1);
        assertFalse(testFight.isMoveUsable(player, Move.HEADSHOT));
    }

    @Test
    void testSetLastUse() {
        final PlayerCharacter player = TestCharacterFactory.getPlayerCharacter();
        final EnemyCharacter enemy = TestCharacterFactory.getEnemyCharacter();
        final Fight testFight = new Fight(player, enemy);

        int lastUse = 0;
        testFight.setLastUse(player, player.getWeapon().getMoveList().get(0), lastUse);
        assertTrue(player.getWeapon().getMoveList().get(0).isUsable(0, lastUse));

        lastUse++;
        testFight.setLastUse(player, player.getWeapon().getMoveList().get(0), lastUse);
        assertFalse(player.getWeapon().getMoveList().get(0).isUsable(1, lastUse));
    }
}
