package application;

import java.util.Collection;

public interface IHarmAlgorithmFilter {

	Collection<PokemonStats> filter(
			Collection<PokemonStats> pokemonStatsCollection);

}
