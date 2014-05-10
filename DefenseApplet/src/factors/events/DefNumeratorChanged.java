package factors.events;

import factors.ISpinnerEvent;

public class DefNumeratorChanged implements ISpinnerEvent {

	private int number;

	public DefNumeratorChanged(int number) {
		this.number = number;
	}

	@Override
	public int getNumber() {
		return number;
	}

}
