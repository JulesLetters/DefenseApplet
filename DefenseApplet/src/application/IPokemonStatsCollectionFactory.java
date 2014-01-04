package application;

import java.util.Set;

public interface IPokemonStatsCollectionFactory {

	Set<PokemonStats> makeStatsCollection(
			Set<EVDistribution> initialCollection, IBaseStats baseStats);
}
