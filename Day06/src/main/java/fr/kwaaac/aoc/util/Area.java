package fr.kwaaac.aoc.util;

import java.util.Objects;

public class Area implements Element {
    private boolean watch;

    private Direction direction;

    private final Coord coord;

    public Area(Coord coord) {
        this.coord = coord;
    }

    @Override
    public boolean isBlocking() {
        return false;
    }

    @Override
    public Coord coord() {
        return coord;
    }

    @Override
    public boolean hasBeenWatched() {
        return watch;
    }

    @Override
    public void watch() {
        watch = true;
    }

    @Override
    public void unwatch() {
        watch = false;
    }

    @Override
    public void watch(Direction direction) {
        watch = true;
        this.direction = Objects.requireNonNull(direction);
    }

    @Override
    public String toString() {
        return watch ? "X" : ".";
    }

    @Override
    public Direction visitedDirection() {
        return direction;
    }
}
