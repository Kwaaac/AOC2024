package fr.kwaaac.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static List<Coord> findAntinodes(Coord start, Coord end, int maxI, int maxJ) {
        var v1X = 2 * end.x - start.x;
        var v1Y = 2 * end.y - start.y;

        var v2X = 2 * start.x - end.x;
        var v2Y = 2 * start.y - end.y;

        Coord v1 = new Coord(v1X, v1Y, '#');
        Coord v2 = new Coord(v2X, v2Y, '#');
        return Stream.of(v1, v2).filter(v -> v.withinBounds(maxI, maxJ)).toList();
    }

    public static List<Coord> pouet(Coord start, int vX, int vY, int x, int y) {
        var result = new ArrayList<Coord>();
        int i = 1;
        while (true) {
            var coord = new Coord(start.x + (vX * i), start.y + vY * i, '#');
            var within = coord.withinBounds(x, y);
            if (within) {
                result.add(coord);
                i++;
                continue;
            }
            return result;
        }
    }

    public static List<Coord> findAllAntinodes(Coord start, Coord end, int maxI, int maxJ) {
        var v1X = (end.x - start.x);
        var v1Y = (end.y - start.y);
        var coordsV1 = pouet(start, v1X, v1Y, maxI, maxJ);

        var v2X = (start.x - end.x);
        var v2Y = (start.y - end.y);
        var coordsV2 = pouet(end, v2X, v2Y, maxI, maxJ);

        return Stream.of(List.of(start, end), coordsV1, coordsV2).flatMap(List::stream).filter(v -> v.withinBounds(maxI, maxJ)).toList();
    }

    public String coordsString() {
        return "(" + x + ", " + y + ")";
    }


    public boolean withinBounds(int x, int y) {
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
        return letter == '#';
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
        return letter + "";
    }

    @Override
    public int compareTo(Coord o) {
        var row = Integer.compare(x, o.x);
        if (row != 0) return row;

        return Integer.compare(y, o.y);
    }

}
