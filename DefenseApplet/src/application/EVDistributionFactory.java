package application;

import java.util.HashSet;
import java.util.Set;

public class EVDistributionFactory implements IEVDistributionFactory {

	@Override
	public Set<EVDistribution> getInitialCollection() {
		Set<EVDistribution> initialCollection = new HashSet<>();
		for (int h = 0; h <= 252; h += 4) {
			for (int d = 0; d <= 252; d += 4) {
				for (int s = 0; s <= 252; s += 4) {
					initialCollection.add(new EVDistribution(h, d, s));
				}
			}
		}
		return initialCollection;
	}
}
