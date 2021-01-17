package pt.fcul.ppc.tfe.multithreading;

import pt.fcul.ppc.tfe.features.api.Feature;
import pt.fcul.ppc.tfe.transaction.Transaction;

public class MultiThreadManager {
    private final ThreadShardingManager threadShardingManager;

    public MultiThreadManager(ThreadShardingManager threadShardingManager) {
        this.threadShardingManager = threadShardingManager;
    }


    public void execute(Feature feature, Transaction transaction) {
        threadShardingManager.execute(feature, transaction);
    }

    public void shutdown() {
        threadShardingManager.shutdown();
    }
}
