package application;

public class StatCalculator implements IStatCalculator {

	@Override
	public int calculate(int baseStat, int statEVs) {
		return baseStat * 2 + statEVs / 4 + 36;
	}

}
