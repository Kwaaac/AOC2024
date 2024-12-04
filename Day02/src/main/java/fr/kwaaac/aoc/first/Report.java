package fr.kwaaac.aoc.first;

import java.util.Arrays;

public record Report(int[] levels) {
    public static Report fromString(String string) {
        int[] levels = Arrays.stream(string.trim().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        return new Report(levels);
    }

    public boolean isSafe() {
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

    @Override
    public String toString() {
        return Arrays.toString(levels);
    }
}
