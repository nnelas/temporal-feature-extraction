package pt.fcul.ppc.tfe.features.api;

import pt.fcul.ppc.tfe.Cache;
import pt.fcul.ppc.tfe.features.*;

import java.util.ArrayList;
import java.util.List;

public class FeatureFactory {
    public static List<Feature> create() {
        List<Feature> features = new ArrayList<>();
        Cache cache = new Cache();
        AverageBuyerAmountFeature averageBuyerAmountFeature = new AverageBuyerAmountFeature(cache);
        features.add(averageBuyerAmountFeature);

        AverageSellerAmountFeature averageSellerAmountFeature = new AverageSellerAmountFeature(cache);
        features.add(averageSellerAmountFeature);

        AverageSellerLastMonthFeature averageSellerLastMonthFeature
                = new AverageSellerLastMonthFeature(cache);
        features.add(averageSellerLastMonthFeature);

        AverageSellerLastNFeature averageSellerLastNFeature = new AverageSellerLastNFeature(cache);
        features.add(averageSellerLastNFeature);

        AverageSellerAmountPlusFeature averageSellerAmountPlusFeature
                = new AverageSellerAmountPlusFeature(cache);
        features.add(averageSellerAmountPlusFeature);

        DistanceLastPosFeature distanceLastPosFeature = new DistanceLastPosFeature(cache);
        features.add(distanceLastPosFeature);

        TotalPurchasesCCFeature totalPurchasesCCFeature = new TotalPurchasesCCFeature(cache);
        features.add(totalPurchasesCCFeature);

        AverageBuyerMonthlyFeature averageBuyerMonthlyFeature = new AverageBuyerMonthlyFeature(cache);
        features.add(averageBuyerMonthlyFeature);
        return features;
    }
}
