package pt.fcul.ppc.tfe.features;

import pt.fcul.ppc.tfe.Cache;

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
        return features;
    }
}
