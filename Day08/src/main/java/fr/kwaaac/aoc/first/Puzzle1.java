import fr.kwaaac.aoc.Grid;

import java.io.IOException;

private void puzzle(Path input) throws IOException {
    var grid = Grid.fromPath(input);
    println(grid.processAntinodes());
}

public void main() throws IOException {
    var example = Paths.get("Day08", "src", "main", "resources", "example_input.txt");
    var input = Paths.get("Day08", "src", "main", "resources", "input.txt");
    puzzle(input);
}