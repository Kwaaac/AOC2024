package fr.kwaaac.aoc.util;

public record Obstruction(Coord coord) implements Element {

    @Override
    public String toString() {
        return "#";
    }
}
