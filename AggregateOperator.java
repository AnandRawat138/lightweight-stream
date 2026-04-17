
/**
 * AggregateOperator.java
 *
 * Performs stateful aggregation over incoming events.
 * Maintains per-key counts and sum (example aggregation).
 *
 * This demonstrates stateful stream processing.
 */

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class AggregateOperator implements Operator {

    /**
     * Stores count per key
     */
    private final ConcurrentHashMap<String, AtomicLong> countMap = new ConcurrentHashMap<>();

    /**
     * Stores sum per key
     */
    private final ConcurrentHashMap<String, Double> sumMap = new ConcurrentHashMap<>();

    @Override
    public Event process(Event event) {
        if (event == null) return null;

        String key = event.getKey();

        // Update count (thread-safe)
        countMap.putIfAbsent(key, new AtomicLong(0));
        countMap.get(key).incrementAndGet();

        // Update sum (simple atomic pattern)
        sumMap.merge(key, event.getValue(), Double::sum);

        return event;
    }

    /**
     * Get count for a key
     */
    public long getCount(String key) {
        return countMap.getOrDefault(key, new AtomicLong(0)).get();
    }

    /**
     * Get sum for a key
     */
    public double getSum(String key) {
        return sumMap.getOrDefault(key, 0.0);
    }

    /**
     * Get average for a key
     */
    public double getAverage(String key) {
        long count = getCount(key);
        return count == 0 ? 0 : getSum(key) / count;
    }

    /**
     * Debug helper (optional)
     */
    public void printStats() {
        System.out.println("---- Aggregation Stats ----");
        for (String key : countMap.keySet()) {
            System.out.println("Key: " + key +
                    " | Count: " + getCount(key) +
                    " | Avg: " + getAverage(key));
        }
    }
}
