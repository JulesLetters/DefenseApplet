package application;

public class PokemonStats {

	private int hp;
	private int def;
	private int spDef;
	private EVDistribution evDistribution;

	public PokemonStats(int hp, int def, int spDef,
			EVDistribution evDistribution) {
		this.hp = hp;
		this.def = def;
		this.spDef = spDef;
		this.evDistribution = evDistribution;
	}

	public EVDistribution getDistribution() {
		return evDistribution;
	}

	public int getHP() {
		return hp;
	}

	public int getDef() {
		return def;
	}

	public int getSpDef() {
		return spDef;
	}

}
