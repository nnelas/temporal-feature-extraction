package pt.fcul.ppc.tfe.features.api;


import pt.fcul.ppc.tfe.transaction.Transaction;

public interface Feature {
    void run(Transaction transactions);

    FeaturePrimaryKey getPrimaryKey();

    enum FeaturePrimaryKey {
        SELLER, BUYER, CREDIT_CARD
    }
}

