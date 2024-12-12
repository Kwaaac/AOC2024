package fr.kwaaac.aoc.ops;

public record Expr(Expression left, char op, Expression right) implements Expression {
    @Override
    public long evaluate() {
        return switch (op) {
            case '+' -> left.evaluate() + right.evaluate();
            case '*' -> left.evaluate() * right.evaluate();
            case '|' -> {
                var strLeft = left.evaluate() + "";
                var strRight = right.evaluate() + "";
                yield Long.parseLong(strLeft + strRight);
            }
            default -> throw new ArithmeticException("Invalid operator: " + op);
        };
    }
}
