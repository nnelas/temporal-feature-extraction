package pt.fcul.ppc.tfe.features;

import pt.fcul.ppc.tfe.Cache;
import pt.fcul.ppc.tfe.CircularLinkedList;
import pt.fcul.ppc.tfe.features.api.Feature;
import pt.fcul.ppc.tfe.transaction.Transaction;

public class AverageSellerLastNFeature implements Feature {
    private static final int TRANSACTIONS_THRESHOLD = 100;
    private final Cache cache;

    public AverageSellerLastNFeature(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run(Transaction transaction) {
        int sellerId = transaction.getSeller();

        CircularLinkedList circularLinkedList;
        String key = "seller/lastN/" + sellerId;
        if (cache.contains(key)) {
            circularLinkedList = (CircularLinkedList) cache.get(key);
            float currentAverage = circularLinkedList.getAverage();
            transaction.setCurrentSellerLastNAverage(currentAverage);
        } else {
            transaction.setCurrentSellerLastNAverage(0f);
            circularLinkedList = new CircularLinkedList(TRANSACTIONS_THRESHOLD);
        }
        circularLinkedList.add(transaction.getAmount());
        cache.put(key, circularLinkedList);

        // System.out.println(transaction.toString());
    }
}
