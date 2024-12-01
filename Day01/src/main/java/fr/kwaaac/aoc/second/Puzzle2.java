import fr.kwaaac.aoc.second.LocationID;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static java.io.IO.print;


public void puzzle(Path input) throws IOException {
    try (var lines = Files.lines(input)) {
        var locationIDs = lines.map(LocationID::fromString).toList();
        var firsts = locationIDs.stream().mapToInt(LocationID::first).sorted().toArray();
        var seconds = locationIDs.stream().mapToInt(LocationID::second).sorted().toArray();

        print(Arrays.stream(firsts).map(first -> {
                    int count = (int) Arrays.stream(seconds).filter(i -> i == first).count();
                    return first * count;
                })
                .sum());
    }
}

public void main() throws IOException {
    var example = Paths.get("Day01", "src", "main", "resources", "example_input.txt");
    var input = Paths.get("Day01", "src", "main", "resources", "input.txt");
    puzzle(input);
}