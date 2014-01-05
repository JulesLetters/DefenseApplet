package application;

public class EVDistribution {

	private int hp;
	private int def;
	private int spDef;

	public EVDistribution(int hp, int def, int spDef) {
		this.hp = hp;
		this.def = def;
		this.spDef = spDef;
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
