package pt.fcul.ppc.tfe.features.api;

import pt.fcul.ppc.tfe.multithreading.MultiThreadManager;
import pt.fcul.ppc.tfe.transaction.BufferedTransactionCsvReader;
import pt.fcul.ppc.tfe.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class FeatureService {
    private final MultiThreadManager multiThreadManager;

    public FeatureService(MultiThreadManager multiThreadManager) {
        this.multiThreadManager = multiThreadManager;
    }

    public ArrayList<Transaction> run(
            List<Feature> features,
            BufferedTransactionCsvReader bufferedTransactionCsvReader) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        Transaction transaction;
        while ((transaction = bufferedTransactionCsvReader.getNextTransaction()) != null) {
            for (Feature feature : features) {
                if (multiThreadManager != null) {
                    multiThreadManager.execute(feature, transaction);
                } else {
                    System.out.println("Running " + feature.getClass().getSimpleName());
                    feature.run(transaction);
                }
            }
            transactions.add(transaction);
        }

        if (multiThreadManager != null) {
            multiThreadManager.shutdown();
        }

        return transactions;
    }

}
