package pt.fcul.ppc.tfe.transaction;

public class Transaction {
    private final int time;
    private final int year;
    private final int month;
    private final int day;
    private final int amount;
    private final int buyer;
    private final int seller;
    private final int creditCard;
    private final Position position;
    private float currentSellerAverage;
    private float currentBuyerAverage;
    private float currentSellerLastMonthAverage;
    private float currentSellerLastNAverage;
    private double currentSellerAveragePlus;
    private double currentDistanceLastPos;
    private int currentCreditCardTotal;
    private float currentBuyerMonthlyAverage;

    public Transaction(int time, int year, int month, int day, int amount, int buyer,
                       int seller, int creditCard, Position position) {
        this.time = time;
        this.year = year;
        this.month = month;
        this.day = day;
        this.amount = amount;
        this.buyer = buyer;
        this.seller = seller;
        this.creditCard = creditCard;
        this.position = position;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getAmount() {
        return amount;
    }

    public int getBuyer() {
        return buyer;
    }

    public int getSeller() {
        return seller;
    }

    public int getCreditCard() {
        return creditCard;
    }

    public Position getPosition() {
        return position;
    }

    public void setCurrentSellerAverage(float currentSellerAverage) {
        this.currentSellerAverage = currentSellerAverage;
    }

    public void setCurrentBuyerAverage(float currentBuyerAverage) {
        this.currentBuyerAverage = currentBuyerAverage;
    }

    public void setCurrentSellerLastMonthAverage(float currentSellerLastMonthAverage) {
        this.currentSellerLastMonthAverage = currentSellerLastMonthAverage;
    }

    public void setCurrentSellerLastNAverage(float currentSellerLastNAverage) {
        this.currentSellerLastNAverage = currentSellerLastNAverage;
    }

    public void setCurrentSellerAveragePlus(double currentSellerAveragePlus) {
        this.currentSellerAveragePlus = currentSellerAveragePlus;
    }

    public void setCurrentDistanceLastPos(double currentDistanceLastPos) {
        this.currentDistanceLastPos = currentDistanceLastPos;
    }

    public void setCurrentCreditCardTotal(int currentCreditCardTotal) {
        this.currentCreditCardTotal = currentCreditCardTotal;
    }

    public void setCurrentBuyerMonthlyAverage(float currentBuyerMonthlyAverage) {
        this.currentBuyerMonthlyAverage = currentBuyerMonthlyAverage;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "time=" + time +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", amount=" + amount +
                ", buyer=" + buyer +
                ", seller=" + seller +
                ", creditCard=" + creditCard +
                ", position=" + position +
                ", currentSellerAverage=" + currentSellerAverage +
                ", currentBuyerAverage=" + currentBuyerAverage +
                ", currentSellerLastMonthAverage=" + currentSellerLastMonthAverage +
                ", currentSellerLastNAverage=" + currentSellerLastNAverage +
                ", currentSellerAveragePlus=" + currentSellerAveragePlus +
                ", currentDistanceLastPos=" + currentDistanceLastPos +
                ", currentCreditCardTotal=" + currentCreditCardTotal +
                ", currentBuyerMonthlyAverage=" + currentBuyerMonthlyAverage +
                '}';
    }

    public static String getHeader() {
        return "time,year,month,day,amount,buyer,seller,creditCard,latitude,longitude," +
                "currentSellerAverage,currentBuyerAverage,currentSellerLastMonthAverage," +
                "currentSellerLastNAverage,currentSellerAveragePlus,currentDistanceLastPos," +
                "currentCreditCardTotal,currentBuyerMonthlyAverage";
    }

    public String toCsv() {
        return time + "," +
                year + "," +
                month + "," +
                day + "," +
                amount + "," +
                buyer + "," +
                seller + "," +
                creditCard + "," +
                position.getLatitude() + "," +
                position.getLongitude() + "," +
                currentSellerAverage + "," +
                currentBuyerAverage + "," +
                currentSellerLastMonthAverage + "," +
                currentSellerLastNAverage + "," +
                currentSellerAveragePlus + "," +
                currentDistanceLastPos + "," +
                currentCreditCardTotal + "," +
                currentBuyerMonthlyAverage;
    }
}
