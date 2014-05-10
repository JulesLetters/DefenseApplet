package application;

import java.util.Set;

import restrictions.IRestrictionsModel;
import factors.IFactorsModel;

public interface IDistributionFinder {

	Set<PokemonStats> calculate(IBaseStats baseStats, IRestrictionsModel restrictionsModel, IFactorsModel factorsModel);

}