package pt.fcul.ppc.tfe.features;


import pt.fcul.ppc.tfe.transaction.Transaction;

import java.util.ArrayList;

@FunctionalInterface
public interface Feature {
    void run(ArrayList<Transaction> transactions);
}

