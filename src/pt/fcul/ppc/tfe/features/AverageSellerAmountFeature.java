package pt.fcul.ppc.tfe.features;

import pt.fcul.ppc.tfe.Cache;
import pt.fcul.ppc.tfe.transaction.Transaction;

import java.util.ArrayList;

public class AverageSellerAmountFeature implements Feature {
    private final Cache cache;

    public AverageSellerAmountFeature(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run(ArrayList<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            int seller = transaction.getSeller();

            Integer currentAmount = 0;
            String key = "seller/" + seller;
            if (cache.contains(key)) {
                currentAmount = (Integer) cache.get(key);
                transaction.setCurrentSellerAmount(currentAmount);
                currentAmount += transaction.getAmount();
                cache.put(key, currentAmount);
            } else {
                cache.put(key, transaction.getAmount());
                transaction.setCurrentSellerAmount(currentAmount);
            }
            System.out.println(transaction.toString());
            System.out.println("Inside cache: " + cache.get(key));
        }
    }
}
