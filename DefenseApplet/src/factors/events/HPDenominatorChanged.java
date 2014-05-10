package factors.events;

import factors.ISpinnerEvent;

public class HPDenominatorChanged implements ISpinnerEvent {

	private int number;

	public HPDenominatorChanged(int number) {
		this.number = number;
	}

	@Override
	public int getNumber() {
		return number;
	}

}
