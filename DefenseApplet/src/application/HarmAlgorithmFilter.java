package application;

import java.util.ArrayList;
import java.util.Collection;

public class HarmAlgorithmFilter implements IHarmAlgorithmFilter {

	private IHarmCalculator harmCalculator;

	public HarmAlgorithmFilter() {
		this(new HarmCalculator());
	}

	public HarmAlgorithmFilter(IHarmCalculator harmCalculator) {
		this.harmCalculator = harmCalculator;
	}

	@Override
	public Collection<PokemonStats> filter(
			Collection<PokemonStats> pokemonStatsCollection) {

		Collection<PokemonStats> result = new ArrayList<>();

		Integer minHarm = Integer.MAX_VALUE;
		for (PokemonStats pokemonStats : pokemonStatsCollection) {
			int harm = harmCalculator.calculate(pokemonStats);
			if (harm < minHarm) {
				result = new ArrayList<>();
				minHarm = harm;
				result.add(pokemonStats);
			} else if (harm == minHarm) {
				result.add(pokemonStats);
			}
		}

		return result;
	}
}
