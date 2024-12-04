import fr.kwaaac.aoc.second.Report;

private void puzzle(Path input) throws IOException {
    try (var lines = Files.lines(input)) {
        print(lines.map(Report::fromString).filter(Report::isSafe).count());
    }
}

public void main() throws IOException {
    var example = Paths.get("Day02", "src", "main", "resources", "example_input.txt");
    var input = Paths.get("Day02", "src", "main", "resources", "input.txt");
    puzzle(input);
}