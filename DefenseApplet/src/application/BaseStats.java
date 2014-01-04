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

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getSpDef() {
		return spDef;
	}

	public void setSpDef(int spDef) {
		this.spDef = spDef;
	}
}
