package factors.events;


public class DefNumeratorChanged implements ISpinnerEvent {

	private int defNumerator;

	public DefNumeratorChanged(int defNumerator) {
		this.defNumerator = defNumerator;
	}

	@Override
	public int getNumber() {
		return defNumerator;
	}

}
