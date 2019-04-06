package pl.sda.intermediate16.calculator;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {


    public static Integer adding(String text) {
        if (StringUtils.isBlank(text)) {
            return 0;
        }

        if (text.startsWith("//")) {
            Pattern pattern = Pattern.compile("//(.*)\n(.*)");
            Matcher matcher = pattern.matcher(text);
            matcher.matches();
            String delimiter = matcher.group(1);
            String[] splitted = text.split("\n");
            return tokenizeAndSum(splitted[1], String.valueOf(delimiter));
        }
        return tokenizeAndSum(text, ",|\n");
    }

    private static Integer tokenizeAndSum(String text, String regex) {
        List<Integer> integerList = Arrays.stream(text.split(regex))
                .map(e -> Integer.valueOf(e.trim()))
                .collect(Collectors.toList());

        List<Integer> negativeValues = integerList.stream()
                .filter(i -> i < 0)
                .collect(Collectors.toList());

        if (!negativeValues.isEmpty()) {
            throw new NegativeNumberFoundException("Tak nie można! " + negativeValues);
        }

        return integerList.stream()
                .filter(a -> a <= 1000)
                .reduce((a, b) -> a + b)
                .orElseGet(() -> superHardLongAndSourcesNeedingMethod());
    }

    private static Integer superHardLongAndSourcesNeedingMethod() {
        System.out.println("NIE OK");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
