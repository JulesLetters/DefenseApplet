package application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import factors.IFactorsModel;

public class HarmCalculatorTest {

	@Mock
	private IFactorsModel factorsModel;
	@Mock
	private PokemonStats pokemonStats;

	private final int INCOMING_HARM = 20000;
	private final int PHYS_DAMAGE_BONUS = 2;
	private final int SPEC_DAMAGE_BONUS = 2;
	private HarmCalculator harmCalculator;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		when(pokemonStats.getHP()).thenReturn(1);
		when(pokemonStats.getDef()).thenReturn(1);
		when(pokemonStats.getSpDef()).thenReturn(1);

		when(factorsModel.getHPNumerator()).thenReturn(1);
		when(factorsModel.getHPDenominator()).thenReturn(1);
		when(factorsModel.getDefNumerator()).thenReturn(1);
		when(factorsModel.getDefDenominator()).thenReturn(1);
		when(factorsModel.getSpDefNumerator()).thenReturn(1);
		when(factorsModel.getSpDefDenominator()).thenReturn(1);

		harmCalculator = new HarmCalculator(INCOMING_HARM);
	}

	@Test
	public void testBaselineHarm() {
		int result = harmCalculator.calculate(pokemonStats, factorsModel);

		assertEquals(INCOMING_HARM + PHYS_DAMAGE_BONUS + INCOMING_HARM + SPEC_DAMAGE_BONUS, result);
	}

	@Test
	public void testDefenseDividesPhysicalDamage() {
		when(pokemonStats.getDef()).thenReturn(2);

		int result = harmCalculator.calculate(pokemonStats, factorsModel);

		assertEquals((INCOMING_HARM + PHYS_DAMAGE_BONUS) / 2 + INCOMING_HARM + SPEC_DAMAGE_BONUS, result);
	}

	@Test
	public void testSpecialDefenseDividesSpecialDamage() {
		when(pokemonStats.getSpDef()).thenReturn(2);

		int result = harmCalculator.calculate(pokemonStats, factorsModel);

		assertEquals(INCOMING_HARM + PHYS_DAMAGE_BONUS + (INCOMING_HARM + SPEC_DAMAGE_BONUS) / 2, result);
	}

	@Test
	public void testHPDividesAllDamage() {
		when(pokemonStats.getHP()).thenReturn(3);

		int result = harmCalculator.calculate(pokemonStats, factorsModel);

		assertEquals((INCOMING_HARM + PHYS_DAMAGE_BONUS + INCOMING_HARM + SPEC_DAMAGE_BONUS) / 3, result);
	}

	// HP Factors
	@Test
	public void testFactorHPNumeratorDividesAllDamage() {
		when(factorsModel.getHPNumerator()).thenReturn(3);

		int result = harmCalculator.calculate(pokemonStats, factorsModel);

		assertEquals((INCOMING_HARM + PHYS_DAMAGE_BONUS + INCOMING_HARM + SPEC_DAMAGE_BONUS) / 3, result);
	}

	@Test
	public void testFactorHPDenominatorMultiplesAllDamage() {
		when(factorsModel.getHPDenominator()).thenReturn(3);

		int result = harmCalculator.calculate(pokemonStats, factorsModel);

		assertEquals((INCOMING_HARM + PHYS_DAMAGE_BONUS + INCOMING_HARM + SPEC_DAMAGE_BONUS) * 3, result);
	}

	@Test
	public void testMultiplyHPDenominatorBeforeDividingByHPNumerator() {
		when(factorsModel.getHPDenominator()).thenReturn(2);
		when(factorsModel.getHPNumerator()).thenReturn(3);

		int result = harmCalculator.calculate(pokemonStats, factorsModel);

		assertEquals((INCOMING_HARM + PHYS_DAMAGE_BONUS + INCOMING_HARM + SPEC_DAMAGE_BONUS) * 2 / 3, result);
	}

	@Test
	public void testMultiplyHPDenominatorBeforeDividingByHPStat() {
		when(pokemonStats.getHP()).thenReturn(3);
		when(factorsModel.getHPDenominator()).thenReturn(2);

		int result = harmCalculator.calculate(pokemonStats, factorsModel);

		assertEquals((INCOMING_HARM + PHYS_DAMAGE_BONUS + INCOMING_HARM + SPEC_DAMAGE_BONUS) * 2 / 3, result);
	}

	// Physical Defense Factors
	@Test
	public void testPhysicalDefenseMultipliedByDefenseFactorNumerator() {
		when(factorsModel.getDefNumerator()).thenReturn(2);

		int result = harmCalculator.calculate(pokemonStats, factorsModel);

		assertEquals(((INCOMING_HARM + PHYS_DAMAGE_BONUS) / 2 + INCOMING_HARM + SPEC_DAMAGE_BONUS), result);
	}

	@Test
	public void testPhysicalDefenseDividedByDefenseFactorDenominator() {
		when(factorsModel.getDefDenominator()).thenReturn(2);

		int result = harmCalculator.calculate(pokemonStats, factorsModel);

		assertEquals(((INCOMING_HARM + PHYS_DAMAGE_BONUS) * 2 + INCOMING_HARM + SPEC_DAMAGE_BONUS), result);
	}

	@Test
	public void testPhysicalDefenseIsMultipliedByFactorNumeratorBeforeDividingByFactorDenominator() {
		when(factorsModel.getDefDenominator()).thenReturn(5);
		when(factorsModel.getDefNumerator()).thenReturn(3);

		int result = harmCalculator.calculate(pokemonStats, factorsModel);

		assertEquals(((INCOMING_HARM + PHYS_DAMAGE_BONUS) * 5 / 3 + INCOMING_HARM + SPEC_DAMAGE_BONUS), result);
	}

	@Test
	public void testPhysicalDefenseIsMultipliedByFactorNumeratorBeforeDividingByDefense() {
		when(pokemonStats.getDef()).thenReturn(3);
		when(factorsModel.getDefDenominator()).thenReturn(5);

		int result = harmCalculator.calculate(pokemonStats, factorsModel);

		assertEquals(((INCOMING_HARM + PHYS_DAMAGE_BONUS) * 5 / 3 + INCOMING_HARM + SPEC_DAMAGE_BONUS), result);
	}

	// Special Defense Factors
	@Test
	public void testSpecialDefenseMultipliedBySpecialDefenseFactorNumerator() {
		when(factorsModel.getSpDefNumerator()).thenReturn(2);

		int result = harmCalculator.calculate(pokemonStats, factorsModel);

		assertEquals((INCOMING_HARM + PHYS_DAMAGE_BONUS + (INCOMING_HARM + SPEC_DAMAGE_BONUS) / 2), result);
	}

	@Test
	public void testSpecialDefenseDividedBySpecialDefenseFactorDenominator() {
		when(factorsModel.getSpDefDenominator()).thenReturn(2);

		int result = harmCalculator.calculate(pokemonStats, factorsModel);

		assertEquals((INCOMING_HARM + PHYS_DAMAGE_BONUS + (INCOMING_HARM + SPEC_DAMAGE_BONUS) * 2), result);
	}

	@Test
	public void testSpecialDefenseIsMultipliedByFactorNumeratorBeforeDividingByFactorDenominator() {
		when(factorsModel.getSpDefDenominator()).thenReturn(5);
		when(factorsModel.getSpDefNumerator()).thenReturn(3);

		int result = harmCalculator.calculate(pokemonStats, factorsModel);

		assertEquals((INCOMING_HARM + PHYS_DAMAGE_BONUS + (INCOMING_HARM + SPEC_DAMAGE_BONUS) * 5 / 3), result);
	}

	@Test
	public void testSpecialDefenseIsMultipliedByFactorNumeratorBeforeDividingBySpecialDefense() {
		when(pokemonStats.getSpDef()).thenReturn(3);
		when(factorsModel.getSpDefDenominator()).thenReturn(5);

		int result = harmCalculator.calculate(pokemonStats, factorsModel);

		assertEquals((INCOMING_HARM + PHYS_DAMAGE_BONUS + (INCOMING_HARM + SPEC_DAMAGE_BONUS) * 5 / 3), result);
	}
}
