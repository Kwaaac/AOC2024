package fr.kwaaac.aoc.second;

public record LocationID(int first, int second) {
    public static LocationID fromString(String s) {
        var token = s.trim().split("\\s+");

        int first = Integer.parseInt(token[0]);
        int second = Integer.parseInt(token[1]);
        return new LocationID(first, second);
    }
}
