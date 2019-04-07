package pl.sda.intermediate16;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ProteinsChecker {
    @Test
    void checkProtein() {
        String path = "C:/Users/mateu/IdeaProjects/lancuchy_bialkowe_dane.txt";
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            String firstLine = scanner.nextLine();
            String secondLine = scanner.nextLine();
            if (firstLine.length() == secondLine.length()) {
                char[] chars = firstLine.toCharArray();
                Arrays.sort(chars);
                char[] a = secondLine.toCharArray();
                Arrays.sort(a);
                boolean equals = Arrays.equals(chars, a);
                if (equals == true) {
                    System.out.println("Równe");
                } else
                    System.out.println("Różne");
            }


        }


    }
}
