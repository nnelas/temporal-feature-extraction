package pt.fcul.ppc.tfe.features;

import pt.fcul.ppc.tfe.Cache;
import pt.fcul.ppc.tfe.features.api.Feature;
import pt.fcul.ppc.tfe.transaction.Position;
import pt.fcul.ppc.tfe.transaction.Transaction;

public class DistanceLastPosFeature implements Feature {
    private final Cache cache;

    public DistanceLastPosFeature(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run(Transaction transaction) {
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

    @Override
    public FeaturePrimaryKey getPrimaryKey() {
        return FeaturePrimaryKey.BUYER;
    }

    private double calculateDistance(Position current, Position lastPosition) {
        int latitude = Math.abs(current.getLatitude() - lastPosition.getLatitude());
        int longitude = Math.abs(current.getLongitude() - lastPosition.getLongitude());
        return Math.sqrt((latitude * latitude) + (longitude * longitude));
    }
}
