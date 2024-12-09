package fr.kwaaac.aoc.util;

import static fr.kwaaac.aoc.util.Direction.*;

public class Guard {

    private Direction direction;

    private Coord coord;

    public Guard(Direction direction, Coord coord) {
        this.direction = direction;
        this.coord = coord;
    }

    public Direction getDirection() {
        return direction;
    }

    public Coord getCoord() {
        return coord;
    }

    public void turnRight() {
        var ordinal = (direction.ordinal() + 1) % 4;
        direction = Direction.values()[ordinal];
    }

    public Coord nextStep() {
        return switch (direction) {
            case UP -> UP.coord().plus(coord);
            case DOWN -> DOWN.coord().plus(coord);
            case LEFT -> LEFT.coord().plus(coord);
            case RIGHT -> RIGHT.coord().plus(coord);
        };
    }

    public void stepForward() {
        this.coord = nextStep();
    }

}
