package application;

import java.util.Set;

public interface IDistributionFinder {

	Set<PokemonStats> calculate(int hp, int def, int spDef);

}