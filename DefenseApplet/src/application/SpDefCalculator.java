package application;

public class SpDefCalculator extends AbstractStatCalculator implements
		IStatCalculator {

	@Override
	public int calculate(int baseStat, int statEVs, Nature nature) {
		return super.calculate(baseStat, statEVs, nature.getSpDefAdjustment());
	}
}
