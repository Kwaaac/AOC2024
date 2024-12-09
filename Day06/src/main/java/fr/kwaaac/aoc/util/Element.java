package fr.kwaaac.aoc.util;

public interface Element {
    default boolean isBlocking() {
        return true;
    }

    Coord coord();

    default boolean hasBeenWatched() {
        return false;
    }

    default void watch() {
    }

    default void unwatch() {
        
    }

    default void watch(Direction direction) {

    }

    default Direction visitedDirection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
