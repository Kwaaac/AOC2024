import java.io.IOException;

private void puzzle(Path input) throws IOException {
    try (BufferedReader br = Files.newBufferedReader(input)) {
        var ordering = new HashMap<Integer, List<Integer>>();
        String line = br.readLine();
        do {
            var tokens = line.split("\\|");
            ordering.computeIfAbsent(Integer.parseInt(tokens[0]), ArrayList::new).add(Integer.parseInt(tokens[1]));
            line = br.readLine();
        } while (!line.isEmpty());

        var result = br.lines()
                .map(Update::fromLine)
                .filter(update -> !update.isValid(ordering))
                .map(update -> update.sort(ordering))
                .peek(System.out::println)
                .mapToInt(Update::middle)
                .sum();

        print(result);
    }
}

public void main() throws IOException {
    var example = Paths.get("Day05", "src", "main", "resources", "example_input.txt");
    var input = Paths.get("Day05", "src", "main", "resources", "input.txt");
    puzzle(input);
}