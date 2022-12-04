import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Day1 extends Day {

    List<Number> elfCalories = new ArrayList<>();
    List<List<Number>> elfCaloriesSeperated = new ArrayList<>();
    List<Number> sums = new ArrayList<>();

    @Override
    public Integer solvePart1() {
        elfCalories = input.stream().map((value) -> {
            if (value.length() > 0) {
                return Integer.parseInt(value);
            }
            return null;
        }).collect(toList());

        elfCaloriesSeperated = separateCaloriesPerElf(elfCalories);

        sums = elfCaloriesSeperated.stream().map((value) ->
                value.stream().mapToInt(Number::intValue).sum()).collect(toList()
        );

        sums.sort(Comparator.comparing(Number::intValue));

        return (Integer) sums.get(sums.size() - 1);
    }

    @Override
    public Integer solvePart2() {
        int sum = 0;
        for(int i = sums.size(); i > sums.size() - 3; i--) {
            sum += sums.get(i - 1).intValue();
        }
        return sum;
    }

    public List<List<Number>> separateCaloriesPerElf(List<Number> input) {
        int size = input.size();
        int[] indexes =
                IntStream.rangeClosed(-1, size)
                        .filter(i -> i == -1 || i == size || input.get(i) == null)
                        .toArray();

        return IntStream.range(0, indexes.length - 1)
                .mapToObj(i -> input.subList(indexes[i] + 1, indexes[i + 1]))
                .collect(toList());
    }
}
