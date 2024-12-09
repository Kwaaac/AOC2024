import java.util.*;

public class Update {
    private final List<Integer> update;

    private Update(List<Integer> update) {
        this.update = update;
    }

    public static Update fromLine(String line) {
        var result = line.trim().split(",");
        return new Update(Arrays.stream(result).mapToInt(Integer::parseInt).boxed().toList());
    }

    public int middle() {
        var mid = Math.floorDiv(update.size(), 2);
        return update.get(mid);
    }

    public Update sort(Map<Integer, List<Integer>> ordering) {
        var result = new ArrayList<>(update);
        result.sort((p1, p2) -> {
            var orderPage1 = ordering.getOrDefault(p1, List.of());
            var orderPage2 = ordering.getOrDefault(p2, List.of());

            // Se contiennent ou ne se contiennent pas mutuellement
            if (orderPage2.contains(p1) == orderPage1.contains(p2)) {
                return 0;
            }

            if (orderPage1.contains(p2)) {
                return -1;
            }

            return 1;
        });

        return new Update(result);
    }

    public boolean isValid(Map<Integer, List<Integer>> ordering) {
        for (int i = 0; i < update.size() - 1; i++) {
            var page = update.get(i);
            var restPages = update.subList(i + 1, update.size());

            var order = ordering.getOrDefault(page, List.of());
            var check = order.containsAll(restPages);
            if (!check) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return update.toString();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Update update1)) return false;

        return Objects.equals(update, update1.update);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(update);
    }
}
