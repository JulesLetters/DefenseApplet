package application;

public class BaseStats implements IBaseStats {
	private int hp;
	private int def;
	private int spDef;

	public BaseStats(int hp, int def, int spDef) {
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
