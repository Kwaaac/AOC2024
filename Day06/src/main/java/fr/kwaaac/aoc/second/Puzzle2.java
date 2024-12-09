import fr.kwaaac.aoc.util.Element;
import fr.kwaaac.aoc.util.Grid;

import java.io.IOException;

private void puzzle(Path input) throws IOException {
    var grid = Grid.fromFile(input);
    System.out.println(grid);
    var res = grid.process2().stream()
            .flatMap(c -> {
                try {
                    // degueux, mais les references, c'est zut
                    return grid.generate(input, c).stream();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            })
            .filter(Grid::hasLoop)
            .count();

    print(res);
}

public void main() throws IOException {
    var example = Paths.get("Day06", "src", "main", "resources", "example_input.txt");
    var input = Paths.get("Day06", "src", "main", "resources", "input.txt");
    puzzle(input);
}