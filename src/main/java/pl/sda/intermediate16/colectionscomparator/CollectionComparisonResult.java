package pl.sda.intermediate16.colectionscomparator;


import lombok.Data;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

@Data
public class CollectionComparisonResult<A, B> {
    private LinkedHashSet<A> onlyInFirst = new LinkedHashSet<>();
    private LinkedHashSet<B> onlyInSecond = new LinkedHashSet<>();
    private Map<A, B> common = new LinkedHashMap<>();

    public boolean isSame() {
        return onlyInFirst.isEmpty() && onlyInSecond.isEmpty() && !common.isEmpty();
    }

}