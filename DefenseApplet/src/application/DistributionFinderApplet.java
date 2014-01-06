package application;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class DistributionFinderApplet extends JApplet implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6828582244444202513L;

	private final IDistributionFinder distributionFinder = new DistributionFinder();

	private BaseStatComponent hpStatPanel = new BaseStatComponent("HP:");
	private BaseStatComponent defStatPanel = new BaseStatComponent("Def:");
	private BaseStatComponent spDefStatPanel = new BaseStatComponent("SpDef:");
	private JButton calculateButton = new JButton("Calculate EVs");

	private PokemonDefensiveStatsTableRowBuilder rowBuilder = new PokemonDefensiveStatsTableRowBuilder();
	private PokemonDefensiveStatsTableModel statsTableModel = new PokemonDefensiveStatsTableModel(
			rowBuilder);
	private JTable table = new JTable(statsTableModel);
	private JScrollPane scrollPane = new JScrollPane(table);

	@Override
	public void init() {
		setLayout(new GridLayout(3, 1));

		JPanel baseStatsPanel = new JPanel();
		baseStatsPanel.setLayout(new GridLayout(3, 1));
		baseStatsPanel.add(hpStatPanel);
		baseStatsPanel.add(defStatPanel);
		baseStatsPanel.add(spDefStatPanel);
		add(baseStatsPanel);

		calculateButton.addActionListener(this);
		add(calculateButton);

		table.setFillsViewportHeight(true);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		IBaseStats baseStats = new BaseStats(hpStatPanel.getStat(),
				defStatPanel.getStat(), spDefStatPanel.getStat());
		Set<PokemonStats> calculate = distributionFinder.calculate(baseStats);

		statsTableModel.setPokemonStats(calculate);

		table.repaint();
	}

}
