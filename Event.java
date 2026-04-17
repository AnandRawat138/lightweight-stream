/**
 * Event.java
 *
 * Represents a single data unit in the stream processing engine.
 * Each event contains:
 *  - key       : identifier for grouping
 *  - value     : numerical payload
 *  - timestamp : event creation time
 */

public class Event {

    private final String key;
    private double value;
    private final long timestamp;

    public Event(String key, double value, long timestamp) {
        this.key = key;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getKey() {
        return key;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Event{" +
                "key='" + key + '\'' +
                ", value=" + value +
                ", timestamp=" + timestamp +
                '}';
    }
}

