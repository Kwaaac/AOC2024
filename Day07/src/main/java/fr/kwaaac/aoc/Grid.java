package fr.kwaaac.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    private void truc(List<Coord> coords) {
        for (int i = 0; i < ; i++) {

        }
    }

    private void addAntinodes(char letter) {
        letters.entrySet().stream()
                .filter(entry -> entry.getKey() != '.')
                .forEach(entry -> {

                });

    }

    private int processAntinodes() {


        return 0;
    }

}
