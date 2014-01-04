package application;

import java.util.Collection;

public interface IPokemonStatsCollectionFactory {

	Collection<PokemonStats> makeStatsCollection(
			Collection<EVDistribution> evDistributionCollection, IBaseStats baseStats);
}
