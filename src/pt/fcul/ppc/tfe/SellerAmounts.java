package pt.fcul.ppc.tfe;

import java.util.ArrayList;

public class SellerAmounts {
    private final ArrayList<Integer> sellerAmounts;

    public SellerAmounts() {
        this.sellerAmounts = new ArrayList<>();
    }

    public void add(int value) {
        sellerAmounts.add(value);
    }

    public float getAverage() {
        int sum = 0;
        for (int amount : sellerAmounts) {
            sum += amount;
        }
        return (float) sum / sellerAmounts.size();
    }

    public double getStandardDeviation() {
        float average = getAverage();
        double temp = 0;
        for (int val : sellerAmounts) {
            temp += Math.pow(val - average, 2);
        }
        return Math.sqrt(temp / (double) (sellerAmounts.size()));
    }
}
