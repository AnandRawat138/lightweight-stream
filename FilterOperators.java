
/**
 * FilterOperator.java
 *
 * Filters events based on a predicate condition.
 * If the condition is not satisfied, the event is dropped (returns null).
 */

import java.util.function.Predicate;

public class FilterOperator implements Operator {

    private final Predicate<Event> predicate;

    /**
     * Constructor with custom predicate
     */
    public FilterOperator(Predicate<Event> predicate) {
        this.predicate = predicate;
    }

    /**
     * Default constructor (example condition)
     * Keeps events with value > 50
     */
    public FilterOperator() {
        this.predicate = e -> e.getValue() > 50;
    }

    @Override
    public Event process(Event event) {
        if (event == null) return null;

        return predicate.test(event) ? event : null;
    }
}
