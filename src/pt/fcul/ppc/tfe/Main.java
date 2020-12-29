package pt.fcul.ppc.tfe;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String filePath = args[0];
        System.out.println("Using CSV file: " + filePath);

        TransactionCsvReader transactionCsvReader =
                new TransactionCsvReader(new TransactionMapper());
        ArrayList<Transaction> transactions = transactionCsvReader.read(filePath);
        Cache cache = new Cache();

        setCurrentSellerTransactions(transactions, cache);
        setCurrentBuyerTransactions(transactions, cache);
    }

    private static void setCurrentSellerTransactions(ArrayList<Transaction> transactions,
                                                     Cache cache) {
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

    private static void setCurrentBuyerTransactions(ArrayList<Transaction> transactions, Cache cache) {
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
