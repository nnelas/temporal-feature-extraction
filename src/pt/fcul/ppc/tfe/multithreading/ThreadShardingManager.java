package pt.fcul.ppc.tfe.multithreading;

import pt.fcul.ppc.tfe.features.api.Feature;
import pt.fcul.ppc.tfe.transaction.Transaction;

import java.util.HashMap;

public class ThreadShardingManager {
    private HashMap<Feature.FeaturePrimaryKey, ThreadShardPool> pools;

    public ThreadShardingManager(int availableCores) throws Exception {
        int numberOfPrimaryKeys = Feature.FeaturePrimaryKey.values().length;
        if (availableCores < numberOfPrimaryKeys * 2) {
            throw new Exception("MultiThreadManager needs at least " +
                    numberOfPrimaryKeys + " available cores to run");
        }
        initiatePools();
    }

    private void initiatePools() {
        pools = new HashMap<>();
        for (Feature.FeaturePrimaryKey primaryKey : Feature.FeaturePrimaryKey.values()) {
            pools.put(primaryKey, new ThreadShardPool());
        }
    }

    public void execute(Feature feature, Transaction transaction) {
        pools.get(feature.getPrimaryKey()).execute(feature, transaction);
    }

    public void shutdown() {
        pools.forEach(((primaryKey, threadShardPool) -> {
            System.out.println("Shutting down " + primaryKey + " pool...");
            threadShardPool.shutdown();
        }));
    }
}
