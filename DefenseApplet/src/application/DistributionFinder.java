package application;

import java.util.HashSet;
import java.util.Set;

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

	@Override
	public Set<PokemonStats> calculate(IBaseStats baseStats) {
		Set<EVDistribution> initialCollection = evDistributionFactory
				.getInitialCollection();
		return new HashSet<PokemonStats>(
				pokemonStatsCollectionFactory.makeStatsCollection(
						initialCollection, baseStats));
	}
}
