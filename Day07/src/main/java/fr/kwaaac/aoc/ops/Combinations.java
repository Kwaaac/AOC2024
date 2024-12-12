package fr.kwaaac.aoc.ops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Combinations {
    private static final HashMap<Integer, List<String>> CACHE = new HashMap<>();

    private final List<String> result = new ArrayList<>();

    private final char[] characters;

    private final int length;

    public Combinations(char[] characters, int length) {
        this.characters = Arrays.copyOf(characters, characters.length);
        this.length = length;
    }

    public List<String> generateCombinations() {
        return CACHE.computeIfAbsent(length, k -> {
            generateCombinationsRecursive(length, "");
            return result;
        });
    }

    private void generateCombinationsRecursive(int length, String current) {
        if (current.length() == length) {
            result.add(current);
            return;
        }

        for (char c : characters) {
            generateCombinationsRecursive(length, current + c);
        }
    }
}
