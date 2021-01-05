package pt.fcul.ppc.tfe.features;

public class Buyer {
    private final int currentNumTransactions;
    private final int currentAmount;

    public Buyer(int currentNumTransactions, int currentAmount) {
        this.currentNumTransactions = currentNumTransactions;
        this.currentAmount = currentAmount;
    }

    public int getCurrentNumTransactions() {
        return currentNumTransactions;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public float getCurrentAverage() {
        return (float) currentAmount / currentNumTransactions;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "currentNumTransactions=" + currentNumTransactions +
                ", currentAmount=" + currentAmount +
                '}';
    }
}
