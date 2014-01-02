package application;

import java.util.Set;

public interface IPokemonStatsCollectionFactory {

	Set<PokemonStats> makeStatsCollection(
			Set<EVDistribution> initialCollection, int hp, int def, int spDef);
}
