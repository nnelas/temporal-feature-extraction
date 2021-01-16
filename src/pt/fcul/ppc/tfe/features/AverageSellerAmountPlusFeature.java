package pt.fcul.ppc.tfe.features;

import pt.fcul.ppc.tfe.Cache;
import pt.fcul.ppc.tfe.SellerAmounts;
import pt.fcul.ppc.tfe.features.api.Feature;
import pt.fcul.ppc.tfe.transaction.Transaction;

public class AverageSellerAmountPlusFeature implements Feature {
    private final Cache cache;

    public AverageSellerAmountPlusFeature(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run(Transaction transaction) {
        int sellerId = transaction.getSeller();

        SellerAmounts sellerAmounts;
        String key = "seller/amountPlus/" + sellerId;
        if (cache.contains(key)) {
            sellerAmounts = (SellerAmounts) cache.get(key);
            float currentAverage = sellerAmounts.getAverage();
            double standardDeviation = sellerAmounts.getStandardDeviation();
            transaction.setCurrentSellerAveragePlus(
                    currentAverage + (3 * standardDeviation));
        } else {
            transaction.setCurrentSellerAveragePlus(0);
            sellerAmounts = new SellerAmounts();
        }
        sellerAmounts.add(transaction.getAmount());
        cache.put(key, sellerAmounts);

        // System.out.println(transaction.toString());

    }
}
