package application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class PokemonDefensiveStatsTableModel extends AbstractTableModel
		implements TableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3768891509074901217L;

	private PokemonDefensiveStatsTableRowBuilder rowBuilder;
	private Object[][] data = new Object[0][0];

	public PokemonDefensiveStatsTableModel(
			PokemonDefensiveStatsTableRowBuilder rowBuilder) {
		this.rowBuilder = rowBuilder;
	}

	@Override
	public int getColumnCount() {
		return rowBuilder.getColumnCount();
	}

	@Override
	public String getColumnName(int arg0) {
		return rowBuilder.getColumnName(arg0);
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
		data = new Object[rowCount][getColumnCount()];
		List<PokemonStats> myStatsTableModel = new ArrayList<>(statsTableModel);

		for (int y = 0; y < rowCount; ++y) {
			data[y] = rowBuilder.buildRow(myStatsTableModel.get(y));
		}
	}
}
