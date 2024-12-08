import fr.kwaaac.aoc.Grid;

import java.io.IOException;

private void puzzle(Path input) throws IOException {
    var res = Grid.fromFile(input)
            .aled()
            .filter(str -> "XMAS".equals(str) || "SAMX".equals(str))
            .count();

    print(res/2);
}

public void main() throws IOException {
    var example = Paths.get("Day04", "src", "main", "resources", "example_input.txt");
    var input = Paths.get("Day04", "src", "main", "resources", "input.txt");
    puzzle(input);
}