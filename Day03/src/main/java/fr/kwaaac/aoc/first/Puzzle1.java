import java.io.IOException;

private final Pattern PATTERN = Pattern.compile("mul\\((?<left>\\d{1,3}),(?<right>\\d{1,3})\\)");

/**
 * Result a IntStream of multiplied mul found in the line
 *
 * @param line line to parse to find mul(X,X)
 * @return IntStream containing the multiplied mul(X,X)
 */
private IntStream findMul(String line) {
    return PATTERN.matcher(line).results()
            .mapToInt(match -> {
                var left = Integer.parseInt(match.group("left"));
                var right = Integer.parseInt(match.group("right"));
                return left * right;
            });
}

private void puzzle(Path input) throws IOException {
    try (var lines = Files.lines(input)) {
        var result = lines.flatMapToInt(this::findMul).sum();
        print(result);
    }
}

public void main() throws IOException {
    var example = Paths.get("Day03", "src", "main", "resources", "example_input.txt");
    var input = Paths.get("Day03", "src", "main", "resources", "input.txt");
    puzzle(input);
}