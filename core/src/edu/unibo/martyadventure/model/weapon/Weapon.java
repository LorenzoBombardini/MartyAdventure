package edu.unibo.martyadventure.model.weapon;

import java.util.ArrayList;
import java.util.List;

public class Weapon {

    private String name;
    private double damageMultiplier;
    private List<Move> moveList = new ArrayList<>();
    
    public static enum WeaponType {
        MELEE, RANGED;
    }
    
    WeaponType type;

    public static final int MOVE_LIST_SIZE = 4;

    /**
     * Protected constructor
     * 
     * @param name             The name of the weapon
     * @param type             The type of the weapon (Melee o Ranged)
     * @param damageMultiplier The multiplier that will be applied to the Move damage
     * @param moveList         The list of possible moves of that weapon
     */
    protected Weapon(String name, WeaponType type, double damageMultiplier, List<Move> moveList) {
        this.name = name;
        this.type = type;
        this.damageMultiplier = damageMultiplier;
        setMoveList(moveList);
    }

    /**
     * Protected constructor
     * 
     * @param name             The name of the weapon
     * @param damageMultiplier The multiplier that will be applied to the Move
     *                         damage
     */
    protected Weapon(String name, double damageMultiplier) {
        this.name = name;
        this.damageMultiplier = damageMultiplier;
    }

    // Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }

    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(double damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public List<Move> getMoveList() {
        return moveList;
    }

    public void setMoveList(List<Move> moveList) {
        if (moveList.size() == MOVE_LIST_SIZE)
            this.moveList = moveList;
        else
            System.err.println("moveList ERROR");
    }
}
