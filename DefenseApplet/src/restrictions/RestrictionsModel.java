package restrictions;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import application.Nature;

public class RestrictionsModel implements IRestrictionsModel {

	private Set<Nature> allowedNatures = Collections.synchronizedSet(new HashSet<Nature>());
	private int maxEVs;
	private int minEVs;

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

	@Override
	public void setMaxEVs(int value) {
		this.maxEVs = value;
	}

	@Override
	public void setMinEVs(int value) {
		this.minEVs = value;
	}

	@Override
	public int getMaxEVs() {
		return maxEVs;
	}

	@Override
	public int getMinEVs() {
		return minEVs;
	}

}
