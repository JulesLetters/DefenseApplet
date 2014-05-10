package factors.events;


public class HPNumeratorChanged implements ISpinnerEvent {

	private int hpNumerator;

	public HPNumeratorChanged(int hpNumerator) {
		this.hpNumerator = hpNumerator;
	}

	@Override
	public int getNumber() {
		return hpNumerator;
	}

}
