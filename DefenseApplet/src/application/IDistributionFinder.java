package application;

import java.util.Set;

import restrictions.IRestrictionsModel;

public interface IDistributionFinder {

	Set<PokemonStats> calculate(IBaseStats baseStats,
			IRestrictionsModel restrictionsModel);

}