package pl.sda.intermediate16;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import pl.sda.intermediate16.users.User;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class SetTest {


    @Test
    void treeSet() {
        Comparator<User> userComparator = Comparator.comparingInt(a -> a.getName().length());
        Set<User> users = new TreeSet<>(userComparator);
        User user = new User();
        user.setName("Adam");
        User user2 = new User();
        user2.setName("Ewa");
        users.add(user);
        users.add(user2);
        System.out.println(users);


    }


    @Test
    void genMethod() {
        List<String> names = Lists.newArrayList("Adam", "Ewa", "Marek");
        Comparator<String> comparator = Comparator.naturalOrder();
        System.out.println(sortSomething(names, comparator));

        List list2 = Lists.newArrayList("a", "4", "1", 2, "3");
        Comparator<Object> comparator1 = (a, b) -> a.toString().compareTo(b.toString());
        System.out.println(sortSomething(list2, comparator1));

    }

    public <T> List<T> sortSomething(List<T> list, Comparator<T> comparator) {
        return list.stream()
                .sorted(comparator)
                .collect(Collectors.toList());


    }


    public <T extends Number> Double sum(List<T> list) {
        return list.stream()
                .mapToDouble(Number::doubleValue)
                .sum();

    }

    @Test
    void removeAllTest() {
        List<String> firstList = Lists.newArrayList();
        List<String> secondList = Lists.newArrayList();
        for (int i = 0; i < 300_000; i++) {
            firstList.add("cos" + i);
            secondList.add("cos" + i);
        }
        secondList.add("cos");
        HashSet<String> strings = Sets.newHashSet(secondList);
        strings.removeAll(firstList);
        System.out.println(strings);


    }
}
