package restrictions;

import application.Nature;

public interface IRestrictionsViewListener {

	void natureAdded(Nature nature);

	void natureRemoved(Nature clicked);

	void maxEVsChanged(int value);

	void minEVsChanged(int value);

}
