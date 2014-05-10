package factors.events;


public class SpDefNumeratorChanged implements ISpinnerEvent {

	private int spDefNumerator;

	public SpDefNumeratorChanged(int spDefNumerator) {
		this.spDefNumerator = spDefNumerator;
	}

	@Override
	public int getNumber() {
		return spDefNumerator;
	}

}
