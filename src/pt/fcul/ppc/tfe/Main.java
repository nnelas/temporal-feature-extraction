package pt.fcul.ppc.tfe;

import pt.fcul.ppc.tfe.features.Feature;
import pt.fcul.ppc.tfe.features.FeatureFactory;
import pt.fcul.ppc.tfe.features.FeatureService;
import pt.fcul.ppc.tfe.transaction.Transaction;
import pt.fcul.ppc.tfe.transaction.TransactionCsvReader;
import pt.fcul.ppc.tfe.transaction.TransactionMapper;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = args[0];
        System.out.println("Using CSV file: " + filePath);

        TransactionCsvReader transactionCsvReader =
                new TransactionCsvReader(new TransactionMapper());
        ArrayList<Transaction> transactions = transactionCsvReader.read(filePath);

        List<Feature> features = FeatureFactory.create();
        FeatureService featureService = new FeatureService();
        featureService.run(features, transactions);
    }
}
