package application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import restrictions.IRestrictionsModel;
import factors.IFactorsModel;

public class DistributionFinderTest {

	@Mock
	private IEVDistributionFactory evDistributionFactory;
	@Mock
	private IPokemonStatsCollectionFactory pokemonStatsCollectionFactory;
	@Mock
	private IBaseStats baseStats;
	@Mock
	private IHarmAlgorithmFilter harmAlgFilter;
	@Mock
	private IRestrictionsModel restrictionsModel;
	@Mock
	private IFactorsModel factorsModel;

	private DistributionFinder distributionFinder;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		distributionFinder = new DistributionFinder(evDistributionFactory, pokemonStatsCollectionFactory, harmAlgFilter);
	}

	@Test
	public void testDistributionFinderGetsInitialDistributionFromFactory() {
		distributionFinder.calculate(baseStats, restrictionsModel, factorsModel);

		verify(evDistributionFactory).getInitialCollection(restrictionsModel);
	}

	@Test
	public void testDistributionFinderGivesInitialCollectionToStatAlgorithm() {
		Set<EVDistribution> initialCollection = new HashSet<>();
		when(evDistributionFactory.getInitialCollection(restrictionsModel)).thenReturn(initialCollection);

		distributionFinder.calculate(baseStats, restrictionsModel, factorsModel);

		verify(pokemonStatsCollectionFactory).makeStatsCollection(initialCollection, baseStats);
	}

	@Test
	public void testDistributionReturnsResultsWhenCalculateCalled() {
		Set<EVDistribution> initialCollection = new HashSet<>();
		when(evDistributionFactory.getInitialCollection(restrictionsModel)).thenReturn(initialCollection);
		Collection<PokemonStats> pokemonStatsCollection = new HashSet<>();
		pokemonStatsCollection.add(mock(PokemonStats.class));
		when(pokemonStatsCollectionFactory.makeStatsCollection(initialCollection, baseStats)).thenReturn(
				pokemonStatsCollection);
		when(harmAlgFilter.filter(pokemonStatsCollection, factorsModel)).thenReturn(pokemonStatsCollection);

		Set<PokemonStats> actualSet = distributionFinder.calculate(baseStats, restrictionsModel, factorsModel);

		assertEquals(pokemonStatsCollection, actualSet);
	}

	@Test
	public void testDistributionReturnsNoDuplicatePokemonStats() {
		Set<EVDistribution> initialCollection = new HashSet<>();
		when(evDistributionFactory.getInitialCollection(restrictionsModel)).thenReturn(initialCollection);
		PokemonStats pokemonStats = mock(PokemonStats.class);
		Collection<PokemonStats> pokemonStatsCollection = Arrays.asList(pokemonStats, pokemonStats);
		when(pokemonStatsCollectionFactory.makeStatsCollection(initialCollection, baseStats)).thenReturn(
				pokemonStatsCollection);

		when(harmAlgFilter.filter(pokemonStatsCollection, factorsModel)).thenReturn(pokemonStatsCollection);

		Set<PokemonStats> actualSet = distributionFinder.calculate(baseStats, restrictionsModel, factorsModel);

		Set<PokemonStats> expectedSet = Collections.singleton(pokemonStats);
		assertEquals(expectedSet, actualSet);
	}

	@Test
	public void testDistributionReturnsFilteredSetOfPokemonStats() {
		Set<EVDistribution> evDistributions = new HashSet<>();
		when(evDistributionFactory.getInitialCollection(restrictionsModel)).thenReturn(evDistributions);
		PokemonStats pokemonStats = mock(PokemonStats.class);
		Collection<PokemonStats> pokemonStatsCollection = Arrays.asList(pokemonStats, pokemonStats);
		when(pokemonStatsCollectionFactory.makeStatsCollection(evDistributions, baseStats)).thenReturn(
				pokemonStatsCollection);

		Collection<PokemonStats> filteredStatsCollection = Collections.singleton(mock(PokemonStats.class));
		when(harmAlgFilter.filter(pokemonStatsCollection, factorsModel)).thenReturn(filteredStatsCollection);

		Set<PokemonStats> actualSet = distributionFinder.calculate(baseStats, restrictionsModel, factorsModel);

		assertEquals(filteredStatsCollection, actualSet);

	}
}
