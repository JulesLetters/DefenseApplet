package application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class PokemonDefensiveStatsTableRowBuilderTest {

	private static final int EV_CAP_PLUS_ONE = 511;
	private static final int ONE_THOUSAND = 1000;
	private PokemonDefensiveStatsTableRowBuilder rowBuilder;

	@Before
	public void setUp() throws Exception {
		rowBuilder = new PokemonDefensiveStatsTableRowBuilder();
	}

	@Test
	public void testBuildRowReturnsObjectArrayMakeFromPokemonStats() {
		EVDistribution evDistro = mock(EVDistribution.class);
		PokemonStats pokemonStats = mock(PokemonStats.class);
		when(pokemonStats.getDistribution()).thenReturn(evDistro);
		Random rand = new Random();
		int[] expectedArray = { rand.nextInt(EV_CAP_PLUS_ONE),
				rand.nextInt(EV_CAP_PLUS_ONE), rand.nextInt(EV_CAP_PLUS_ONE) };
		when(evDistro.getHP()).thenReturn(expectedArray[0]);
		when(evDistro.getDef()).thenReturn(expectedArray[1]);
		when(evDistro.getSpDef()).thenReturn(expectedArray[2]);

		Object[] actualArray = rowBuilder.buildRow(pokemonStats);

		for (int i = 0; i < 3; ++i) {
			assertEquals(expectedArray[i], actualArray[i]);
		}
	}

	@Test
	public void testBuildRowReturnsNatureIntoObjectArray() {
		EVDistribution evDistro = mock(EVDistribution.class);
		PokemonStats pokemonStats = mock(PokemonStats.class);
		Nature nature = mock(Nature.class);
		when(pokemonStats.getDistribution()).thenReturn(evDistro);
		when(evDistro.getNature()).thenReturn(nature);

		Object[] actualArray = rowBuilder.buildRow(pokemonStats);

		assertEquals(nature, actualArray[3]);
	}

	@Test
	public void testBuildRowReturnsCalculatedStatsIntoObjectArray() {
		EVDistribution evDistro = mock(EVDistribution.class);
		PokemonStats pokemonStats = mock(PokemonStats.class);
		when(pokemonStats.getDistribution()).thenReturn(evDistro);
		Random rand = new Random();
		int[] expectedArray = { rand.nextInt(ONE_THOUSAND),
				rand.nextInt(ONE_THOUSAND), rand.nextInt(ONE_THOUSAND) };
		when(pokemonStats.getHP()).thenReturn(expectedArray[0]);
		when(pokemonStats.getDef()).thenReturn(expectedArray[1]);
		when(pokemonStats.getSpDef()).thenReturn(expectedArray[2]);

		Object[] actualArray = rowBuilder.buildRow(pokemonStats);

		for (int i = 0; i < 3; ++i) {
			assertEquals(expectedArray[i], actualArray[i + 4]);
		}
	}
}
