/**
 * Main.java
 *
 * Entry point for the Lightweight Stream Processing Engine.
 * Sets up pipeline, starts processing, and measures performance.
 */

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {

        // -------------------------------
        // Configuration
        // -------------------------------
        int threadCount = Runtime.getRuntime().availableProcessors();
        int eventRate = 10000; // events per second
        int durationSeconds = 30;

        System.out.println("Starting Lightweight Stream Processing Engine...");
        System.out.println("Threads: " + threadCount);
        System.out.println("Event Rate: " + eventRate + " events/sec");
        System.out.println("Duration: " + durationSeconds + " seconds");

        // -------------------------------
        // Build Pipeline
        // -------------------------------
        AggregateOperator aggregateOperator = new AggregateOperator();

        Pipeline pipeline = new Pipeline(Arrays.asList(
                new FilterOperator(),     // filter stage
                new MapOperator(),        // transform stage
                aggregateOperator        // stateful aggregation
        ));

        // -------------------------------
        // Initialize Processor
        // -------------------------------
        StreamProcessor processor = new StreamProcessor(pipeline, threadCount);
        processor.start();

        // -------------------------------
        // Start Event Generator
        // -------------------------------
        EventGenerator generator = new EventGenerator(processor, eventRate);
        Thread generatorThread = new Thread(generator);
        generatorThread.start();

        // -------------------------------
        // Metrics Tracking
        // -------------------------------
        long startTime = System.currentTimeMillis();
        long lastCount = 0;

        for (int i = 0; i < durationSeconds; i++) {
            Thread.sleep(1000);

            long currentCount = processor.getProcessedCount();
            long throughput = currentCount - lastCount;

            System.out.println(
                "Time: " + (i + 1) + "s | " +
                "Throughput: " + throughput + " events/sec | " +
                "Total: " + currentCount + " | " +
                "Queue: " + processor.getQueueSize()
            );

            lastCount = currentCount;
        }

        // -------------------------------
        // Stop System
        // -------------------------------
        generator.stop();
        processor.stop();

        long totalTime = System.currentTimeMillis() - startTime;
        long totalEvents = processor.getProcessedCount();

        double avgThroughput = totalEvents / (totalTime / 1000.0);

        System.out.println("\n--- FINAL RESULTS ---");
        System.out.println("Total Events Processed: " + totalEvents);
        System.out.println("Average Throughput: " + (long) avgThroughput + " events/sec");

        // -------------------------------
        // Print Aggregation Stats
        // -------------------------------
        aggregateOperator.printStats();

        System.out.println("System shutdown complete.");
    }
}

