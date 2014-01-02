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

	private DistributionFinder distributionFinder;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		distributionFinder = new DistributionFinder(evDistributionFactory,
				pokemonStatsCollectionFactory);
	}

	@Test
	public void testDistributionFinderGetsInitialDistributionFromFactory() {
		distributionFinder.calculate(0, 1, 2);

		verify(evDistributionFactory).getInitialCollection();
	}

	@Test
	public void testDistributionFinderGivesInitialCollectionToStatAlgorithm() {
		Set<EVDistribution> initialCollection = new HashSet<>();
		when(evDistributionFactory.getInitialCollection()).thenReturn(
				initialCollection);

		distributionFinder.calculate(3, 4, 5);

		verify(pokemonStatsCollectionFactory).makeStatsCollection(
				initialCollection, 3, 4, 5);
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
						initialCollection, 6, 7, 8))
				.thenReturn(pokemonStatsSet);

		Set<PokemonStats> actualSet = distributionFinder.calculate(6, 7, 8);

		assertSame(pokemonStatsSet, actualSet);
	}
}
