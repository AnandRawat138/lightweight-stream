/**
 * StreamProcessor.java
 *
 * Core execution engine of the stream processing system.
 *
 * Responsibilities:
 *  - Manage event queue
 *  - Execute processing pipeline
 *  - Handle multi-threading
 *  - Apply backpressure control
 */

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class StreamProcessor {

    private final ConcurrentLinkedQueue<Event> queue;
    private final ExecutorService executor;
    private final Pipeline pipeline;

    // Metrics
    private final AtomicLong processedCount = new AtomicLong(0);

    // Config
    private final int THREAD_COUNT;
    private final int BACKPRESSURE_THRESHOLD = 10000;

    private volatile boolean running = true;

    public StreamProcessor(Pipeline pipeline, int threadCount) {
        this.pipeline = pipeline;
        this.queue = new ConcurrentLinkedQueue<>();
        this.THREAD_COUNT = threadCount;
        this.executor = Executors.newFixedThreadPool(threadCount);
    }

    /**
     * Start worker threads
     */
    public void start() {
        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(this::processLoop);
        }
    }

    /**
     * Submit event into queue (with backpressure)
     */
    public void submit(Event event) {
        if (queue.size() > BACKPRESSURE_THRESHOLD) {
            applyBackpressure();
        }
        queue.add(event);
    }

    /**
     * Core processing loop
     */
    private void processLoop() {
        while (running) {
            Event event = queue.poll();

            if (event != null) {
                Event result = pipeline.execute(event);

                if (result != null) {
                    emit(result);
                    processedCount.incrementAndGet();
                }
            } else {
                // Avoid busy spinning
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    /**
     * Output sink (currently no-op for performance)
     */
    private void emit(Event event) {
        // Uncomment for debugging
        // System.out.println(event);
    }

    /**
     * Backpressure mechanism
     */
    private void applyBackpressure() {
        try {
            Thread.sleep(1); // simple throttling
        } catch (InterruptedException ignored) {}
    }

    /**
     * Stop processing
     */
    public void stop() {
        running = false;
        executor.shutdown();
    }

    /**
     * Get processed event count
     */
    public long getProcessedCount() {
        return processedCount.get();
    }

    /**
     * Get queue size
     */
    public int getQueueSize() {
        return queue.size();
    }
}

