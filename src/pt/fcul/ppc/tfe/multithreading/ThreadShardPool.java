package pt.fcul.ppc.tfe.multithreading;

import pt.fcul.ppc.tfe.features.api.Feature;
import pt.fcul.ppc.tfe.transaction.Transaction;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadShardPool {
    private final HashMap<ThreadShardType, ThreadPoolExecutor> pool;

    public ThreadShardPool() {
        this.pool = new HashMap<>();
        initializePool();
    }

    private void initializePool() {
        for (ThreadShardType type : ThreadShardType.values()) {
            pool.put(type, (ThreadPoolExecutor)
                    Executors.newFixedThreadPool(1));
        }
    }

    public void execute(Feature feature, Transaction transaction) {
        getShard(feature, transaction).execute(() -> {
            // System.out.println(
            //         "[" + Thread.currentThread().getName() + "] " +
            //                 "Running " + feature.getClass().getSimpleName() +
            //                 " on " + transaction.toString());
            feature.run(transaction);
        });
    }

    private ThreadPoolExecutor getShard(Feature feature, Transaction transaction) {
        switch (feature.getPrimaryKey()) {
            case BUYER:
                if (isEven(transaction.getBuyer())) {
                    return pool.get(ThreadShardType.EVEN);
                }
                return pool.get(ThreadShardType.ODD);
            case SELLER:
                if (isEven(transaction.getSeller())) {
                    return pool.get(ThreadShardType.EVEN);
                }
                return pool.get(ThreadShardType.ODD);
            case CREDIT_CARD:
                if (isEven(transaction.getCreditCard())) {
                    return pool.get(ThreadShardType.EVEN);
                }
                return pool.get(ThreadShardType.ODD);
        }
        return null;
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }

    public void shutdown() {
        pool.forEach((type, pool) -> {
            System.out.println("Shutting down " + type + " pool. " +
                    "Pool info: " + pool.toString() + "...");
            pool.shutdown();
        });
        waitForCompletion();
    }

    private void waitForCompletion() {
        try {
            for (ThreadPoolExecutor pool : pool.values()) {
                pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    enum ThreadShardType {
        ODD, EVEN
    }
}
