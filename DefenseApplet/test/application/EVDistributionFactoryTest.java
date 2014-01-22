package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import restrictions.IRestrictionsModel;

public class EVDistributionFactoryTest {

	@Mock
	private IRestrictionsModel restrictionsModel;

	private static final int MAX_EVS = 508;
	private static final int MAX_STAT_EVS = 252;
	private static final int EV_STEP = 4;
	private Nature neutralNature = Nature.Possibility.NEUTRAL.getNature();
	private EVDistributionFactory evDistributionFactory;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		evDistributionFactory = new EVDistributionFactory();
		when(restrictionsModel.getAllowedNatures()).thenReturn(Collections.singleton(neutralNature));
		when(restrictionsModel.getMaxEVs()).thenReturn(MAX_EVS);
		when(restrictionsModel.getMinEVs()).thenReturn(0);
	}

	@Test
	public void testInitialCollectionContainsAllOnlyHPDistributions() {
		Set<EVDistribution> initialCollection = evDistributionFactory.getInitialCollection(restrictionsModel);

		for (int h = 0; h <= MAX_STAT_EVS; h += EV_STEP) {
			assertTrue(initialCollection.contains(new EVDistribution(h, 0, 0, neutralNature)));
		}

	}

	@Test
	public void testInitialCollectionContainsAllOnlyDefDistributions() {
		Set<EVDistribution> initialCollection = evDistributionFactory.getInitialCollection(restrictionsModel);

		for (int d = 0; d <= MAX_STAT_EVS; d += EV_STEP) {
			assertTrue(initialCollection.contains(new EVDistribution(0, d, 0, neutralNature)));
		}

	}

	@Test
	public void testInitialCollectionContainsAllOnlySpDefDistributions() {
		Set<EVDistribution> initialCollection = evDistributionFactory.getInitialCollection(restrictionsModel);

		for (int s = 0; s <= MAX_STAT_EVS; s += EV_STEP) {
			assertTrue(initialCollection.contains(new EVDistribution(0, 0, s, neutralNature)));
		}

	}

	@Test
	public void testGenerateOnlyDistributionsWithNaturesFromRestrictions() {
		Set<Nature> natures = new HashSet<>();
		natures.add(neutralNature);
		natures.add(Nature.Possibility.INC_DEF.getNature());
		natures.add(Nature.Possibility.INC_SPDEF.getNature());

		when(restrictionsModel.getAllowedNatures()).thenReturn(natures);

		Set<EVDistribution> initialCollection = evDistributionFactory.getInitialCollection(restrictionsModel);

		int counter = 0;
		for (Nature nature : natures) {
			for (int h = 0; h <= MAX_STAT_EVS; h += EV_STEP) {
				for (int d = 0; d <= MAX_STAT_EVS; d += EV_STEP) {
					for (int s = 0; s <= MAX_STAT_EVS; s += EV_STEP) {
						EVDistribution expectedDistro = new EVDistribution(h, d, s, nature);
						if (h + d + s <= MAX_EVS) {
							assertTrue(initialCollection.contains(expectedDistro));
							++counter;
						} else {
							assertFalse(initialCollection.contains(expectedDistro));
						}
					}
				}
			}
		}
		assertEquals(counter, initialCollection.size());
	}

	@Test
	public void testGenerateOnlyDistributionsEVRangeFromRestrictions() {
		int maxEVs = 200;
		int minEVs = 80;
		when(restrictionsModel.getMaxEVs()).thenReturn(maxEVs);
		when(restrictionsModel.getMinEVs()).thenReturn(minEVs);

		Set<EVDistribution> initialCollection = evDistributionFactory.getInitialCollection(restrictionsModel);

		int counter = 0;
		for (int h = 0; h <= maxEVs; h += EV_STEP) {
			for (int d = 0; d <= maxEVs; d += EV_STEP) {
				for (int s = 0; s <= maxEVs; s += EV_STEP) {
					EVDistribution expectedDistro = new EVDistribution(h, d, s, neutralNature);
					int sum = h + d + s;
					if (sum >= minEVs && sum <= maxEVs) {
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
