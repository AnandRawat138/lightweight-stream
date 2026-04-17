
/**
 * MapOperator.java
 *
 * Transforms each event using a user-defined function.
 * This represents the "map" stage in the stream pipeline.
 */

import java.util.function.Function;

public class MapOperator implements Operator {

    private final Function<Event, Event> mapper;

    /**
     * Constructor with custom mapping function
     */
    public MapOperator(Function<Event, Event> mapper) {
        this.mapper = mapper;
    }

    /**
     * Default constructor (example transformation)
     * Multiplies value by 1.1
     */
    public MapOperator() {
        this.mapper = e -> {
            e.setValue(e.getValue() * 1.1);
            return e;
        };
    }

    @Override
    public Event process(Event event) {
        if (event == null) return null;
        return mapper.apply(event);
    }
}
