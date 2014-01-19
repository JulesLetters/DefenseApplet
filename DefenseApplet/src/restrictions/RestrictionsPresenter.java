package restrictions;

import java.util.Arrays;
import java.util.List;

import application.Nature;

public class RestrictionsPresenter {

	private final List<Nature> initialSelectedNatures = Arrays.asList(
			Nature.Possibility.NEUTRAL.getNature(),
			Nature.Possibility.INC_DEF.getNature(),
			Nature.Possibility.INC_SPDEF.getNature(),
			Nature.Possibility.DEC_DEF.getNature(),
			Nature.Possibility.DEC_SPDEF.getNature());

	public RestrictionsPresenter(
			RestrictionsPanel view,
			final IRestrictionsModel model) {

		view.setViewListener(new IRestrictionsViewListener() {

			@Override
			public void natureAdded(Nature nature) {
				model.addNature(nature);
			}

			@Override
			public void natureRemoved(Nature nature) {
				model.removeNature(nature);
			}

		});

		view.setSelectedNatures(initialSelectedNatures);
	}

}
