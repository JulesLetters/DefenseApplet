package application;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DistributionFinderTest {

	@Mock
	private IEVDistributionFactory evDistributionFactory;
	@Mock
	private IPokemonStatsCollectionFactory pokemonStatsCollectionFactory;
	@Mock
	private IBaseStats baseStats;

	private DistributionFinder distributionFinder;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		distributionFinder = new DistributionFinder(evDistributionFactory,
				pokemonStatsCollectionFactory);
	}

	@Test
	public void testDistributionFinderGetsInitialDistributionFromFactory() {
		distributionFinder.calculate(baseStats);

		verify(evDistributionFactory).getInitialCollection();
	}

	@Test
	public void testDistributionFinderGivesInitialCollectionToStatAlgorithm() {
		Set<EVDistribution> initialCollection = new HashSet<>();
		when(evDistributionFactory.getInitialCollection()).thenReturn(
				initialCollection);

		distributionFinder.calculate(baseStats);

		verify(pokemonStatsCollectionFactory).makeStatsCollection(
				initialCollection, baseStats);
	}

	@Test
	public void testDistributionReturnsResultsWhenCalculateCalled() {
		Set<EVDistribution> initialCollection = new HashSet<>();
		when(evDistributionFactory.getInitialCollection()).thenReturn(
				initialCollection);
		Set<PokemonStats> pokemonStatsSet = new HashSet<>();
		pokemonStatsSet.add(new PokemonStats());
		when(
				pokemonStatsCollectionFactory.makeStatsCollection(
						initialCollection, baseStats)).thenReturn(
				pokemonStatsSet);

		Set<PokemonStats> actualSet = distributionFinder.calculate(baseStats);

		assertSame(pokemonStatsSet, actualSet);
	}
}
