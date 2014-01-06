package application;

import application.Nature.Adjustment;

public class AbstractStatCalculator {

	public int calculate(int baseStat, int statEVs, Adjustment adjustment) {
		int stat = calculate(baseStat, statEVs);
		if (adjustment == Nature.Adjustment.INCREASE) {
			return stat * 11 / 10;
		} else if (adjustment == Nature.Adjustment.DECREASE) {
			return stat * 9 / 10;
		}
		return stat;
	}

	private int calculate(int baseStat, int statEVs) {
		return (baseStat * 2 + statEVs / 4 + 36);
	}
}
