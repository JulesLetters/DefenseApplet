package application;

public class PokemonDefensiveStatsTableRowBuilder {

	private String[] columnNames = { "EVs:HP", "EVs:Def", "EVs:SpDef",
			"Nature", "HP", "Def", "SpDef" };

	public Object[] buildRow(PokemonStats pokemonStats) {
		Object[] row = new Object[getColumnCount()];
		EVDistribution distribution = pokemonStats.getDistribution();
		row[0] = distribution.getHP();
		row[1] = distribution.getDef();
		row[2] = distribution.getSpDef();
		row[3] = distribution.getNature();
		row[4] = pokemonStats.getHP();
		row[5] = pokemonStats.getDef();
		row[6] = pokemonStats.getSpDef();
		return row;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int i) {
		return columnNames[i];
	}
}
