package restrictions;

import java.util.Arrays;
import java.util.List;

import application.Nature;

public class RestrictionsPresenter {

	private final List<Nature> initialSelectedNatures = Arrays.asList(Nature.Possibility.NEUTRAL.getNature(),
			Nature.Possibility.INC_DEF.getNature(), Nature.Possibility.INC_SPDEF.getNature());

	public RestrictionsPresenter(RestrictionsPanel view, final IRestrictionsModel model) {

		view.setViewListener(new IRestrictionsViewListener() {

			@Override
			public void natureAdded(Nature nature) {
				model.addNature(nature);
			}

			@Override
			public void natureRemoved(Nature nature) {
				model.removeNature(nature);
			}

			@Override
			public void maxEVsChanged(int value) {
				model.setMaxEVs(value);
			}

			@Override
			public void minEVsChanged(int value) {
				model.setMinEVs(value);
			}

		});

		view.setSelectedNatures(initialSelectedNatures);
	}

}
