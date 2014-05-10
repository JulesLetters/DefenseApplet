package factors.events;

import factors.ISpinnerEvent;

public class HPNumeratorChanged implements ISpinnerEvent {

	private int number;

	public HPNumeratorChanged(int number) {
		this.number = number;
	}

	@Override
	public int getNumber() {
		return number;
	}

}
