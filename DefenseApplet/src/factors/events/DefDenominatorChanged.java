package factors.events;

import factors.ISpinnerEvent;

public class DefDenominatorChanged implements ISpinnerEvent {

	private int number;

	public DefDenominatorChanged(int number) {
		this.number = number;
	}

	@Override
	public int getNumber() {
		return number;
	}

}
