package edu.unibo.martyadventure.model.weapon;

import java.util.concurrent.ThreadLocalRandom;

/*
 * Enum class that contains all Move included in the game
 * moveNAME( name, damage, failRatio, reloadTime , Melee or Ranged)
 */
public enum Move {
    // FIST, BRASS KNUCKLES MOVE
    HOOK("Gancio", 5, 10, 0, MoveType.MELEE),
    JAB("Diretto", 6, 20, 0, MoveType.MELEE),
    UPPERCUT("Montante", 7, 30, 2, MoveType.MELEE),
    SUPERMANPUNCH("SupermanPunch", 10, 40, 2, MoveType.MELEE),

    // BASEBALL BAT, CROWBAR, SLEDGEHUMMER MOVE
    LOWDAMAGE("Colpo Debole", 5, 10, 0, MoveType.MELEE),
    HANDLESHOT("Colpo di Manico", 6, 20, 0, MoveType.MELEE),
    HIGHDAMAGE("Colpo Potente", 8, 30, 2, MoveType.MELEE),
    TEMPLESHOT("Colpo alla Tempia", 12, 40, 2, MoveType.MELEE),

    // KNIFE MOVE
    THRUST("Pugnalata", 7, 20, 2, MoveType.MELEE),
    STAB("Coltellata", 8, 20, 2, MoveType.MELEE),
    TROW("Lancio", 15, 70, 4, MoveType.RANGED),

    // REVOLVER MOVE
    GRAZEDSHOT("Colpo di Striscio", 10, 20, 2, MoveType.RANGED),
    BODYSHOT("Colpo al Corpo", 15, 30, 4, MoveType.RANGED),
    HEADSHOT("Colpo alla Testa", 30, 70, 4, MoveType.RANGED);

    private final String name;
    private final int damage;
    private final int failRatio; // 0 success 100 fail
    private final int reloadTime;

    public enum MoveType {
        MELEE, RANGED;
    };

    MoveType type;

    /**
     * Private constructor
     * 
     * @param name       The name of the move
     * @param damage     The damage of the move
     * @param failRatio  The failRatio of the move
     * @param reloadTime The reloadTime of the move
     * @param type       The type of the move
     */
    private Move(String name, int damage, int failRatio, int reloadTime, MoveType type) {
        this.name = name;
        this.damage = damage;
        this.failRatio = failRatio;
        this.reloadTime = reloadTime;
        this.type = type;
    }

    // Getter & Setter
    public int getDamage() {
        return damage;
    }

    public int getFailRatio() {
        return failRatio;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public MoveType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    /**
     * Check if the move is usable
     * 
     * @param fightTurn The fight turn where the move will be used
     * @return TRUE if isUsable, FALSE in the other case
     */
    public boolean isUsable(int fightTurn, int lastUse) {
        if (lastUse + reloadTime < fightTurn || lastUse == 0) {
            return true;
        } else
            return false;
    }

    /**
     * Check if the move fail
     * 
     * @return FALSE if fail, TRUE if is usable
     */
    public boolean testFailure() {
        // random number (0 to 100) if it's >= than failRatio success(TRUE), else
        // fail(FALSE)
        return ThreadLocalRandom.current().nextInt(101) >= failRatio;
    }

    /**
     * Get a random Move of a specific type
     * 
     * @param typr The type of the random move
     * @return The Random Move of that type
     */
    private static Move getRandomTypeMove(MoveType type) {
        Move move;
        do {
            move = values()[(int) (Math.random() * values().length)];
        } while (move.getType() != type);

        return move;
    }

    /**
     * Get a random MELEE Move
     * 
     * @return The Random MELEE Move
     */
    public static Move getRandomMeleeMove() {
        return getRandomTypeMove(MoveType.MELEE);
    }

    /**
     * Get a random RANGED Move
     * 
     * @return The Random RANGED Move
     */
    public static Move getRandomRangedMove() {
        return getRandomTypeMove(MoveType.RANGED);
    }

}