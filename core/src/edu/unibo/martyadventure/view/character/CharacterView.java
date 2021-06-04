package edu.unibo.martyadventure.view.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import edu.unibo.martyadventure.controller.entity.ControllableEntity;
import edu.unibo.martyadventure.view.entity.EntityDirection;
import edu.unibo.martyadventure.view.entity.EntityState;

/**
 * A character's base providing basic movement, interaction with given the map
 * and visual representation.
 */
public abstract class CharacterView implements ControllableEntity {

    protected final float maxAccelleration;
    protected final float accellerationFactor;
    protected final float maxSpeed;

    private float velocity;
    private Vector2 currentPosition;
    private Vector2 nextPosition;

    private EntityState movementState;
    private EntityDirection movementDirection;

    private final Sprite sprite;

    private final AnimationPack animations;
    private float animationStartTime;

    private final Rectangle boundingBox;


    public CharacterView(final Vector2 initialPosition, final float maxAccelleration, final float accellerationFactor,
            final float maxSpeed, final Sprite sprite, final TextureRegion texture) {
        this.maxAccelleration = maxAccelleration;
        this.accellerationFactor = accellerationFactor;
        this.maxSpeed = maxSpeed;

        this.velocity = 0.0f;
        this.currentPosition = initialPosition;
        this.nextPosition = initialPosition;

        this.movementState = EntityState.IDLE;
        this.movementDirection = EntityDirection.UP;

        this.sprite = sprite;

        this.animations = new AnimationPack(texture, sprite.getRegionWidth(), sprite.getRegionHeight());
        this.animationStartTime = AnimationPack.ANIMATION_START;

        this.boundingBox = new Rectangle(this.sprite.getBoundingRectangle());
    }

    /**
     * Resize the character bounding box.
     * 
     * @param resizeWidth  percentage from 1.0 to 0.0 to resize the width to.
     * @param resizeHeight percentage from 1.0 to 0.0 to resize the height to.
     */
    public void resizeBoundingBox(final float resizeWidth, final float resizeHeight) {
        this.boundingBox.width *= resizeWidth;
        this.boundingBox.height *= resizeHeight;
    }

    /**
     * Restore the character bounding box to it's original size.
     */
    public void resetBoundingBoxSize() {
        this.boundingBox.set(this.sprite.getBoundingRectangle());
    }

    /**
     * Move the character to the next position.
     */
    public void goNextPosition() {
        this.currentPosition = this.nextPosition;
    }

    /**
     * @return the character sprite
     */
    public Sprite getSprite() {
        return this.sprite;
    }

    /**
     * @return the current position.
     */
    public Vector2 getCurrentPosition() {
        return this.currentPosition;
    }

    /**
     * @param position the position to set the character in.
     */
    public void setCurrentPosition(final Vector2 position) {
        this.currentPosition = position;
        this.sprite.setPosition(position.x, position.y);
        this.boundingBox.set(this.sprite.getBoundingRectangle());
    }

    /**
     * @return the next precalculated position.
     */
    public Vector2 getNextPosition() {
        return this.nextPosition;
    }

    /**
     * @return the character collision bounding box.
     */
    public Rectangle getBoundingBox() {
        return new Rectangle(this.boundingBox);
    }

    /**
     * @return calculate the current frame.
     */
    public TextureRegion getCurrentFrame() {
        if (this.movementState == EntityState.WALKING) {
            this.animationStartTime += Gdx.graphics.getDeltaTime();
            return this.animations.getEntityDirectionAnimation(this.movementDirection)
                    .getKeyFrame(this.animationStartTime);
        } else {
            this.animationStartTime = AnimationPack.ANIMATION_START;
            return this.animations.getEntityDirectionIdle(this.movementDirection);
        }
    }

    @Override
    public void setState(final EntityState state) {
        this.movementState = state;
    }

    @Override
    public void setDirection(final EntityDirection direction) {
        this.movementDirection = direction;
    }

    @Override
    public void calculateNextPosition(final EntityDirection direction, final float delta) {
        // Increase the acceleration (clamped to it's max).
        this.velocity = Math.min(this.maxAccelleration, this.velocity + this.accellerationFactor * delta);

        // Update the direction
        this.movementDirection = direction;

        // Calculate the movement.
        Vector2 movement = Vector2.Zero;
        switch (direction) {
        case LEFT:
            movement.x = -1;
            break;
        case RIGHT:
            movement.x = +1;
            break;
        case UP:
            movement.x = -1;
            break;
        case DOWN:
            movement.x = +1;
            break;
        default:
            throw new IllegalArgumentException("Illegal direction '" + direction + "'");
        }

        movement = movement.scl(this.velocity);

        // Calculate the next position from the currently next (old) one.
        this.nextPosition = this.nextPosition.add(movement).clamp(0, this.maxSpeed);
    }
}