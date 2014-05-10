package application;

import factors.IFactorsModel;

public class HarmCalculator implements IHarmCalculator {

	private static final long PHYS_DAMAGE_BONUS = 2;
	private static final long SPEC_DAMAGE_BONUS = 2;
	private long incomingHarm;

	public HarmCalculator() {
		this(320844000);
	}

	public HarmCalculator(long incomingHarm) {
		this.incomingHarm = incomingHarm;
	}

	@Override
	public long calculate(PokemonStats pokemonStats, IFactorsModel factorsModel) {
		long initialPhysicalDamage = (incomingHarm + PHYS_DAMAGE_BONUS) * factorsModel.getDefDenominator();
		long initialSpecialDamage = (incomingHarm + SPEC_DAMAGE_BONUS) * factorsModel.getSpDefDenominator();

		long physicalDamage = initialPhysicalDamage / pokemonStats.getDef() / factorsModel.getDefNumerator();
		long specialDamage = initialSpecialDamage / pokemonStats.getSpDef() / factorsModel.getSpDefNumerator();

		long result = physicalDamage + specialDamage;
		result *= factorsModel.getHPDenominator();
		result /= factorsModel.getHPNumerator();
		return result / pokemonStats.getHP();
	}
}
