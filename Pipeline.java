
/**
 * Pipeline.java
 *
 * Represents a sequence of operators applied to each event.
 * Implements operator chaining (functional composition).
 */

import java.util.List;

public class Pipeline {

    private final List<Operator> operators;

    public Pipeline(List<Operator> operators) {
        this.operators = operators;
    }

    /**
     * Execute the full operator chain on an event
     */
    public Event execute(Event event) {
        Event current = event;

        for (Operator operator : operators) {
            if (current == null) return null; // short-circuit (filter)
            current = operator.process(current);
        }

        return current;
    }
}
