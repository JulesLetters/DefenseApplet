package application;

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
	public int calculate(PokemonStats pokemonStats) {
		int result = (incomingHarm + PHYS_DAMAGE_BONUS) / pokemonStats.getDef()
				+ (incomingHarm + SPEC_DAMAGE_BONUS) / pokemonStats.getSpDef();
		return result / pokemonStats.getHP();
	}

}
