package restrictions;

import java.util.Collection;
import java.util.Set;

import application.Nature;

public interface IRestrictionsModel {

	Set<Nature> getAllowedNatures();

	void addNature(Nature nature);

	void removeNature(Nature nature);

	void setAllowedNatures(Collection<Nature> initialSelectedNatures);
}
