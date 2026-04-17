
/**
 * Operator.java
 *
 * Core abstraction for all stream processing operators.
 * Each operator transforms an incoming Event and returns:
 *  - a modified Event (map/aggregate)
 *  - null (filter/drop)
 *
 * This matches the functional pipeline model:
 *      e_out = f(e_in)
 */

public interface Operator {

    /**
     * Process a single event.
     *
     * @param event input event
     * @return processed event or null (if filtered out)
     */
    Event process(Event event);
}

