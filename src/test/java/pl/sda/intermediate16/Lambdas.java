package pl.sda.intermediate16;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lambdas {

    private List<String> animals = Arrays.asList("cat", "dog", "mouse", "rat", "pig", "rabbit", "hamster", "parrot", null); // Tradycyjna pętla
    List<String> someList = new ArrayList<>();

    @Test
    void lambdasOne() {
        List<String> transformedAnimals = animals.stream()
                .filter(StringUtils::isNotBlank)
                .map(String::trim)
                .collect(Collectors.toList());

        for (int i = 0; i < transformedAnimals.size(); i++) {
            if (i < transformedAnimals.size() - 1) {
                System.out.print(transformedAnimals.get(i) + ", ");
            } else {
                System.out.println(transformedAnimals.get(i) + ".");
            }
        }
    }

    public interface SuperChecker {
        boolean check(Integer a);
    }

    @Test
    void checkers() {
        SuperChecker superChecker = a -> a % 2 == 0;

    }

    @FunctionalInterface
    public interface MyBiComparator<T, E> {
        Integer compare(T first, E second);
    }

    @Test
    void myBiComparatorTest() {
        int first = 3;
        String second = "4";

        MyBiComparator<Integer, String> myBiComparator = (a, b) -> a - Integer.valueOf(b);
        System.out.println(myBiComparator.compare(first, second));

    }


}
