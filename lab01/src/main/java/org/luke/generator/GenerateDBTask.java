package org.luke.generator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@AllArgsConstructor
public class GenerateDBTask implements Runnable {

    private int i;
    private int total;
    private OldDBDataGenerator generator;
    private AtomicInteger current;
    private Executor executor;

    @Override
    public void run() {
        // execute task
        if (i > total) return;
        if (i == total) {
            boolean success = OldDBDataGenerator.tryReleaseRun();
            log.info("release lock data generator, success = {}", success);
            return;
        }
        generator.generateTransaction(i);

        // put another task to Executor
        int taskIndex = current.getAndIncrement();
        executor.execute(new GenerateDBTask(taskIndex, total, generator, current, executor));
    }
}
