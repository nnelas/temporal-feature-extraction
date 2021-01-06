package pt.fcul.ppc.tfe.features;

import pt.fcul.ppc.tfe.Cache;
import pt.fcul.ppc.tfe.transaction.Position;
import pt.fcul.ppc.tfe.transaction.Transaction;

import java.util.ArrayList;

public class DistanceLastPosFeature implements Feature {
    private final Cache cache;

    public DistanceLastPosFeature(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run(ArrayList<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            int buyerId = transaction.getBuyer();

            double distance = 0;
            String key = "buyer/lastPosition/" + buyerId;
            if (cache.contains(key)) {
                Position lastPosition = (Position) cache.get(key);
                distance = calculateDistance(transaction.getPosition(), lastPosition);

            }
            transaction.setCurrentDistanceLastPos(distance);
            cache.put(key, transaction.getPosition());

            // System.out.println(transaction);
        }
    }

    private double calculateDistance(Position current, Position lastPosition) {
        int latitude = Math.abs(current.getLatitude() - lastPosition.getLatitude());
        int longitude = Math.abs(current.getLongitude() - lastPosition.getLongitude());
        return Math.sqrt((latitude * latitude) + (longitude * longitude));
    }
}