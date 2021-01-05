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
    private final int latitude;
    private final int longitude;
    private float currentSellerAverage;
    private float currentBuyerAverage;

    public Transaction(int time, int year, int month, int day, int amount, int buyer,
                       int seller, int creditCard, int latitude, int longitude) {
        this.time = time;
        this.year = year;
        this.month = month;
        this.day = day;
        this.amount = amount;
        this.buyer = buyer;
        this.seller = seller;
        this.creditCard = creditCard;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getTime() {
        return time;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
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

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setCurrentSellerAverage(float currentSellerAverage) {
        this.currentSellerAverage = currentSellerAverage;
    }

    public void setCurrentBuyerAverage(float currentBuyerAverage) {
        this.currentBuyerAverage = currentBuyerAverage;
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
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", currentSellerAverage=" + currentSellerAverage +
                ", currentBuyerAverage=" + currentBuyerAverage +
                '}';
    }

    public static String getHeader() {
        return "time,year,month,day,amount,buyer,seller,creditCard,latitude,longitude," +
                "currentSellerAverage,currentBuyerAverage";
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
                latitude + "," +
                longitude + "," +
                currentSellerAverage + "," +
                currentBuyerAverage;
    }
}
