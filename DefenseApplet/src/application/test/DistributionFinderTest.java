package application.test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import application.DistributionFinder;
import application.EVDistribution;
import application.IEVDistributionFactory;

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
		distributionFinder.calculate();

		verify(evDistributionFactory).getInitialCollection();
	}

	@Test
	public void testDistributionFinderGivesInitialCollectionToStatAlgorithm() {
		Set<EVDistribution> initialCollection = new HashSet<>();
		when(evDistributionFactory.getInitialCollection()).thenReturn(
				initialCollection);

		distributionFinder.calculate();

		verify(pokemonStatsCollectionFactory).makeStatsCollection(
				initialCollection);
	}

	@Test
	public void testDistributionReturnsResultsWhenCalculateCalled() {
		Set<EVDistribution> initialCollection = new HashSet<>();
		when(evDistributionFactory.getInitialCollection()).thenReturn(
				initialCollection);
		Set<PokemonStats> pokemonStatsSet = new HashSet<>();
		pokemonStatsSet.add(new PokemonStats());
		when(
				pokemonStatsCollectionFactory
						.makeStatsCollection(initialCollection)).thenReturn(
				pokemonStatsSet);

		Set<PokemonStats> actualSet = distributionFinder.calculate();

		assertSame(pokemonStatsSet, actualSet);
	}
}
