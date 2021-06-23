package edu.unibo.martyadventure.view.character;

import java.util.concurrent.ExecutionException;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import edu.unibo.martyadventure.model.character.PlayerCharacter;
import edu.unibo.martyadventure.view.Toolbox;
import edu.unibo.martyadventure.view.weapon.WeaponView;
import edu.unibo.martyadventure.view.weapon.WeaponViewFactory;

/**
 * A player character's base providing basic movement, interaction with given
 * the map and visual representation.
 */
public class PlayerCharacterView extends CharacterView<PlayerCharacter> implements Disposable {

    private static final float MAX_ACCELLERATION = 20.0f;
    private static final float ACCELLERATION_FACTOR = 10.0f;
    private static final float MAX_SPEED = 100.0f;

    public static final int PLAYER_HP = 500;
    public static final double MAP1_PLAYER_HP_MULTIPLIER = 1.5;
    public static final double MAP2_PLAYER_HP_MULTIPLIER = 2;

    private static WeaponView playerWeapon;
    private static PlayerCharacter player;

    private final String texturePath;
    private boolean disposed;

    static {
        resetPlayer();
    }


    /**
     * Reset the player model status to it's defaults.
     */
    public static void resetPlayer() {
        playerWeapon = WeaponViewFactory.createPlayerWeaponView();
        player = new PlayerCharacter("", PLAYER_HP, playerWeapon.getWeapon());
    }

    /**
     * Instantiate a new player character view.
     *
     * @param name             the character's in-game name.
     * @param initialiPosition the map position map the character should start at.
     * @param texturePath      the path to the character textures to load.
     */
    PlayerCharacterView(final String name, final Vector2 initialPosition, final String texturePath)
            throws InterruptedException, ExecutionException {
        super(player, initialPosition, MAX_ACCELLERATION, ACCELLERATION_FACTOR, MAX_SPEED,
                new TextureRegion(Toolbox.getTexture(texturePath)), playerWeapon);
        player.setName(name);
        this.texturePath = texturePath;
        this.disposed = false;
    }

    /**
     * @param weapon the player weapon to set.
     */
    public void setWeapon(final WeaponView weapon) {
        super.weaponView = weapon;
        super.character.setWeapon(weapon.getWeapon());
        playerWeapon = weapon;
    }

    @Override
    public void dispose() {
        if (!this.disposed) {
            Toolbox.unloadAsset(this.texturePath);
            this.disposed = true;
        }
    }

    @Override
    public Sprite getFightSprite() {
        return new Sprite(animations.getRightIdle());
    }
}
