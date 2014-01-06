package application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class HarmCalculatorTest {

	private final int INCOMING_HARM = 20000;
	private final int PHYS_DAMAGE_BONUS = 2;
	private final int SPEC_DAMAGE_BONUS = 2;
	private HarmCalculator harmCalculator;

	@Before
	public void setUp() throws Exception {
		harmCalculator = new HarmCalculator(INCOMING_HARM);

	}

	@Test
	public void testBaselineHarm() {
		PokemonStats pokemonStats = mock(PokemonStats.class);
		when(pokemonStats.getHP()).thenReturn(1);
		when(pokemonStats.getDef()).thenReturn(1);
		when(pokemonStats.getSpDef()).thenReturn(1);

		int result = harmCalculator.calculate(pokemonStats);

		assertEquals(INCOMING_HARM + PHYS_DAMAGE_BONUS + INCOMING_HARM
				+ SPEC_DAMAGE_BONUS, result);
	}

	@Test
	public void testDefenseDividesPhysicalDamage() {
		PokemonStats pokemonStats = mock(PokemonStats.class);
		when(pokemonStats.getHP()).thenReturn(1);
		when(pokemonStats.getDef()).thenReturn(2);
		when(pokemonStats.getSpDef()).thenReturn(1);

		int result = harmCalculator.calculate(pokemonStats);

		assertEquals((INCOMING_HARM + PHYS_DAMAGE_BONUS) / 2 + INCOMING_HARM
				+ SPEC_DAMAGE_BONUS, result);
	}

	@Test
	public void testSpecialDefenseDividesSpecialDamage() {
		PokemonStats pokemonStats = mock(PokemonStats.class);
		when(pokemonStats.getHP()).thenReturn(1);
		when(pokemonStats.getDef()).thenReturn(1);
		when(pokemonStats.getSpDef()).thenReturn(2);

		int result = harmCalculator.calculate(pokemonStats);

		assertEquals(INCOMING_HARM + PHYS_DAMAGE_BONUS
				+ (INCOMING_HARM + SPEC_DAMAGE_BONUS) / 2, result);
	}

	@Test
	public void testHPDividesAllDamage() {
		PokemonStats pokemonStats = mock(PokemonStats.class);
		when(pokemonStats.getHP()).thenReturn(3);
		when(pokemonStats.getDef()).thenReturn(1);
		when(pokemonStats.getSpDef()).thenReturn(1);

		int result = harmCalculator.calculate(pokemonStats);

		assertEquals(
				(INCOMING_HARM + PHYS_DAMAGE_BONUS + INCOMING_HARM + SPEC_DAMAGE_BONUS) / 3,
				result);
	}
}
