package application;

public class PokemonDefensiveStatsTableRowBuilder {

	public Object[] buildRow(PokemonStats pokemonStats) {
		Object[] row = new Object[3];
		EVDistribution distribution = pokemonStats.getDistribution();
		row[0] = distribution.getHP();
		row[1] = distribution.getDef();
		row[2] = distribution.getSpDef();
		return row;
	}
}
