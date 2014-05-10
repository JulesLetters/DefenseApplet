package factors;

public class FactorsModel implements IFactorsModel {
	private int hpNumerator = 1;
	private int hpDenominator = 1;
	private int defNumerator = 1;
	private int defDenominator = 1;
	private int spDefNumerator = 1;
	private int spDefDenominator = 1;

	@Override
	public int getHPNumerator() {
		return hpNumerator;
	}

	@Override
	public void setHPNumerator(int hpNumerator) {
		this.hpNumerator = hpNumerator;
	}

	@Override
	public int getHPDenominator() {
		return hpDenominator;
	}

	@Override
	public void setHPDenominator(int hpDenominator) {
		this.hpDenominator = hpDenominator;
	}

	@Override
	public int getDefNumerator() {
		return defNumerator;
	}

	@Override
	public void setDefNumerator(int defNumerator) {
		this.defNumerator = defNumerator;
	}

	@Override
	public int getDefDenominator() {
		return defDenominator;
	}

	@Override
	public void setDefDenominator(int defDenominator) {
		this.defDenominator = defDenominator;
	}

	@Override
	public int getSpDefNumerator() {
		return spDefNumerator;
	}

	@Override
	public void setSpDefNumerator(int spDefNumerator) {
		this.spDefNumerator = spDefNumerator;
	}

	@Override
	public int getSpDefDenominator() {
		return spDefDenominator;
	}

	@Override
	public void setSpDefDenominator(int spDefDenominator) {
		this.spDefDenominator = spDefDenominator;
	}

}
