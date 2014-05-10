package factors.events;

import factors.ISpinnerEvent;

public class SpDefDenominatorChanged implements ISpinnerEvent {

	private int number;

	public SpDefDenominatorChanged(int number) {
		this.number = number;
	}

	@Override
	public int getNumber() {
		return number;
	}

}
