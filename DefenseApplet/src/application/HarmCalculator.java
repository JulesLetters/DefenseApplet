package application;

import factors.IFactorsModel;

public class HarmCalculator implements IHarmCalculator {

	private static final int PHYS_DAMAGE_BONUS = 2;
	private static final int SPEC_DAMAGE_BONUS = 2;
	private int incomingHarm;

	public HarmCalculator() {
		this(320844000);
	}

	public HarmCalculator(int incomingHarm) {
		this.incomingHarm = incomingHarm;
	}

	@Override
	public int calculate(PokemonStats pokemonStats, IFactorsModel factorsModel) {
		int initialPhysicalDamage = (incomingHarm + PHYS_DAMAGE_BONUS) * factorsModel.getDefDenominator();
		int initialSpecialDamage = (incomingHarm + SPEC_DAMAGE_BONUS) * factorsModel.getSpDefDenominator();

		int physicalDamage = initialPhysicalDamage / pokemonStats.getDef() / factorsModel.getDefNumerator();
		int specialDamage = initialSpecialDamage / pokemonStats.getSpDef() / factorsModel.getSpDefNumerator();

		int result = physicalDamage + specialDamage;
		result *= factorsModel.getHPDenominator();
		result /= factorsModel.getHPNumerator();
		return result / pokemonStats.getHP();
	}
}
