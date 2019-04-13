package pl.sda.intermediate16.colectionscomparator;


@FunctionalInterface
public interface CollectionComparisonComparator<A, B> {
    boolean isEqual(A elementA, B elementB);

}