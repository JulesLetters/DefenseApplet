package application;

public class DefCalculator extends AbstractStatCalculator implements
		IStatCalculator {

	public int calculate(int baseStat, int statEVs, Nature nature) {
		return super.calculate(baseStat, statEVs, nature.getDefAdjustment());
	}
}
