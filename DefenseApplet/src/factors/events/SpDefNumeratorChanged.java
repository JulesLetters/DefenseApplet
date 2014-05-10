package factors.events;

import factors.ISpinnerEvent;

public class SpDefNumeratorChanged implements ISpinnerEvent {

	private int number;

	public SpDefNumeratorChanged(int number) {
		this.number = number;
	}

	@Override
	public int getNumber() {
		return number;
	}

}
