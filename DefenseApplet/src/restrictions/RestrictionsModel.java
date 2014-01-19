package restrictions;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import application.Nature;

public class RestrictionsModel implements IRestrictionsModel {

	private Set<Nature> allowedNatures = Collections
			.synchronizedSet(new HashSet<Nature>());

	@Override
	public Set<Nature> getAllowedNatures() {
		synchronized (allowedNatures) {
			return new HashSet<>(allowedNatures);
		}
	}

	@Override
	public void addNature(Nature nature) {
		allowedNatures.add(nature);
	}

	@Override
	public void removeNature(Nature nature) {
		allowedNatures.remove(nature);
	}

	@Override
	public void setAllowedNatures(Collection<Nature> natures) {
		allowedNatures = Collections.synchronizedSet(new HashSet<>(natures));
	}

}
