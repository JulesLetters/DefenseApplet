package application;

import factors.IFactorsModel;

public interface IHarmCalculator {

	int calculate(PokemonStats pokemonStatsBetter, IFactorsModel factorsModel);

}
