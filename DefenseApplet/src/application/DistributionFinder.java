package application;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import restrictions.IRestrictionsModel;

public class DistributionFinder implements IDistributionFinder {

	private IEVDistributionFactory evDistributionFactory;
	private IPokemonStatsCollectionFactory pokemonStatsCollectionFactory;
	private IHarmAlgorithmFilter harmAlgFilter;

	DistributionFinder() {
		this(new EVDistributionFactory(), new PokemonStatsCollectionFactory(),
				new HarmAlgorithmFilter());
	}

	protected DistributionFinder(IEVDistributionFactory evDistributionFactory,
			IPokemonStatsCollectionFactory pokemonStatsCollectionFactory,
			IHarmAlgorithmFilter harmAlgFilter) {
		this.evDistributionFactory = evDistributionFactory;
		this.pokemonStatsCollectionFactory = pokemonStatsCollectionFactory;
		this.harmAlgFilter = harmAlgFilter;
	}

	@Override
	public Set<PokemonStats> calculate(IBaseStats baseStats,
			IRestrictionsModel restrictionsModel) {
		Set<EVDistribution> evDistributions = evDistributionFactory
				.getInitialCollection(restrictionsModel);
		Collection<PokemonStats> pokemonStats = pokemonStatsCollectionFactory
				.makeStatsCollection(evDistributions, baseStats);
		Collection<PokemonStats> filteredPokemonStats = harmAlgFilter
				.filter(pokemonStats);
		return new HashSet<PokemonStats>(filteredPokemonStats);
	}
}
