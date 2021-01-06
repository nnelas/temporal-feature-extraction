package pt.fcul.ppc.tfe.features;

import pt.fcul.ppc.tfe.Cache;
import pt.fcul.ppc.tfe.transaction.Transaction;

import java.util.ArrayList;

public class AverageSellerLastMonthFeature implements Feature {
    private final Cache cache;

    public AverageSellerLastMonthFeature(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run(ArrayList<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            int sellerId = transaction.getSeller();

            String key = "seller/" + sellerId +
                    "/year/" + transaction.getYear() +
                    "/month/" + transaction.getMonth();
            if (cache.contains(key)) {
                Seller seller = (Seller) cache.get(key);
                float currentAverage = seller.getCurrentAverage();
                transaction.setCurrentSellerLastMonthAverage(currentAverage);

                int currentAmount = seller.getCurrentAmount();
                currentAmount += transaction.getAmount();
                int currentNumTransactions = seller.getCurrentNumTransactions() + 1;
                cache.put(key, new Seller(currentNumTransactions, currentAmount));
            } else {
                transaction.setCurrentSellerLastMonthAverage(0f);
                cache.put(key, new Seller(1, transaction.getAmount()));
            }
            // System.out.println(transaction.toString());
        }
    }
}
