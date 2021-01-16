package pt.fcul.ppc.tfe.features.api;


import pt.fcul.ppc.tfe.transaction.Transaction;

@FunctionalInterface
public interface Feature {
    void run(Transaction transactions);
}

