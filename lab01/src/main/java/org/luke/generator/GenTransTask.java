package org.luke.generator;

import lombok.AllArgsConstructor;

/**
 * Generate a transaction
 */
@AllArgsConstructor
public class GenTransTask implements Runnable {

    /**
     * Index of the transaction to generate
     */
    private int index;

    /**
     * Data generator
     */
    private OldDBDataGenerator generator;

    @Override
    public void run() {
        generator.generateTransaction(index);
    }
}
