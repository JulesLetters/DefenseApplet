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

}
