package application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PokemonStatsFactoryTest {
	@Mock
	IStatCalculator hpCalculator;
	@Mock
	IStatCalculator defCalculator;
	@Mock
	IStatCalculator spDefCalculator;

	private PokemonStatsFactory statsFactory;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		statsFactory = new PokemonStatsFactory(hpCalculator, defCalculator,
				spDefCalculator);
	}

	@Test
	public void testMakeStatsReturnsCorrectStats() {
		Random rand = new Random();
		EVDistribution evDistribution = mock(EVDistribution.class);
		int hpEVs = rand.nextInt(1000);
		int defEVs = rand.nextInt(1000);
		int spDefEVs = rand.nextInt(1000);
		when(evDistribution.getHP()).thenReturn(hpEVs);
		when(evDistribution.getDef()).thenReturn(defEVs);
		when(evDistribution.getSpDef()).thenReturn(spDefEVs);

		IBaseStats baseStats = mock(IBaseStats.class);
		int baseHP = rand.nextInt(1000);
		int baseDef = rand.nextInt(1000);
		int baseSpDef = rand.nextInt(1000);
		when(baseStats.getHP()).thenReturn(baseHP);
		when(baseStats.getDef()).thenReturn(baseDef);
		when(baseStats.getSpDef()).thenReturn(baseSpDef);

		int hpStat = rand.nextInt(1000);
		int defStat = rand.nextInt(1000);
		int spDefStat = rand.nextInt(1000);

		when(hpCalculator.calculate(baseHP, hpEVs)).thenReturn(hpStat);
		when(defCalculator.calculate(baseDef, defEVs)).thenReturn(defStat);
		when(spDefCalculator.calculate(baseSpDef, spDefEVs)).thenReturn(
				spDefStat);

		PokemonStats pokemonStats = statsFactory.makeStats(evDistribution,
				baseStats);

		assertEquals(hpStat, pokemonStats.getHP());
		assertEquals(defStat, pokemonStats.getDef());
		assertEquals(spDefStat, pokemonStats.getSpDef());
	}

	@Test
	public void testStatsReturnsWithDistributionAttached() {
		EVDistribution evDistribution = mock(EVDistribution.class);
		IBaseStats baseStats = mock(IBaseStats.class);
		PokemonStats pokemonStats = statsFactory.makeStats(evDistribution,
				baseStats);

		assertEquals(evDistribution, pokemonStats.getDistribution());
	}
}
