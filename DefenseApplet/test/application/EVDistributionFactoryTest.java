package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class EVDistributionFactoryTest {

	private static final int MAX_EVS = 510;
	private static final int MAX_STAT_EVS = 252;
	private static final int EV_STEP = 4;
	private EVDistributionFactory evDistributionFactory;

	@Before
	public void setUp() throws Exception {
		evDistributionFactory = new EVDistributionFactory();
	}

	@Test
	public void testInitialCollectionContainsAllOnlyHPDistributions() {
		Set<EVDistribution> initialCollection = evDistributionFactory
				.getInitialCollection();

		for (int h = 0; h <= MAX_STAT_EVS; h += EV_STEP) {
			assertTrue(initialCollection.contains(new EVDistribution(h, 0, 0)));
		}

	}

	@Test
	public void testInitialCollectionContainsAllOnlyDefDistributions() {
		Set<EVDistribution> initialCollection = evDistributionFactory
				.getInitialCollection();

		for (int d = 0; d <= MAX_STAT_EVS; d += EV_STEP) {
			assertTrue(initialCollection.contains(new EVDistribution(0, d, 0)));
		}

	}

	@Test
	public void testInitialCollectionContainsAllOnlySpDefDistributions() {
		Set<EVDistribution> initialCollection = evDistributionFactory
				.getInitialCollection();

		for (int s = 0; s <= MAX_STAT_EVS; s += EV_STEP) {
			assertTrue(initialCollection.contains(new EVDistribution(0, 0, s)));
		}

	}

	@Test
	public void testGetInitialCollectionGeneratesAllPossibleDistributions() {
		Set<EVDistribution> initialCollection = evDistributionFactory
				.getInitialCollection();

		int counter = 0;
		for (int h = 0; h <= MAX_STAT_EVS; h += EV_STEP) {
			for (int d = 0; d <= MAX_STAT_EVS; d += EV_STEP) {
				for (int s = 0; s <= MAX_STAT_EVS; s += EV_STEP) {
					EVDistribution expectedDistro = new EVDistribution(h, d, s);
					if (h + d + s <= MAX_EVS) {
						assertTrue(initialCollection.contains(expectedDistro));
						++counter;
					} else {
						assertFalse(initialCollection.contains(expectedDistro));
					}
				}
			}
		}
		assertEquals(counter, initialCollection.size());
	}

}
