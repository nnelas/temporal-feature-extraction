package pt.fcul.ppc.tfe;

import pt.fcul.ppc.tfe.features.api.Feature;
import pt.fcul.ppc.tfe.features.api.FeatureFactory;
import pt.fcul.ppc.tfe.features.api.FeatureService;
import pt.fcul.ppc.tfe.multithreading.MultiThreadManager;
import pt.fcul.ppc.tfe.transaction.Transaction;
import pt.fcul.ppc.tfe.transaction.BufferedTransactionCsvReader;
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

        // TODO: think of buffered read, for each line, run features
        // TODO: increase CSV to run in 1/2 min seq - DONE, current 2:20
        // sharding , futures ,
        // order in write is not important

        // perhaps number of features can increase (and see if improves)
        BufferedTransactionCsvReader bufferedTransactionCsvReader =
                new BufferedTransactionCsvReader(new TransactionMapper(), inputFilePath);

        MultiThreadManager multiThreadManager = new MultiThreadManager(NUMBER_OF_THREADS);

        List<Feature> features = FeatureFactory.create();
        FeatureService featureService = new FeatureService(multiThreadManager);
        ArrayList<Transaction> transactions = featureService.run(features,
                bufferedTransactionCsvReader);

        System.out.println("Writing to CSV file: " + outputFilePath);
        TransactionCsvWriter transactionCsvWriter = new TransactionCsvWriter();
        transactionCsvWriter.write(transactions, outputFilePath);
    }
}
