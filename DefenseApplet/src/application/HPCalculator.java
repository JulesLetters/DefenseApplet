package application;

public class HPCalculator implements IStatCalculator {

	public int calculate(int baseHP, int hpEVs, Nature nature) {
		return baseHP * 2 + hpEVs / 4 + 141;
	}

}
