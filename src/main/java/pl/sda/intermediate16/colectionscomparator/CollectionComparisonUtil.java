package pl.sda.intermediate16.colectionscomparator;

import org.apache.commons.lang3.NotImplementedException;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.function.Function;


public class CollectionComparisonUtil {
    public static <A, B> CollectionComparisonResult<A, B> compareCollections(Collection<A> collectionA, Collection<B> collectionB, CollectionComparisonComparator<A, B> comparator) {
        LinkedHashSet<A> firstHS = new LinkedHashSet<>();
        LinkedHashSet<B> secondHS = new LinkedHashSet<>();
        Map<A, B> abMap = new HashMap<>();

    
        CollectionComparisonResult<A, B> occr = new CollectionComparisonResult<>();
        occr.setOnlyInFirst(firstHS);
        occr.setOnlyInSecond(secondHS);
        occr.setCommon(abMap);
        return occr;

    }


    public static <ELEMENT, KEY> CollectionComparisonResult<ELEMENT, ELEMENT> compareCollections(Collection<ELEMENT> collectionA, Collection<ELEMENT> collectionB, Function<ELEMENT, KEY> keyProvider) {
        throw new NotImplementedException("Do dzieła!"); //todo

    }

}