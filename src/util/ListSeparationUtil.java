package util;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class ListSeparationUtil {
    public static <T> List<List<T>> separate(List<T> input) {
        int size = input.size();
        int[] indexes =
                IntStream.rangeClosed(-1, size)
                        .filter(i -> i == -1 || i == size || input.get(i) == null || input.get(i).equals(""))
                        .toArray();

        return IntStream.range(0, indexes.length - 1)
                .mapToObj(i -> input.subList(indexes[i] + 1, indexes[i + 1]))
                .collect(toList());
    }
}
