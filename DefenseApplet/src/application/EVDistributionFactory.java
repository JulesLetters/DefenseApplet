package application;

import java.util.HashSet;
import java.util.Set;

import restrictions.IRestrictionsModel;

public class EVDistributionFactory implements IEVDistributionFactory {

	private static final int MAX_STAT_EVS = 252;
	private static final int EV_STEP = 4;

	@Override
	public Set<EVDistribution> getInitialCollection(IRestrictionsModel restrictionsModel) {
		Set<EVDistribution> initialCollection = new HashSet<>();
		int maxEVs = restrictionsModel.getMaxEVs();
		int minEVs = restrictionsModel.getMinEVs();
		for (Nature nature : restrictionsModel.getAllowedNatures()) {
			for (int h = 0; h <= MAX_STAT_EVS; h += EV_STEP) {
				for (int d = 0; d <= MAX_STAT_EVS; d += EV_STEP) {
					for (int s = 0; s <= MAX_STAT_EVS; s += EV_STEP) {
						int sum = h + d + s;
						if (sum >= minEVs && sum <= maxEVs) {
							initialCollection.add(new EVDistribution(h, d, s, nature));
						}
					}
				}
			}
		}
		return initialCollection;
	}
}
