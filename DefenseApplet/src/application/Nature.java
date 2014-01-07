package application;

public class Nature {

	private Adjustment defense;
	private Adjustment specialDefense;

	public enum Adjustment {
		DECREASE, NEUTRAL, INCREASE
	}

	public Nature(Adjustment defense, Adjustment specialDefense) {
		this.defense = defense;
		this.specialDefense = specialDefense;
	}

	public Adjustment getDefAdjustment() {
		return defense;
	}

	public Adjustment getSpDefAdjustment() {
		return specialDefense;
	}

	@Override
	public String toString() {
		return prefix(defense) + "Def, " + prefix(specialDefense) + "SpDef";
	}

	private String prefix(Adjustment stat) {
		if (stat == Adjustment.DECREASE) {
			return "-";
		}
		if (stat == Adjustment.INCREASE) {
			return "+";
		}
		return "=";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((defense == null) ? 0 : defense.hashCode());
		result = prime * result
				+ ((specialDefense == null) ? 0 : specialDefense.hashCode());
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
		Nature other = (Nature) obj;
		if (defense != other.defense)
			return false;
		if (specialDefense != other.specialDefense)
			return false;
		return true;
	}

}
