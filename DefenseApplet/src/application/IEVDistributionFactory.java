package application;

import java.util.Set;

import restrictions.IRestrictionsModel;

public interface IEVDistributionFactory {

	Set<EVDistribution> getInitialCollection(
			IRestrictionsModel restrictionsModel);

}