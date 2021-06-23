package edu.unibo.martyadventure.view.character;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.unibo.martyadventure.view.entity.EntityDirection;

/**
 * From a texture containing the tiles for the walking animation, build each
 * animation.
 */
class AnimationPack {

    private static final float FRAME_DURATION = 0.25f;
    private static final int DOWN_FRAMES_INDEX = 0;
    private static final int LEFT_FRAMES_INDEX = 1;
    private static final int RIGHT_FRAMES_INDEX = 2;
    private static final int UP_FRAMES_INDEX = 3;

    public static final float ANIMATION_START = 0.0f;

    private final Animation<TextureRegion> upAnimations;
    private final Animation<TextureRegion> downAnimations;
    private final Animation<TextureRegion> leftAnimations;
    private final Animation<TextureRegion> rightAnimations;


    private Animation<TextureRegion> buildAnimation(final TextureRegion[][] textures, final int tilesIndex) {
        final Animation<TextureRegion> animation = new Animation<TextureRegion>(AnimationPack.FRAME_DURATION,
                textures[tilesIndex]);
        animation.setPlayMode(PlayMode.LOOP);
        return animation;
    }

    /**
     * @param texture    the texture to extract the frames from.
     * @param tileWidth  the width of each tile.
     * @param tileHeight the height of each tile.
     */
    public AnimationPack(final TextureRegion texture, final int tileWidth, final int tileHeight) {
        TextureRegion[][] textures = texture.split(tileWidth, tileHeight);
        // Workaround for no generic arrays in java
        this.downAnimations = buildAnimation(textures, AnimationPack.DOWN_FRAMES_INDEX);
        this.leftAnimations = buildAnimation(textures, AnimationPack.LEFT_FRAMES_INDEX);
        this.rightAnimations = buildAnimation(textures, AnimationPack.RIGHT_FRAMES_INDEX);
        this.upAnimations = buildAnimation(textures, AnimationPack.UP_FRAMES_INDEX);
    }

    /**
     * @return the up-side walking animation
     */
    public Animation<TextureRegion> getUpAnimation() {
        return this.upAnimations;
    }

    /**
     * @return the down-side walking animation
     */
    public Animation<TextureRegion> getDownAnimation() {
        return this.downAnimations;
    }

    /**
     * @return the left-side walking animation
     */
    public Animation<TextureRegion> getLeftAnimation() {
        return this.leftAnimations;
    }

    /**
     * @return the right-side walking animation.
     */
    public Animation<TextureRegion> getRightAnimation() {
        return this.rightAnimations;
    }

    /**
     * @return the matching animation for the given entity direction.
     */
    public Animation<TextureRegion> getEntityDirectionAnimation(final EntityDirection direction) {
        switch (direction) {
        case LEFT:
            return this.leftAnimations;
        case RIGHT:
            return this.rightAnimations;
        case UP:
            return this.upAnimations;
        case DOWN:
            return this.downAnimations;
        default:
            throw new IllegalArgumentException("Unknow direction");
        }
    }

    /**
     * @return the up-side idle frame.
     */
    public TextureRegion getUpIdle() {
        return getUpAnimation().getKeyFrame(AnimationPack.ANIMATION_START);
    }

    /**
     * @return the down-side idle frame.
     */
    public TextureRegion getDownIdle() {
        return getDownAnimation().getKeyFrame(AnimationPack.ANIMATION_START);
    }

    /**
     * @return the left-side idle frame.
     */
    public TextureRegion getLeftIdle() {
        return getLeftAnimation().getKeyFrame(AnimationPack.ANIMATION_START);
    }

    /**
     * @return the right-side idle frame.
     */
    public TextureRegion getRightIdle() {
        return getRightAnimation().getKeyFrame(AnimationPack.ANIMATION_START);
    }

    /**
     * @return the matching idle frame for the given entity direction.
     */
    public TextureRegion getEntityDirectionIdle(final EntityDirection direction) {
        return getEntityDirectionAnimation(direction).getKeyFrame(AnimationPack.ANIMATION_START);
    }
}
