package application;

import java.util.Collection;

import factors.IFactorsModel;

public interface IHarmAlgorithmFilter {

	Collection<PokemonStats> filter(Collection<PokemonStats> pokemonStatsCollection, IFactorsModel factorsModel);

}
