package restrictions;

public class RestrictionsMVPFactory {

	private final RestrictionsPanel view;
	private final IRestrictionsModel model;

	public RestrictionsMVPFactory() {
		this.view = new RestrictionsPanel();
		this.model = new RestrictionsModel();
		new RestrictionsPresenter(view, model);
	}

	public RestrictionsPanel getView() {
		return view;
	}

	public IRestrictionsModel getModel() {
		return model;
	}
}
