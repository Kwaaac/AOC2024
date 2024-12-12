package fr.kwaaac.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Coord implements Comparable<Coord> {
    private final int x;
    private final int y;
    private final char letter;
    private boolean antinode;

    public Coord(int x, int y, char letter) {
        this.x = x;
        this.y = y;
        this.letter = letter;
    }

    public static List<Coord> findAntinodes(Coord c1, Coord c2, int maxI, int maxJ) {

    }

    private boolean withinBounds(int x, int y) {
        return this.x >= 0 && this.x < x && this.y >= 0 && this.y < y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public char letter() {
        return letter;
    }

    public boolean isAntinode() {
        return antinode;
    }

    public void setAntinode() {
        if (letter != '.') antinode = true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Coord that &&
                this.x == that.x &&
                this.y == that.y &&
                this.letter == that.letter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, letter);
    }

    @Override
    public String toString() {
        return "Coord[" +
                "x=" + x + ", " +
                "y=" + y + ", " +
                "letter=" + letter + ']';
    }

    @Override
    public int compareTo(Coord o) {
        var row = Integer.compare(x, o.x);
        if (row != 0) return row;

        return Integer.compare(y, o.y);
    }

}
