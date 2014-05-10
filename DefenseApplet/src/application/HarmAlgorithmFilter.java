package application;

import java.util.ArrayList;
import java.util.Collection;

import factors.IFactorsModel;

public class HarmAlgorithmFilter implements IHarmAlgorithmFilter {

	private IHarmCalculator harmCalculator;

	public HarmAlgorithmFilter() {
		this(new HarmCalculator());
	}

	public HarmAlgorithmFilter(IHarmCalculator harmCalculator) {
		this.harmCalculator = harmCalculator;
	}

	@Override
	public Collection<PokemonStats> filter(Collection<PokemonStats> pokemonStatsCollection, IFactorsModel factorsModel) {

		Collection<PokemonStats> result = new ArrayList<>();

		Long minHarm = Long.MAX_VALUE;
		for (PokemonStats pokemonStats : pokemonStatsCollection) {
			long harm = harmCalculator.calculate(pokemonStats, factorsModel);
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
