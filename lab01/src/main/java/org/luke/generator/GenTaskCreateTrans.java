package org.luke.generator;


import lombok.AllArgsConstructor;

import java.util.concurrent.Executor;

/**
 * Generate and put multiple CreateTransactionTask to Executor
 */
@AllArgsConstructor
public class GenTaskCreateTrans implements Runnable {

    /**
     * Total number of tasks to generate
     */
    private int nTaskTotal;

    /**
     * Current number of tasks generated
     */
    private int nTaskCurrent;

    /**
     * Max number of tasks to generate at a time
     */
    private int nTaskGen;

    /**
     * Executor to run the tasks
     */
    private Executor executor;

    /**
     * Data generator
     */
    private OldDBDataGenerator generator;

    @Override
    public void run() {
        if (nTaskCurrent >= nTaskTotal) return;
        int nTaskToGen = Math.min(nTaskGen, nTaskTotal - nTaskCurrent);
        for (int i = 0; i < nTaskToGen; i++) {
            executor.execute(new GenTransTask(nTaskCurrent + i, generator));
        }
        nTaskCurrent += nTaskToGen;
        executor.execute(this);
    }
}
