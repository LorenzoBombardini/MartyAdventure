package edu.unibo.martyadventure.view.character;

import java.util.concurrent.ExecutionException;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import edu.unibo.martyadventure.model.character.EnemyCharacter;
import edu.unibo.martyadventure.view.weapon.WeaponView;

/**
 * A enemy charater's view.
 */
public class EnemyCharacterView extends CharacterView<EnemyCharacter> {

    private static final float MAX_ACCELLERATION = 20.0f;
    private static final float ACCELLERATION_FACTOR = 5.0f;
    private static final float MAX_SPEED = 70.0f;

    private final WeaponView dropWeapon;


    /**
     * @param character        the character's model.
     * @param initialiPosition the map position map the character should start at.
     * @param texture          the character's texture.
     * @param weapon           the character's weapon.
     * @param dropWeapon       the character's weapon to drop.
     */
    EnemyCharacterView(final EnemyCharacter character, final Vector2 initialPosition, final Texture texture,
            final WeaponView weapon, final WeaponView dropWeapon) throws InterruptedException, ExecutionException {
        super(character, initialPosition, MAX_ACCELLERATION, ACCELLERATION_FACTOR, MAX_SPEED,
                new TextureRegion(texture), weapon);
        this.dropWeapon = dropWeapon;
    }

    /**
     * @return the character's drop weapon.
     */
    public WeaponView getDropWeapon() {
        return this.dropWeapon;
    }

    @Override
    public Sprite getFightSprite() {
        return new Sprite(animations.getLeftIdle());
    }
}
