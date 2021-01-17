package pt.fcul.ppc.tfe.features;

import pt.fcul.ppc.tfe.Cache;
import pt.fcul.ppc.tfe.features.api.Feature;
import pt.fcul.ppc.tfe.features.models.Buyer;
import pt.fcul.ppc.tfe.transaction.Transaction;

public class AverageBuyerAmountFeature implements Feature {
    private final Cache cache;

    public AverageBuyerAmountFeature(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run(Transaction transaction) {
        int buyerId = transaction.getBuyer();

        String key = "buyer/" + buyerId;
        if (cache.contains(key)) {
            Buyer buyer = (Buyer) cache.get(key);
            float currentAverage = buyer.getCurrentAverage();
            transaction.setCurrentBuyerAverage(currentAverage);

            int currentAmount = buyer.getCurrentAmount();
            currentAmount += transaction.getAmount();
            int currentNumTransactions = buyer.getCurrentNumTransactions() + 1;
            cache.put(key, new Buyer(currentNumTransactions, currentAmount));
        } else {
            transaction.setCurrentBuyerAverage(0f);
            cache.put(key, new Buyer(1, transaction.getAmount()));
        }
        // System.out.println(transaction.toString());
    }
}
