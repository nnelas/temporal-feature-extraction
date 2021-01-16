package pt.fcul.ppc.tfe.multithreading;

import pt.fcul.ppc.tfe.features.api.Feature;
import pt.fcul.ppc.tfe.transaction.Transaction;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MultiThreadManager {
    private final ThreadPoolExecutor threadPoolExecutor;

    public MultiThreadManager(int numberOfThreads) {
        this.threadPoolExecutor = (ThreadPoolExecutor)
                Executors.newFixedThreadPool(numberOfThreads);
    }

    public void execute(Feature feature, Transaction transaction) {
        threadPoolExecutor.execute(() -> {
            System.out.println("Running " + feature.getClass().getSimpleName() +
                    " on " + Thread.currentThread().getName());
            feature.run(transaction);
        });
    }

    public void shutdown() {
        threadPoolExecutor.shutdown();
        waitForCompletion();
    }

    private void waitForCompletion() {
        try {
            threadPoolExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
