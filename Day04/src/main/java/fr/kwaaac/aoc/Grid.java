package fr.kwaaac.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Grid {
    private final char[][] grid;

    public Grid(char[][] grid) {
        this.grid = grid;
    }

    public static Grid fromFile(Path file) throws IOException {
        try (var lines = Files.lines(file)) {
            var grid = lines
                    .map(String::strip)
                    .map(String::toCharArray)
                    .toArray(char[][]::new);

            return new Grid(grid);
        }
    }

    private boolean withinBound(int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }

    private record Coord(int i, int j) {
    }

    public Stream<String> aled2() {
        var coords = new ArrayList<Coord>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                coords.add(new Coord(i, j));
            }
        }

        return coords.stream()
                .map(coord -> findXMAS(coord.i, coord.j))
                .filter(xmas -> !xmas.isEmpty());
    }

    private String findXMAS(int i, int j) {
        var sb = new StringBuilder();
        for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
                if (!withinBound(k, l)) {
                    return "";
                }
                sb.append(grid[k][l]);
            }
        }
        return sb.toString();
    }

    public Stream<String> aled() {
        var coords = new ArrayList<Coord>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                coords.add(new Coord(i, j));
            }
        }

        return coords.stream()
                .flatMap(coord -> fromLetter(coord.i, coord.j, 4));
    }

    private Stream<String> fromLetter(int i, int j, int length) {
        var directionals = directionals(i, j, length);

        // top-left
        var topLeft = new StringBuilder();
        for (int index = 0; index < length; index++) {
            var k = i - index;
            var l = j - index;
            if (!withinBound(k, l)) {
                break;
            }
            topLeft.append(grid[k][l]);
        }
        directionals.add(topLeft.toString());

        // top-right
        var topRight = new StringBuilder();
        for (int index = 0; index < length; index++) {
            var k = i - index;
            var l = j + index;
            if (!withinBound(k, l)) {
                break;
            }
            topRight.append(grid[k][l]);
        }
        directionals.add(topRight.toString());

        // downLeft
        var downLeft = new StringBuilder();
        for (int index = 0; index < length; index++) {
            var k = i + index;
            var l = j - index;
            if (!withinBound(k, l)) {
                break;
            }
            downLeft.append(grid[k][l]);
        }
        directionals.add(downLeft.toString());

        // down right
        var downRight = new StringBuilder();
        for (int index = 0; index < length; index++) {
            var k = i + index;
            var l = j + index;
            if (!withinBound(k, l)) {
                break;
            }
            downRight.append(grid[k][l]);
        }
        directionals.add(downRight.toString());

        return directionals.stream().filter(word -> word.length() == length);
    }

    private List<String> directionals(int i, int j, int length) {
        var strings = new ArrayList<String>(8);

        // Left
        var left = new StringBuilder();
        for (int k = j; k > j - length; k--) {
            if (withinBound(i, k)) {
                left.append(grid[i][k]);
            }
        }
        strings.add(left.toString());

        // Right
        var right = IntStream.range(j, j + length)
                .filter(k -> withinBound(i, k))
                .mapToObj(k -> grid[i][k])
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        strings.add(right.toString());

        // Down
        var down = IntStream.range(i, i + length)
                .filter(k -> withinBound(k, j))
                .mapToObj(k -> grid[k][j])
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        strings.add(down.toString());

        // Up
        var up = new StringBuilder();
        for (int k = i; k > i - length; k--) {
            if (withinBound(k, j)) {
                up.append(grid[k][j]);
            }
        }
        strings.add(up.toString());

        return strings;
    }

    @Override
    public String toString() {
        var sj = new StringJoiner("\n");
        for (var ints : grid) {
            sj.add(Arrays.toString(ints));
        }
        return sj.toString();
    }
}
