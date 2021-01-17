package pt.fcul.ppc.tfe.features;

import pt.fcul.ppc.tfe.Cache;
import pt.fcul.ppc.tfe.features.api.Feature;
import pt.fcul.ppc.tfe.transaction.Transaction;

public class TotalPurchasesCCFeature implements Feature {
    private final Cache cache;

    public TotalPurchasesCCFeature(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run(Transaction transaction) {
        int creditCardId = transaction.getCreditCard();

        Integer currentTotal = 0;
        String key = "creditCard/" + creditCardId;
        if (cache.contains(key)) {
            currentTotal = (Integer) cache.get(key);
            transaction.setCurrentCreditCardTotal(currentTotal);

            currentTotal += 1;
            cache.put(key, currentTotal);
        } else {
            cache.put(key, 1);
            transaction.setCurrentCreditCardTotal(currentTotal);
        }
        // System.out.println(transaction.toString());

    }

    @Override
    public FeaturePrimaryKey getPrimaryKey() {
        return FeaturePrimaryKey.CREDIT_CARD;
    }
}
