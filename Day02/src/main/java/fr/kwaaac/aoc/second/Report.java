package fr.kwaaac.aoc.second;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Report(int[] levels) {
    public static Report fromString(String string) {
        int[] levels = Arrays.stream(string.trim().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        return new Report(levels);
    }

    private boolean isUsallySafe(int[] levels) {
        if (levels[0] == levels[1]) return false;

        boolean increasing = levels[0] < levels[1];

        for (int i = 1, last = levels[0];
             i < levels.length;
             last = levels[i], i++) {

            int current = levels[i];

            int diff = Math.abs(last - current);
            if (diff > 3 || ((!increasing || last >= current) && (increasing || last <= current))) {
                return false;
            }
        }
        return true;
    }

    public boolean isSafe() {
        var usuallySafe = isUsallySafe(this.levels);
        if (usuallySafe) return true;
        // idiot brute force is the way
        return IntStream.range(0, this.levels.length)
                .mapToObj(i -> {
                    var list = Arrays.stream(levels).boxed().collect(Collectors.toList());
                    list.remove(i);
                    return list.stream().mapToInt(j -> j).toArray();
                })
                .anyMatch(this::isUsallySafe);
    }

    @Override
    public String toString() {
        return Arrays.toString(levels);
    }
}
