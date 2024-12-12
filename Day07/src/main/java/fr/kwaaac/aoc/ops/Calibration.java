package fr.kwaaac.aoc.ops;

import java.util.List;

public record Calibration(long result, List<Long> numbers, Combinations combinations) {
    public long process() {
        var values = numbers.stream().map(Value::new).toList();
        var valid = combinations.generateCombinations().stream()
                .map(combi -> {
                    Expression expr = new Expr(new Value(0), '+', values.getFirst());
                    for (int i = 0; i < combi.length(); i++) {
                        expr = new Expr(expr, combi.charAt(i), values.get(i + 1));
                    }
                    return expr;
                })
                .map(Expression::evaluate)
                .anyMatch(eval -> eval == result);

        return valid ? result : 0;
    }
}
