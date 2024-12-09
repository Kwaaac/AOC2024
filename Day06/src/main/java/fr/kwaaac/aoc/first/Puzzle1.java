import fr.kwaaac.aoc.util.Element;
import fr.kwaaac.aoc.util.Grid;

import java.io.IOException;

private void puzzle(Path input) throws IOException {
    var grid = Grid.fromFile(input);
    System.out.println(grid);
    var res = grid.process().filter(Element::hasBeenWatched).count();
    print(res);
}

public void main() throws IOException {
    var example = Paths.get("Day06", "src", "main", "resources", "example_input.txt");
    var input = Paths.get("Day06", "src", "main", "resources", "input.txt");
    puzzle(input);
}