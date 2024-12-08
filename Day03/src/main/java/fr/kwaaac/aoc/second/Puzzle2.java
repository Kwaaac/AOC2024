import fr.kwaaac.aoc.second.MulGatherer;
import fr.kwaaac.aoc.second.MulState;

import java.io.IOException;

private final Pattern PATTERN = Pattern.compile("(?<instruction>don't\\(\\)|do\\(\\))|mul\\((?<left>\\d{1,3}),(?<right>\\d{1,3})\\)");

private void puzzle(Path input) throws IOException {
    try (var lines = Files.lines(input)) {
        var result = lines
                .flatMap(line -> PATTERN.matcher(line).results())
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

private void puzzleGatherFactory(Path input) throws IOException {
    try (var lines = Files.lines(input)) {
        var result = lines
                .flatMap(line -> PATTERN.matcher(line).results())
                .gather(Gatherer.ofSequential(
                        MulState::new,
                        Gatherer.Integrator.<MulState, MatchResult, MatchResult>ofGreedy((instruction, match, downstream) -> {
                            // On trouve une instruction
                            if (!match.group().contains("mul")) {
                                var newInstruction = match.group().equals("do()");
                                instruction.setInstruction(newInstruction);
                                return true;
                            }

                            // Si do() on ajoute au downstream le mul
                            if (instruction.instruction()) {
                                downstream.push(match);
                            }

                            return true;
                        })
                ))
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
    puzzleGatherFactory(input);
}