package fr.kwaaac.aoc.ops;

public record Value(long val) implements Expression {
    @Override
    public long evaluate() {
        return val;
    }
}
