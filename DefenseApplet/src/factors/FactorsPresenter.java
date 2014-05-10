package factors;

import com.google.common.eventbus.Subscribe;

import factors.events.DefDenominatorChanged;
import factors.events.DefNumeratorChanged;
import factors.events.HPDenominatorChanged;
import factors.events.HPNumeratorChanged;
import factors.events.SpDefDenominatorChanged;
import factors.events.SpDefNumeratorChanged;

public class FactorsPresenter {

	private IFactorsModel model;

	public FactorsPresenter(IFactorsModel model) {
		this.model = model;
	}

	@Subscribe
	public void receiveHPNumeratorEvent(HPNumeratorChanged e) {
		model.setHPNumerator(e.getNumber());
	}

	@Subscribe
	public void receiveHPDenominatorEvent(HPDenominatorChanged e) {
		model.setHPDenominator(e.getNumber());
	}

	@Subscribe
	public void receiveDefNumeratorEvent(DefNumeratorChanged e) {
		model.setDefNumerator(e.getNumber());
	}

	@Subscribe
	public void receiveDefDenominatorEvent(DefDenominatorChanged e) {
		model.setDefDenominator(e.getNumber());
	}

	@Subscribe
	public void receiveSpDefNumeratorEvent(SpDefNumeratorChanged e) {
		model.setSpDefNumerator(e.getNumber());
	}

	@Subscribe
	public void receiveSpDefDenominatorEvent(SpDefDenominatorChanged e) {
		model.setSpDefDenominator(e.getNumber());
	}

}
