package cs2030.simulator;

/**
 * A utility class to pair two items.
 * created to facilitate returning of
 * two values in a function.
 *
 **/
public final class Pair<T, U> {
    /**
     * First item in the pair.
     */
    public T first;
    /**
     * Second item in the pair.
     */
    public U second;

    /**
     * A private constructor for a new pair.
     * @param first     the first item in the pair
     * @param second    the second item in the pair
     */
    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Creates a new pair of items.
     *
     * @param first First item in the pair
     * @param second Second item in the pair
     * @return The new pair
     **/
    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }
}
