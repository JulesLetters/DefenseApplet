package application;

import java.util.Set;

import application.test.IPokemonStatsCollectionFactory;
import application.test.PokemonStats;

public class DistributionFinder implements IDistributionFinder {

	private IEVDistributionFactory evDistributionFactory;
	private IPokemonStatsCollectionFactory pokemonStatsCollectionFactory;

	DistributionFinder() {
		this(new EVDistributionFactory(), new PokemonStatsCollectionFactory());
	}

	public DistributionFinder(IEVDistributionFactory evDistributionFactory,
			IPokemonStatsCollectionFactory pokemonStatsCollectionFactory) {
		this.evDistributionFactory = evDistributionFactory;
		this.pokemonStatsCollectionFactory = pokemonStatsCollectionFactory;
	}

	public Set<PokemonStats> calculate() {
		Set<EVDistribution> initialCollection = evDistributionFactory
				.getInitialCollection();
		return pokemonStatsCollectionFactory
				.makeStatsCollection(initialCollection);
	}
}
