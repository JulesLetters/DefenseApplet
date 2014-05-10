package factors.events;


public class HPDenominatorChanged implements ISpinnerEvent {

	private int hpDenominator;

	public HPDenominatorChanged(int hpDenominator) {
		this.hpDenominator = hpDenominator;
	}

	@Override
	public int getNumber() {
		return hpDenominator;
	}

}
