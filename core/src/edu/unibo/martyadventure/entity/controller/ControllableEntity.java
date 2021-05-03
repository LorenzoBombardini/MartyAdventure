package edu.unibo.martyadventure.entity.controller;

import edu.unibo.martyadventure.entity.EntityDirection;
import edu.unibo.martyadventure.entity.EntityState;

public interface ControllableEntity {
    void calculateNextPosition(EntityDirection direction, float delta);
    void setState(EntityState state);
    void setDirection(EntityDirection direction);
}
