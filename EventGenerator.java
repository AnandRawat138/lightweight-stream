/**
 * EventGenerator.java
 *
 * Simulates a real-time data source.
 * Generates events at a controlled rate (events per second).
 */

import java.util.Random;

public class EventGenerator implements Runnable {

    private final StreamProcessor processor;
    private final int eventsPerSecond;
    private volatile boolean running = true;

    private final Random random = new Random();

    public EventGenerator(StreamProcessor processor, int eventsPerSecond) {
        this.processor = processor;
        this.eventsPerSecond = eventsPerSecond;
    }

    @Override
    public void run() {
        System.out.println("Event Generator started at rate: " + eventsPerSecond + " events/sec");

        while (running) {
            long startTime = System.nanoTime();

            // Generate batch of events
            for (int i = 0; i < eventsPerSecond; i++) {
                Event event = generateEvent();
                processor.submit(event);
            }

            // Maintain 1-second interval
            long elapsed = System.nanoTime() - startTime;
            long sleepTime = 1_000_000_000L - elapsed;

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime / 1_000_000);
                } catch (InterruptedException ignored) {}
            }
        }

        System.out.println("Event Generator stopped.");
    }

    /**
     * Generate a random event
     */
    private Event generateEvent() {
        String key = "key-" + random.nextInt(5); // simulate grouping
        double value = random.nextDouble() * 100; // 0–100 range
        long timestamp = System.currentTimeMillis();

        return new Event(key, value, timestamp);
    }

    /**
     * Stop generator
     */
    public void stop() {
        running = false;
    }
}

