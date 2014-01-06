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

	private PokemonDefensiveStatsTableModel tableModel;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		tableModel = new PokemonDefensiveStatsTableModel(rowBuilder);
	}

	@Test
	public void testColumnNamesTakenFromRowBuilder() {
		String[] expectedColumnNames = { "abc", "123", "xyz" };
		when(rowBuilder.getColumnName(0)).thenReturn(expectedColumnNames[0]);
		when(rowBuilder.getColumnName(1)).thenReturn(expectedColumnNames[1]);
		when(rowBuilder.getColumnName(2)).thenReturn(expectedColumnNames[2]);

		for (int c = 0; c < 3; ++c) {
			assertEquals(expectedColumnNames[c], tableModel.getColumnName(c));
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
		when(rowBuilder.getColumnCount()).thenReturn(3);

		statsCollection.add(pokemonStats);

		tableModel.setPokemonStats(statsCollection);

		for (int c = 0; c < 3; ++c) {
			assertEquals(row[c], tableModel.getValueAt(0, c));
		}
	}

	@Test
	public void testMultipleRowsAreBuiltFromPokemonStats() {
		int columns = 3;
		PokemonStats pokemonStats1 = mock(PokemonStats.class);
		PokemonStats pokemonStats2 = mock(PokemonStats.class);
		PokemonStats pokemonStats3 = mock(PokemonStats.class);
		Object[] row1 = { 3, 2, 1 };
		Object[] row2 = { 3, 2, 1 };
		Object[] row3 = { 0, 4, 9 };
		when(rowBuilder.buildRow(pokemonStats1)).thenReturn(row1);
		when(rowBuilder.buildRow(pokemonStats2)).thenReturn(row2);
		when(rowBuilder.buildRow(pokemonStats3)).thenReturn(row3);
		when(rowBuilder.getColumnCount()).thenReturn(3);
		List<PokemonStats> statsCollection = Arrays.asList(pokemonStats1,
				pokemonStats2, pokemonStats3);

		tableModel.setPokemonStats(statsCollection);

		assertEquals(columns, tableModel.getColumnCount());
		Object[][] grid = { row1, row2, row3 };
		for (int r = 0; r < 3; ++r) {
			for (int c = 0; c < columns; ++c) {
				assertEquals(grid[r][c], tableModel.getValueAt(r, c));
			}
		}
	}

	@Test
	public void testColumnWidthIsDecidedByRowBuilder() {
		int columns = 4;
		PokemonStats pokemonStats1 = mock(PokemonStats.class);
		PokemonStats pokemonStats2 = mock(PokemonStats.class);
		Object[] row1 = { 9, 6, 3, 2 };
		Object[] row2 = { 8, 7, 4, 1 };
		when(rowBuilder.buildRow(pokemonStats1)).thenReturn(row1);
		when(rowBuilder.buildRow(pokemonStats2)).thenReturn(row2);
		when(rowBuilder.getColumnCount()).thenReturn(columns);
		List<PokemonStats> statsCollection = Arrays.asList(pokemonStats1,
				pokemonStats2);

		tableModel.setPokemonStats(statsCollection);

		assertEquals(columns, tableModel.getColumnCount());
		Object[][] grid = { row1, row2 };
		for (int r = 0; r < 2; ++r) {
			for (int c = 0; c < columns; ++c) {
				assertEquals(grid[r][c], tableModel.getValueAt(r, c));
			}
		}
	}
}
