package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class EVDistributionFactoryTest {

	private static final int MAX_EVS = 510;
	private static final int MAX_STAT_EVS = 252;
	private static final int EV_STEP = 4;
	private EVDistributionFactory evDistributionFactory;
	private Nature neutralNature = new Nature(Nature.Adjustment.NEUTRAL,
			Nature.Adjustment.NEUTRAL);

	@Before
	public void setUp() throws Exception {
		evDistributionFactory = new EVDistributionFactory();
	}

	@Test
	public void testInitialCollectionContainsAllOnlyHPDistributions() {
		Set<EVDistribution> initialCollection = evDistributionFactory
				.getInitialCollection();

		for (int h = 0; h <= MAX_STAT_EVS; h += EV_STEP) {
			assertTrue(initialCollection.contains(new EVDistribution(h, 0, 0,
					neutralNature)));
		}

	}

	@Test
	public void testInitialCollectionContainsAllOnlyDefDistributions() {
		Set<EVDistribution> initialCollection = evDistributionFactory
				.getInitialCollection();

		for (int d = 0; d <= MAX_STAT_EVS; d += EV_STEP) {
			assertTrue(initialCollection.contains(new EVDistribution(0, d, 0,
					neutralNature)));
		}

	}

	@Test
	public void testInitialCollectionContainsAllOnlySpDefDistributions() {
		Set<EVDistribution> initialCollection = evDistributionFactory
				.getInitialCollection();

		for (int s = 0; s <= MAX_STAT_EVS; s += EV_STEP) {
			assertTrue(initialCollection.contains(new EVDistribution(0, 0, s,
					neutralNature)));
		}

	}

	@Test
	public void testGetInitialCollectionGeneratesAllPossibleDistributions() {
		Set<EVDistribution> initialCollection = evDistributionFactory
				.getInitialCollection();

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

		int counter = 0;
		for (Nature nature : natures) {

			for (int h = 0; h <= MAX_STAT_EVS; h += EV_STEP) {
				for (int d = 0; d <= MAX_STAT_EVS; d += EV_STEP) {
					for (int s = 0; s <= MAX_STAT_EVS; s += EV_STEP) {
						EVDistribution expectedDistro = new EVDistribution(h,
								d, s, nature);
						if (h + d + s <= MAX_EVS) {
							assertTrue(initialCollection
									.contains(expectedDistro));
							++counter;
						} else {
							assertFalse(initialCollection
									.contains(expectedDistro));
						}
					}
				}
			}
		}
		assertEquals(counter, initialCollection.size());
	}

}
