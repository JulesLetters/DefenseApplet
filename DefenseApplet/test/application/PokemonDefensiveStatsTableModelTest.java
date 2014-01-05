package application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PokemonDefensiveStatsTableModelTest {

	@Mock
	private PokemonDefensiveStatsTableRowBuilder rowBuilder;

	private static final int TABLE_COLUMNS = 3;
	private String[] columnNames = { "EV:HP", "EV:Def", "EV:SpDef" };
	private PokemonDefensiveStatsTableModel tableModel;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		tableModel = new PokemonDefensiveStatsTableModel(rowBuilder);
	}

	@Test
	public void testColumnNames() {
		assertEquals(TABLE_COLUMNS, tableModel.getColumnCount());
		for (int c = 0; c < TABLE_COLUMNS; ++c) {
			assertEquals(columnNames[c], tableModel.getColumnName(c));
		}
	}

	@Test
	public void testGetRowCountMatchesItemsInSet() {
		assertEquals(0, tableModel.getRowCount());
		Set<PokemonStats> stats = new HashSet<>();
		tableModel.setPokemonStats(stats);
		assertEquals(0, tableModel.getRowCount());

		stats.add(mock(PokemonStats.class));
		tableModel.setPokemonStats(stats);
		assertEquals(1, tableModel.getRowCount());

		stats.add(mock(PokemonStats.class));
		stats.add(mock(PokemonStats.class));
		tableModel.setPokemonStats(stats);
		assertEquals(3, tableModel.getRowCount());
	}

	@Test
	public void testSingleRowIsBuiltFromPokemonStats() {
		Set<PokemonStats> statsCollection = new HashSet<>();
		PokemonStats pokemonStats = mock(PokemonStats.class);

		Object[] row = { 1, 2, 3 };
		when(rowBuilder.buildRow(pokemonStats)).thenReturn(row);

		statsCollection.add(pokemonStats);

		tableModel.setPokemonStats(statsCollection);

		for (int c = 0; c < TABLE_COLUMNS; ++c) {
			assertEquals(row[c], tableModel.getValueAt(0, c));
		}
	}

	@Test
	public void testMultipleRowsAreBuiltFromPokemonStats() {
		PokemonStats pokemonStats1 = mock(PokemonStats.class);
		PokemonStats pokemonStats2 = mock(PokemonStats.class);
		PokemonStats pokemonStats3 = mock(PokemonStats.class);
		Object[] row1 = { 3, 2, 1 };
		Object[] row2 = { 3, 2, 1 };
		Object[] row3 = { 0, 4, 9 };
		when(rowBuilder.buildRow(pokemonStats1)).thenReturn(row1);
		when(rowBuilder.buildRow(pokemonStats2)).thenReturn(row2);
		when(rowBuilder.buildRow(pokemonStats3)).thenReturn(row3);
		List<PokemonStats> statsCollection = Arrays.asList(pokemonStats1,
				pokemonStats2, pokemonStats3);

		tableModel.setPokemonStats(statsCollection);

		Object[][] grid = { row1, row2, row3 };
		for (int r = 0; r < 3; ++r) {
			for (int c = 0; c < TABLE_COLUMNS; ++c) {
				assertEquals(grid[r][c], tableModel.getValueAt(r, c));
			}
		}
	}

}
