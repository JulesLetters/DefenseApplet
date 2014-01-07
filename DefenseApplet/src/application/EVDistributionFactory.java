package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EVDistributionFactory implements IEVDistributionFactory {

	private static final int MAX_EVS = 508;
	private static final int MAX_STAT_EVS = 252;
	private static final int EV_STEP = 4;

	@Override
	public Set<EVDistribution> getInitialCollection() {
		List<Nature> natures = new ArrayList<>();
		natures.add(new Nature(Nature.Adjustment.NEUTRAL,
				Nature.Adjustment.NEUTRAL));

		natures.add(new Nature(Nature.Adjustment.INCREASE,
				Nature.Adjustment.NEUTRAL));
		natures.add(new Nature(Nature.Adjustment.DECREASE,
				Nature.Adjustment.NEUTRAL));
		natures.add(new Nature(Nature.Adjustment.INCREASE,
				Nature.Adjustment.DECREASE));

		natures.add(new Nature(Nature.Adjustment.NEUTRAL,
				Nature.Adjustment.INCREASE));
		natures.add(new Nature(Nature.Adjustment.NEUTRAL,
				Nature.Adjustment.DECREASE));
		natures.add(new Nature(Nature.Adjustment.DECREASE,
				Nature.Adjustment.INCREASE));

		Set<EVDistribution> initialCollection = new HashSet<>();
		for (Nature nature : natures) {
			for (int h = 0; h <= MAX_STAT_EVS; h += EV_STEP) {
				for (int d = 0; d <= MAX_STAT_EVS; d += EV_STEP) {
					for (int s = 0; s <= MAX_STAT_EVS; s += EV_STEP) {
						if (h + d + s <= MAX_EVS) {
							initialCollection.add(new EVDistribution(h, d, s,
									nature));
						}
					}
				}
			}
		}
		return initialCollection;
	}
}
