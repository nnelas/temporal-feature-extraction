package pt.fcul.ppc.tfe.features;

import pt.fcul.ppc.tfe.Cache;
import pt.fcul.ppc.tfe.LinkedList;
import pt.fcul.ppc.tfe.transaction.Transaction;

import java.util.ArrayList;

public class AverageSellerLastNFeature implements Feature {
    private static final int TRANSACTIONS_THRESHOLD = 100;
    private final Cache cache;

    public AverageSellerLastNFeature(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run(ArrayList<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            int sellerId = transaction.getSeller();

            LinkedList linkedList;
            String key = "seller/lastN/" + sellerId;
            if (cache.contains(key)) {
                linkedList = (LinkedList) cache.get(key);
                float currentAverage = linkedList.getAverage();
                transaction.setCurrentSellerLastNAverage(currentAverage);
            } else {
                transaction.setCurrentSellerLastNAverage(0f);
                linkedList = new LinkedList(TRANSACTIONS_THRESHOLD);
            }
            linkedList.add(transaction.getAmount());
            cache.put(key, linkedList);

            // System.out.println(transaction.toString());
        }
    }
}
