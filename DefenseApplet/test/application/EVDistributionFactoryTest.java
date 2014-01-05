package application;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class EVDistributionFactoryTest {

	private EVDistributionFactory evDistributionFactory;

	@Before
	public void setUp() throws Exception {
		evDistributionFactory = new EVDistributionFactory();
	}

	@Test
	public void testGetInitialCollectionGeneratesRandomDistribution() {
		Set<EVDistribution> initialCollection = evDistributionFactory
				.getInitialCollection();

		for (int h = 0; h < 4; h += 4) {
			for (int d = 0; d < 4; d += 4) {
				for (int s = 0; s < 4; s += 4) {
					initialCollection.contains(new EVDistribution(h, d, s));
				}
			}
		}
	}

}
