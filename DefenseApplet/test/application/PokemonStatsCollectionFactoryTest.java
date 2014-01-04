package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PokemonStatsCollectionFactoryTest {

	@Mock
	private IBaseStats baseStats;
	@Mock
	private PokemonStatsFactory statsFactory;
	private List<EVDistribution> evDistributionCollection;

	private PokemonStatsCollectionFactory collectionFactory;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		evDistributionCollection = new ArrayList<>();
		collectionFactory = new PokemonStatsCollectionFactory(statsFactory);
	}

	@Test
	public void testFactoryReturnsEmptyCollectionWhenGivenNoDistributions() {

		Collection<PokemonStats> statsCollection = collectionFactory
				.makeStatsCollection(evDistributionCollection, baseStats);

		assertNotNull(statsCollection);
		assert (statsCollection.isEmpty());
	}

	@Test
	public void testFactoryReturnsSingleElementCollectionWhenGivenOneDistribution() {
		EVDistribution evDistribution = mock(EVDistribution.class);
		PokemonStats pokemonStats = mock(PokemonStats.class);
		evDistributionCollection.add(evDistribution);
		when(statsFactory.makeStats(evDistribution, baseStats)).thenReturn(
				pokemonStats);

		Collection<PokemonStats> statsCollection = collectionFactory
				.makeStatsCollection(evDistributionCollection, baseStats);

		assertNotNull(statsCollection);
		assertEquals(1, statsCollection.size());
		PokemonStats actualPokemonStats = statsCollection.iterator().next();
		assertEquals(pokemonStats, actualPokemonStats);
	}

	@Test
	public void testFactoryReturnsMultipleElementCollectionWhenGivenOneDistribution() {
		EVDistribution evDistribution1 = mock(EVDistribution.class);
		EVDistribution evDistribution2 = mock(EVDistribution.class);
		EVDistribution evDistribution3 = mock(EVDistribution.class);
		PokemonStats pokemonStats1 = mock(PokemonStats.class);
		PokemonStats pokemonStats2 = mock(PokemonStats.class);
		PokemonStats pokemonStats3 = mock(PokemonStats.class);
		evDistributionCollection = Arrays.asList(evDistribution1,
				evDistribution2, evDistribution3);
		when(statsFactory.makeStats(evDistribution1, baseStats)).thenReturn(
				pokemonStats1);
		when(statsFactory.makeStats(evDistribution2, baseStats)).thenReturn(
				pokemonStats2);
		when(statsFactory.makeStats(evDistribution3, baseStats)).thenReturn(
				pokemonStats3);

		Collection<PokemonStats> statsCollection = collectionFactory
				.makeStatsCollection(evDistributionCollection, baseStats);

		assertNotNull(statsCollection);
		assertEquals(3, statsCollection.size());
		assert (statsCollection.contains(pokemonStats1));
		assert (statsCollection.contains(pokemonStats2));
		assert (statsCollection.contains(pokemonStats3));
	}
}
