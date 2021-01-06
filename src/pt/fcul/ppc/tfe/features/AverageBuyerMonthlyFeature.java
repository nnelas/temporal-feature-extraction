package pt.fcul.ppc.tfe.features;

import pt.fcul.ppc.tfe.Cache;
import pt.fcul.ppc.tfe.transaction.Transaction;

import java.util.ArrayList;

public class AverageBuyerMonthlyFeature implements Feature {
    private final Cache cache;

    public AverageBuyerMonthlyFeature(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run(ArrayList<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            int buyerId = transaction.getBuyer();

            String key = "buyer/" + buyerId +
                    "/month/" + transaction.getMonth();
            if (cache.contains(key)) {
                Buyer buyer = (Buyer) cache.get(key);
                float currentAverage = buyer.getCurrentAverage();
                transaction.setCurrentBuyerMonthlyAverage(currentAverage);

                int currentAmount = buyer.getCurrentAmount();
                currentAmount += transaction.getAmount();
                int currentNumTransactions = buyer.getCurrentNumTransactions() + 1;
                cache.put(key, new Buyer(currentNumTransactions, currentAmount));
            } else {
                transaction.setCurrentBuyerMonthlyAverage(0f);
                cache.put(key, new Buyer(1, transaction.getAmount()));
            }
            // System.out.println(transaction.toString());
        }
    }
}
