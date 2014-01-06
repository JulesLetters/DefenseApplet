package application;

import static org.junit.Assert.assertEquals;

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

		int counter = 0;
		for (int h = 0; h <= 252; h += 4) {
			for (int d = 0; d <= 252; d += 4) {
				for (int s = 0; s <= 252; s += 4) {
					initialCollection.contains(new EVDistribution(h, d, s));
					++counter;
				}
			}
		}
		assertEquals(counter, initialCollection.size());
	}

}
