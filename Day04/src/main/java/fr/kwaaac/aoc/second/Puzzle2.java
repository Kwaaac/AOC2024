import fr.kwaaac.aoc.Grid;

import java.io.IOException;

private void puzzle(Path input) throws IOException {
    var p1 = Pattern.compile("M.S.A.M.S");
    var p2 = Pattern.compile("S.S.A.M.M");
    var p3 = Pattern.compile("M.M.A.S.S");
    var p4 = Pattern.compile("S.M.A.S.M");
    var patterns = List.of(p1, p2, p3, p4);
    var res = Grid.fromFile(input)
            .aled2()
            .peek(System.out::println)
            .filter(xmas -> patterns.stream().map(p -> p.matcher(xmas)).anyMatch(Matcher::find))
            .count();

    print(res);
}

public void main() throws IOException {
    var example = Paths.get("Day04", "src", "main", "resources", "example_input.txt");
    var input = Paths.get("Day04", "src", "main", "resources", "input.txt");
    puzzle(input);
}