package pt.fcul.ppc.tfe.features;

import pt.fcul.ppc.tfe.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class FeatureService {
    public void run(List<Feature> features, ArrayList<Transaction> transactions) {
        for (Feature feature: features) {
            feature.run(transactions);
        }
    }
}
