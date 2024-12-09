package fr.kwaaac.aoc.util;

public enum Direction {
    UP(-1, 0),
    RIGHT(0, 1),
    DOWN(1, 0),
    LEFT(0, -1);

    private final Coord coord;

    Direction(int x, int y) {
        coord = new Coord(x, y);
    }

    public Coord coord() {
        return coord;
    }
}