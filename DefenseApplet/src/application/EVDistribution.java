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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + def;
		result = prime * result + hp;
		result = prime * result + spDef;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EVDistribution other = (EVDistribution) obj;
		if (def != other.def)
			return false;
		if (hp != other.hp)
			return false;
		if (spDef != other.spDef)
			return false;
		return true;
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
