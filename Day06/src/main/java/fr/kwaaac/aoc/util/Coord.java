package fr.kwaaac.aoc.util;

public record Coord(int x, int y) {
    public Coord plus(Coord dir) {
        return new Coord(x + dir.x, y + dir.y);
    }
}
