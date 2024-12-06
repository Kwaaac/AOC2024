import fr.kwaaac.aoc.second.MulGatherer;

import java.io.IOException;

private final Pattern PATTERN = Pattern.compile("(?<instruction>don't\\(\\)|do\\(\\))|mul\\((?<left>\\d{1,3}),(?<right>\\d{1,3})\\)");

private void puzzle(Path input) throws IOException {
    try (var lines = Files.lines(input)) {
        var result = lines
                .flatMap(line -> PATTERN.matcher(line).results())
                .sequential()
                .gather(new MulGatherer())
                .mapToInt(match -> {
                    var left = Integer.parseInt(match.group("left"));
                    var right = Integer.parseInt(match.group("right"));
                    return left * right;
                })
                .sum();

        print(result);
    }
}

public void main() throws IOException {
    var example = Paths.get("Day03", "src", "main", "resources", "example2_input.txt");
    var input = Paths.get("Day03", "src", "main", "resources", "input.txt");
    puzzle(input);
}