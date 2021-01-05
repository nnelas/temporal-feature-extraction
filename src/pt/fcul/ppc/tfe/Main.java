package pt.fcul.ppc.tfe;

import pt.fcul.ppc.tfe.features.Feature;
import pt.fcul.ppc.tfe.features.FeatureFactory;
import pt.fcul.ppc.tfe.features.FeatureService;
import pt.fcul.ppc.tfe.transaction.Transaction;
import pt.fcul.ppc.tfe.transaction.TransactionCsvReader;
import pt.fcul.ppc.tfe.transaction.TransactionCsvWriter;
import pt.fcul.ppc.tfe.transaction.TransactionMapper;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = args[0];
        String outputFilePath = args[1];
        System.out.println("Using CSV file: " + inputFilePath);

        TransactionCsvReader transactionCsvReader =
                new TransactionCsvReader(new TransactionMapper());
        ArrayList<Transaction> transactions = transactionCsvReader.read(inputFilePath);

        List<Feature> features = FeatureFactory.create();
        FeatureService featureService = new FeatureService();
        featureService.run(features, transactions);

        System.out.println("Writing to CSV file: " + outputFilePath);
        TransactionCsvWriter transactionCsvWriter = new TransactionCsvWriter();
        transactionCsvWriter.write(transactions, outputFilePath);
    }
}
