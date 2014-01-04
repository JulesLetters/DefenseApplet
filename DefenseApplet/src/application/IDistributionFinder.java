package application;

import java.util.Set;

public interface IDistributionFinder {

	Set<PokemonStats> calculate(IBaseStats baseStats);

}