package pt.fcul.ppc.tfe.features;

import pt.fcul.ppc.tfe.Cache;
import pt.fcul.ppc.tfe.transaction.Transaction;

import java.util.ArrayList;

public class AverageBuyerAmountFeature implements Feature {
    private final Cache cache;

    public AverageBuyerAmountFeature(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run(ArrayList<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            int buyer = transaction.getBuyer();

            Integer currentAmount = 0;
            String key = "buyer/" + buyer;
            if (cache.contains(key)) {
                currentAmount = (Integer) cache.get(key);
                transaction.setCurrentBuyerAmount(currentAmount);
                currentAmount += transaction.getAmount();
                cache.put(key, currentAmount);
            } else {
                cache.put(key, transaction.getAmount());
                transaction.setCurrentBuyerAmount(currentAmount);
            }
            System.out.println(transaction.toString());
            System.out.println("Inside cache: " + cache.get(key));
        }
    }
}
