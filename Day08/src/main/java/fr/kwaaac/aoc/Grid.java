package fr.kwaaac.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Grid {

    private static final HashMap<Character, List<Coord>> letters = new HashMap<>();
    private final List<List<Coord>> grid;

    private Grid(List<List<Coord>> grid) {
        this.grid = List.copyOf(grid);
    }

    public static Grid fromPath(Path input) throws IOException {
        var lines = Files.readAllLines(input);

        var result = new ArrayList<List<Coord>>();
        for (int i = 0; i < lines.size(); i++) {
            var row = new ArrayList<Coord>();
            var line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                var letter = line.charAt(j);
                var coord = new Coord(i, j, letter);
                letters.computeIfAbsent(letter, ArrayList::new).add(coord);
                row.add(coord);
            }
            result.add(row);
        }

        return new Grid(result);
    }

    private boolean setAntinode(Coord c) {
        if (grid.get(c.x()).get(c.y()).letter() != '#') {
            grid.get(c.x()).set(c.y(), c);
            return true;
        }

        return false;
    }

    private Stream<Coord> truc(List<Coord> coords) {
        return IntStream.range(0, coords.size() - 1)
                .boxed()
                .flatMap(i -> classicAntinode().apply(coords, i));
    }

    private Stream<Coord> trucHarmonique(List<Coord> coords) {
        return IntStream.range(0, coords.size() - 1)
                .boxed()
                .flatMap(i -> harmonicAntinode().apply(coords, i));
    }


    private BiFunction<List<Coord>, Integer, Stream<Coord>> classicAntinode() {
        return (coords, i) -> {
            var sub = coords.subList(i + 1, coords.size());
            var start = coords.get(i);
            return sub.stream().flatMap(end ->
                    Coord.findAntinodes(start, end, grid.size(), grid.getFirst().size()).stream()
            );
        };
    }

    private BiFunction<List<Coord>, Integer, Stream<Coord>> harmonicAntinode() {
        return (coords, i) -> {
            var sub = coords.subList(i + 1, coords.size());
            var start = coords.get(i);
            return sub.stream().flatMap(end ->
                    Coord.findAllAntinodes(start, end, grid.size(), grid.getFirst().size()).stream()
            );
        };
    }

    public long processAntinodes() {
        return letters.entrySet().stream()
                .filter(entry -> entry.getKey() != '.')
                .map(Map.Entry::getValue)
                .flatMap(this::truc)
                .filter(this::setAntinode)

                .count();
    }

    public long processHarmonicAntinodes() {
        return letters.entrySet().stream()
                .filter(entry -> entry.getKey() != '.')
                .map(Map.Entry::getValue)
                .flatMap(this::trucHarmonique)
                .filter(this::setAntinode)
                .filter(Coord::isAntinode)
                .count();
    }



    @Override
    public String toString() {
        letters.entrySet().stream().forEach(entry -> {
            System.out.println(entry.getKey() + ": " + entry.getValue().size());
        });

        var sj = new StringJoiner("\n");
        for (var row : grid) {
            sj.add(row.stream().map(Coord::toString).collect(Collectors.joining("")));
        }
        return sj.toString();
    }
}
