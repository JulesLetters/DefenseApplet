package factors;

import com.google.common.eventbus.EventBus;

public class FactorsMVPFactory {

	private final FactorsPanel view;
	private final IFactorsModel model;
	private final EventBus eventBus = new EventBus();

	public FactorsMVPFactory() {
		this.view = new FactorsPanel(eventBus);
		this.model = new FactorsModel();
		eventBus.register(new FactorsPresenter(model));
	}

	public FactorsPanel getView() {
		return view;
	}

	public IFactorsModel getModel() {
		return model;
	}

}
