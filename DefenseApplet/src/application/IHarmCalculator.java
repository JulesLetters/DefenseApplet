package application;

import factors.IFactorsModel;

public interface IHarmCalculator {

	long calculate(PokemonStats pokemonStatsBetter, IFactorsModel factorsModel);

}
