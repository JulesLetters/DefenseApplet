package application.test;

import java.util.Set;

import application.EVDistribution;

public interface IPokemonStatsCollectionFactory {

	Set<PokemonStats> makeStatsCollection(Set<EVDistribution> initialCollection);

}
