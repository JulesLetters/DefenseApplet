package factors.events;


public class SpDefDenominatorChanged implements ISpinnerEvent {

	private int spDefDenominator;

	public SpDefDenominatorChanged(int spDefDenominator) {
		this.spDefDenominator = spDefDenominator;
	}

	@Override
	public int getNumber() {
		return spDefDenominator;
	}

}
