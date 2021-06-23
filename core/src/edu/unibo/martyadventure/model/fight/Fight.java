package edu.unibo.martyadventure.model.fight;

import java.util.concurrent.ThreadLocalRandom;
import java.util.HashMap;
import java.util.Map;

import edu.unibo.martyadventure.model.character.Character;
import edu.unibo.martyadventure.model.character.EnemyCharacter;
import edu.unibo.martyadventure.model.character.PlayerCharacter;
import edu.unibo.martyadventure.model.weapon.Move;
import edu.unibo.martyadventure.model.weapon.Weapon;

/*
 *  Main fight class (damage = weapon.damageMoltiplier * move.getDamage)
 */

public class Fight {
    private PlayerCharacter player;
    private EnemyCharacter enemy;
    private boolean playerFail = false;
    private boolean enemyFail = false;
    private Move playerLastMove;
    private Move enemyLastMove;
    private int turnCount;

    private Map<Character, Map<Move, Integer>> mapCharactersMove;
    private Map<Move, Integer> mapMartyMove;
    private Map<Move, Integer> mapEnemyMove;

    /**
     * The public constructor start a fight
     * 
     * @param player The PlayerCharacter
     * @param enemy  The EnemyCharacter
     */
    public Fight(PlayerCharacter player, EnemyCharacter enemy) {
        this.player = player;
        this.enemy = enemy;
        this.turnCount = 1;
        createHashMap();

    }

    // Getter & Setter
    public PlayerCharacter getPlayer() {
        return player;
    }

    public EnemyCharacter getEnemy() {
        return enemy;
    }

    public int getTurnCount() {
        return turnCount;
    }

    /**
     * Call attack function with enemy weapon, random move and player character
     */
    private void enemyAttack() {
        attack(enemy.getWeapon(), enemyMove(), player);
    }

    /**
     * Give a usable random Enemy Move
     * 
     * @return The random Move choosen from the enemy MoveList
     */
    private Move enemyMove() {
        Move move;
        do {
            move = enemy.getWeapon().getMoveList()
                    .get(ThreadLocalRandom.current().nextInt(enemy.getWeapon().getMoveList().size()));

        } while (!isMoveUsable(enemy, move));
        enemyLastMove = move;
        return move;
    }

    /**
     * Call attack function with input Move and after the player has attacked call
     * the enemyAttack function
     * 
     * @param inputMove The Move that the player wants to use
     */
    public void playerAttack(Move inputMove) {
        if (isMoveUsable(player, inputMove)) {
            playerLastMove = inputMove;
            attack(player.getWeapon(), inputMove, enemy);
        }
        enemyAttack();
    }

    /**
     * Main attack function used to handle all the fight possible situation
     * 
     * @param weapon    The striker's weapon
     * @param move      The striker's move
     * @param character The opponent character
     */
    private void attack(Weapon weapon, Move move, Character character) {
        // check if the move fail
        if (!move.testFailure()) {
            setLastFailCharacter(getOpponent(character), true);
            setLastUse(getOpponent(character), move, turnCount);

        } else {
            setLastFailCharacter(getOpponent(character), false);
            setLastUse(getOpponent(character), move, turnCount);

            // check if the damage will kill the opponent using isDead function
            if (isDead((int) Math.round((weapon.getDamageMultiplier() * move.getDamage())), character.getHp())) {
                // opponent is DEAD
                character.setHp(0);
                fightWinner();

            } else {
                // inflict attack on the opponent
                character.setHp(
                        (int) Math.round((character.getHp() - (weapon.getDamageMultiplier() * move.getDamage()))));
            }
        }

        turnCount++;
    }

    /**
     * Check if the damage will kill the character
     * 
     * @param damage      The attack damage
     * @param characterHP The Character HP
     * @return TRUE if the character is Dead, FALSE in the other case
     */
    public boolean isDead(int damage, int characterHP) {
        return damage >= characterHP;
    }

    /**
     * The fight is ended
     * 
     * @return The winner
     */
    public Character fightWinner() {
        if (player.getHp() == 0) {
            return enemy;
        }
        if (enemy.getHp() == 0) {
            return player;
        }
        return null;
    }

    /**
     * Set the lastUse of a specific Move
     * 
     * @param character The character who attack
     * @param move      The Move choosen by the character
     * @param fightTurn The current fightTurn
     */
    public void setLastUse(Character character, Move move, int fightTurn) {
        mapCharactersMove.get(character).replace(move, fightTurn);
    }

    /**
     * Check if a specific Move of a specific character is usable
     * 
     * @param character The character who wants to use the move
     * @param move      The Move that will be used
     * @return If the move is usable by the character
     */
    public boolean isMoveUsable(Character character, Move move) {
        return move.isUsable(turnCount, mapCharactersMove.get(character).get(move));
    }

    /**
     * Get if the the last Move of a specific character has failed
     * 
     * @param character The character whose last move you want to know has failed
     * @return TRUE if the character has failed, FALSE in the other case
     */
    public boolean getLastFail(Character character) {
        if (character == player) {
            return playerFail;
        }
        return enemyFail;
    }

    /**
     * Get the last Move used by a specific character
     * 
     * @param character The character whose last move you want to know
     * @return The character's last used Move
     */
    public Move getLastMove(Character character) {
        if (character == player) {
            return playerLastMove;
        }
        return enemyLastMove;
    }

    /**
     * Get the opponent of the attack
     * 
     * @param character The character that will be attacked
     * @return The character who attack
     */
    private Character getOpponent(Character character) {
        if (character == player) {
            return enemy;
        }
        return player;
    }

    /**
     * Set if the lastMove of a specific character has failed
     * 
     * @param character   The character whose last move failed
     * @param testFailure If the move has failed or not
     */
    private void setLastFailCharacter(Character character, boolean testFailure) {
        if (character == player) {
            playerFail = testFailure;
            return;
        }
        enemyFail = testFailure;
    }

    /**
     * Function to create the hashMaps when the fight start
     */
    private void createHashMap() {
        mapMartyMove = new HashMap<>();
        mapEnemyMove = new HashMap<>();
        for (int i = 0; i < Weapon.MOVE_LIST_SIZE; i++) {
            mapMartyMove.put(player.getWeapon().getMoveList().get(i), 0);
            mapEnemyMove.put(enemy.getWeapon().getMoveList().get(i), 0);
        }

        mapCharactersMove = new HashMap<>();
        mapCharactersMove.put(player, mapMartyMove);
        mapCharactersMove.put(enemy, mapEnemyMove);
    }
}
