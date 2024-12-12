import fr.kwaaac.aoc.ops.Calibration;
import fr.kwaaac.aoc.ops.Combinations;

import java.io.IOException;

private void puzzle(Path input) throws IOException {
    try (var lines = Files.lines(input)) {
        var sum = lines.map(line -> {
                    var tokens = line.split(": ");

                    var result = Long.parseLong(tokens[0]);
                    var numbers = Arrays.stream(tokens[1].split(" ")).map(Long::parseLong).toList();
                    var combinations = new Combinations(new char[]{'*', '+', '|'}, numbers.size() - 1);

                    return new Calibration(result, numbers, combinations);
                })
                .mapToLong(Calibration::process)
                .sum();

        print(sum);
    }
}

public void main() throws IOException {
    var example = Paths.get("Day07", "src", "main", "resources", "example_input.txt");
    var input = Paths.get("Day07", "src", "main", "resources", "input.txt");
    puzzle(input);
}