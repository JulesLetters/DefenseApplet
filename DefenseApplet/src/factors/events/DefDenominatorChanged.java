package factors.events;


public class DefDenominatorChanged implements ISpinnerEvent {

	private int defDenominator;

	public DefDenominatorChanged(int defDenominator) {
		this.defDenominator = defDenominator;
	}

	@Override
	public int getNumber() {
		return defDenominator;
	}

}
