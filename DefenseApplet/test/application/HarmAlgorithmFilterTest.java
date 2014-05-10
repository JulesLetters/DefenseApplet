package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import factors.IFactorsModel;

public class HarmAlgorithmFilterTest {

	@Mock
	private IHarmCalculator harmCalculator;
	@Mock
	private IFactorsModel factorsModel;

	private HarmAlgorithmFilter harmAlgorithmFilter;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		harmAlgorithmFilter = new HarmAlgorithmFilter(harmCalculator);
	}

	@Test
	public void testFilterReturnsEmptyCollectionIfGivenNoPokemonStats() {
		assertEquals(0, harmAlgorithmFilter.filter(new HashSet<PokemonStats>(), factorsModel).size());
	}

	@Test
	public void testFilterReturnsOnlyElementIfOneGiven() {
		PokemonStats pokemonStats = mock(PokemonStats.class);
		Set<PokemonStats> pokemonStatsCollection = Collections.singleton(pokemonStats);

		Collection<PokemonStats> filteredCollection = harmAlgorithmFilter.filter(pokemonStatsCollection, factorsModel);

		assertEquals(1, filteredCollection.size());
		assertTrue(filteredCollection.contains(pokemonStats));
	}

	@Test
	public void testFilterReturnsOnlyBestOfTwoElements() {
		PokemonStats pokemonStats1 = mock(PokemonStats.class);
		PokemonStats pokemonStats2 = mock(PokemonStats.class);
		when(harmCalculator.calculate(pokemonStats1, factorsModel)).thenReturn(1);
		when(harmCalculator.calculate(pokemonStats2, factorsModel)).thenReturn(2);
		List<PokemonStats> pokemonStatsCollection = Arrays.asList(pokemonStats1, pokemonStats2);

		Collection<PokemonStats> filteredCollection = harmAlgorithmFilter.filter(pokemonStatsCollection, factorsModel);

		assertEquals(1, filteredCollection.size());
		assertTrue(filteredCollection.contains(pokemonStats1));
	}

	@Test
	public void testFilterReturnsTwoElementsIfBothElementsAreEqual() {
		PokemonStats pokemonStats1 = mock(PokemonStats.class);
		PokemonStats pokemonStats2 = mock(PokemonStats.class);
		when(harmCalculator.calculate(pokemonStats1, factorsModel)).thenReturn(3);
		when(harmCalculator.calculate(pokemonStats2, factorsModel)).thenReturn(3);
		List<PokemonStats> pokemonStatsCollection = Arrays.asList(pokemonStats1, pokemonStats2);

		Collection<PokemonStats> filteredCollection = harmAlgorithmFilter.filter(pokemonStatsCollection, factorsModel);

		assertEquals(2, filteredCollection.size());
		assertTrue(filteredCollection.contains(pokemonStats1));
		assertTrue(filteredCollection.contains(pokemonStats2));
	}

	@Test
	public void testFilterDropsAllOldElementsWhenBetterElementFound() {
		PokemonStats pokemonStats1 = mock(PokemonStats.class);
		PokemonStats pokemonStats2 = mock(PokemonStats.class);
		PokemonStats pokemonStats3 = mock(PokemonStats.class);
		when(harmCalculator.calculate(pokemonStats1, factorsModel)).thenReturn(5);
		when(harmCalculator.calculate(pokemonStats2, factorsModel)).thenReturn(5);
		when(harmCalculator.calculate(pokemonStats3, factorsModel)).thenReturn(4);
		List<PokemonStats> pokemonStatsCollection = Arrays.asList(pokemonStats1, pokemonStats2, pokemonStats3);

		Collection<PokemonStats> filteredCollection = harmAlgorithmFilter.filter(pokemonStatsCollection, factorsModel);

		assertEquals(1, filteredCollection.size());
		assertTrue(filteredCollection.contains(pokemonStats3));
	}
}
