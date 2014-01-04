package application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PokemonStatsCollectionFactory implements
		IPokemonStatsCollectionFactory {

	private PokemonStatsFactory pokemonStatsFactory;

	public PokemonStatsCollectionFactory() {
		this(new PokemonStatsFactory());
	}

	protected PokemonStatsCollectionFactory(
			PokemonStatsFactory pokemonStatsFactory) {
		this.pokemonStatsFactory = pokemonStatsFactory;
	}

	@Override
	public List<PokemonStats> makeStatsCollection(
			Collection<EVDistribution> evDistributionCollection,
			IBaseStats baseStats) {

		List<PokemonStats> pokemonStatsSet = new ArrayList<>(
				evDistributionCollection.size());
		for (EVDistribution evDistribution : evDistributionCollection) {
			pokemonStatsSet.add(pokemonStatsFactory.makeStats(evDistribution,
					baseStats));
		}
		return pokemonStatsSet;
	}

}
