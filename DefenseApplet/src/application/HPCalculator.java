package application;

public class HPCalculator implements IStatCalculator {

	@Override
	public int calculate(int baseHP, int hpEVs) {
		return baseHP * 2 + hpEVs / 4 + 141;
	}

}
