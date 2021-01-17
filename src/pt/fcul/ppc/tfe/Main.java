package pt.fcul.ppc.tfe;

import pt.fcul.ppc.tfe.features.api.Feature;
import pt.fcul.ppc.tfe.features.api.FeatureFactory;
import pt.fcul.ppc.tfe.features.api.FeatureService;
import pt.fcul.ppc.tfe.multithreading.MultiThreadManager;
import pt.fcul.ppc.tfe.multithreading.ThreadShardingManager;
import pt.fcul.ppc.tfe.transaction.BufferedTransactionCsvReader;
import pt.fcul.ppc.tfe.transaction.Transaction;
import pt.fcul.ppc.tfe.transaction.TransactionCsvWriter;
import pt.fcul.ppc.tfe.transaction.TransactionMapper;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        String inputFilePath = args[0];
        String outputFilePath = args[1];
        System.out.println("Using CSV file: " + inputFilePath);

        // order in write is not important
        // perhaps number of features can increase (and see if improves)
        BufferedTransactionCsvReader bufferedTransactionCsvReader =
                new BufferedTransactionCsvReader(new TransactionMapper(), inputFilePath);

        ThreadShardingManager threadShardingManager;
        try {
            threadShardingManager = new ThreadShardingManager(NUMBER_OF_THREADS);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        MultiThreadManager multiThreadManager = new MultiThreadManager(threadShardingManager);
        FeatureService featureService = new FeatureService(multiThreadManager);

        List<Feature> features = FeatureFactory.create();
        ArrayList<Transaction> transactions = featureService.run(features,
                bufferedTransactionCsvReader);

        System.out.println("Writing to CSV file: " + outputFilePath);
        TransactionCsvWriter transactionCsvWriter = new TransactionCsvWriter();
        transactionCsvWriter.write(transactions, outputFilePath);
    }
}
