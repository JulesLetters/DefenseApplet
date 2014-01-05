package application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class PokemonDefensiveStatsTableModel extends AbstractTableModel
		implements TableModel {

	private static final int COLUMN_COUNT = 3;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3768891509074901217L;

	String[] columnNames = { "EV:HP", "EV:Def", "EV:SpDef" };

	private PokemonDefensiveStatsTableRowBuilder rowBuilder;

	private Object[][] data = new Object[0][0];

	public PokemonDefensiveStatsTableModel(
			PokemonDefensiveStatsTableRowBuilder rowBuilder) {
		this.rowBuilder = rowBuilder;
	}

	@Override
	public int getColumnCount() {
		return COLUMN_COUNT;
	}

	@Override
	public String getColumnName(int arg0) {
		return columnNames[arg0];
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return data[arg0][arg1];
	}

	public void setPokemonStats(Collection<PokemonStats> statsTableModel) {
		int rowCount = statsTableModel.size();
		data = new Object[rowCount][COLUMN_COUNT];
		List<PokemonStats> myStatsTableModel = new ArrayList<>(statsTableModel);

		for (int y = 0; y < rowCount; ++y) {
			data[y] = rowBuilder.buildRow(myStatsTableModel.get(y));
		}
	}
}
