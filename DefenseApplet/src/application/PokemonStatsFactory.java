package application;

public class PokemonStatsFactory {

	private IStatCalculator hpCalculator;
	private IStatCalculator defCalculator;
	private IStatCalculator spDefCalculator;

	public PokemonStatsFactory() {
		this(new HPCalculator(), new DefCalculator(), new SpDefCalculator());
	}

	protected PokemonStatsFactory(IStatCalculator hpCalculator,
			IStatCalculator defCalculator, IStatCalculator spDefCalculator) {
		this.hpCalculator = hpCalculator;
		this.defCalculator = defCalculator;
		this.spDefCalculator = spDefCalculator;
	}

	public PokemonStats makeStats(EVDistribution evDistribution,
			IBaseStats baseStats) {
		int hp = hpCalculator.calculate(baseStats.getHP(),
				evDistribution.getHP(), evDistribution.getNature());
		int def = defCalculator.calculate(baseStats.getDef(),
				evDistribution.getDef(), evDistribution.getNature());
		int spDef = spDefCalculator.calculate(baseStats.getSpDef(),
				evDistribution.getSpDef(), evDistribution.getNature());

		return new PokemonStats(hp, def, spDef, evDistribution);
	}

}
