package fr.kwaaac.aoc.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static fr.kwaaac.aoc.util.Direction.*;

public class Grid {
    private final Element[][] grid;

    private Guard guard;

    private final Guard initialGuard;

    private Grid(Element[][] grid, Guard guard) {
        this.grid = Objects.requireNonNull(grid);
        this.guard = Objects.requireNonNull(guard);
        this.initialGuard = new Guard(guard.getDirection(), guard.getCoord());
    }

    public static Grid fromFile(Path input) throws IOException {
        Guard guard = null;
        var lines = Files.readAllLines(input);
        var grid = new ArrayList<Element[]>();
        for (int i = 0; i < lines.size(); i++) {
            var line = lines.get(i);
            var row = new ArrayList<Element>();
            char[] charArray = line.toCharArray();
            for (int j = 0; j < charArray.length; j++) {

                var c = charArray[j];
                var coord = new Coord(i, j);
                switch (c) {
                    case '.' -> row.add(new Area(coord));
                    case '#' -> row.add(new Obstruction(coord));
                    case '^' -> {
                        guard = new Guard(UP, coord);
                        row.add(new Area(coord));
                    }
                    case 'v' -> {
                        guard = new Guard(DOWN, coord);
                        row.add(new Area(coord));
                    }
                    case '<' -> {
                        guard = new Guard(LEFT, coord);
                        row.add(new Area(coord));
                    }
                    case '>' -> {
                        guard = new Guard(RIGHT, coord);
                        row.add(new Area(coord));
                    }
                }
            }
            grid.add(row.toArray(Element[]::new));
        }
        return new Grid(grid.toArray(Element[][]::new), guard);
    }

    private Element fetch(Coord c) {
        return grid[c.x()][c.y()];
    }

    public Stream<Element> process() {
        var nextStep = guard.nextStep();
        fetch(guard.getCoord()).watch();
        try {
            while (true) {
                nextStep = guard.nextStep();
                var element = fetch(nextStep);

                if (element.isBlocking()) {
                    guard.turnRight();
                    continue;
                }

                guard.stepForward();
                element.watch();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return Arrays.stream(grid).flatMap(Arrays::stream);
        }
    }

    public Optional<Grid> generate(Path input, Coord c) throws IOException {
        if (c.equals(initialGuard.getCoord())) {
            return Optional.empty();
        }

        var newGrid = fromFile(input);
        newGrid.grid[c.x()][c.y()] = new Obstruction(c);

        return Optional.of(newGrid);
    }

    public boolean hasLoop() {
        var nextStep = guard.nextStep();
        fetch(guard.getCoord()).watch();
        try {
            while (true) {
                nextStep = guard.nextStep();
                var element = fetch(nextStep);


                if (element.isBlocking()) {
                    guard.turnRight();
                    continue;
                }
                // loop
                if (guard.getDirection().equals(element.visitedDirection())) {
                    System.out.println(this);
                    return true;
                }

                guard.stepForward();
                element.watch(guard.getDirection());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }


    public List<Coord> process2() {
        var nextStep = guard.nextStep();
        fetch(guard.getCoord()).watch();
        try {
            while (true) {
                nextStep = guard.nextStep();
                var element = fetch(nextStep);

                if (element.isBlocking()) {
                    guard.turnRight();
                    continue;
                }

                guard.stepForward();
                element.watch();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            List<Coord> list = Arrays.stream(grid)
                    .flatMap(Arrays::stream)
                    .filter(Element::hasBeenWatched)
                    .map(Element::coord)
                    .toList();
            Arrays.stream(grid).flatMap(Arrays::stream).forEach(Element::unwatch);
            return list;
        }
    }

    private boolean withinBound(Coord c) {
        int x = c.x();
        int y = c.y();
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (guard.getCoord().equals(new Coord(i, j))) {
                    char dir = switch (guard.getDirection()) {
                        case UP -> '^';
                        case DOWN -> 'v';
                        case LEFT -> '<';
                        case RIGHT -> '>';
                    };
                    sb.append(dir);
                } else {
                    sb.append(grid[i][j].toString());
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
