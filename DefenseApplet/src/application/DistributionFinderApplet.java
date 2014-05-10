package application;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import restrictions.RestrictionsMVPFactory;
import factors.FactorsMVPFactory;

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
	private PokemonDefensiveStatsTableModel statsTableModel = new PokemonDefensiveStatsTableModel(rowBuilder);
	private JTable resultTable = new JTable(statsTableModel);
	private JScrollPane scrollPane = new JScrollPane(resultTable);

	private final FactorsMVPFactory factorsMVP = new FactorsMVPFactory();
	private final RestrictionsMVPFactory restrictionsMVP = new RestrictionsMVPFactory();

	@Override
	public void init() {
		setSize(640, 480);
		setLayout(new GridBagLayout());

		JPanel baseStatsPanel = new JPanel();
		Border blackline = BorderFactory.createLineBorder(Color.black);
		TitledBorder border = BorderFactory.createTitledBorder(blackline, "Base Stats");
		baseStatsPanel.setBorder(border);

		baseStatsPanel.setLayout(new GridLayout(3, 1));
		baseStatsPanel.add(hpStatPanel);
		baseStatsPanel.add(defStatPanel);
		baseStatsPanel.add(spDefStatPanel);

		GridBagConstraints baseStatsConstraints = new GridBagConstraints();
		baseStatsConstraints.gridx = 0;
		baseStatsConstraints.gridy = 0;
		baseStatsConstraints.weightx = .50;
		baseStatsConstraints.weighty = .10;
		baseStatsConstraints.anchor = GridBagConstraints.CENTER;
		add(baseStatsPanel, baseStatsConstraints);

		GridBagConstraints factorsConstraints = new GridBagConstraints();
		factorsConstraints.gridx = 1;
		factorsConstraints.gridy = 0;
		factorsConstraints.weightx = .50;
		factorsConstraints.weighty = .10;
		factorsConstraints.anchor = GridBagConstraints.CENTER;
		add(factorsMVP.getView(), factorsConstraints);

		GridBagConstraints restrictionsConstraints = new GridBagConstraints();
		restrictionsConstraints.gridx = 0;
		restrictionsConstraints.gridy = 1;
		restrictionsConstraints.gridwidth = 2;
		restrictionsConstraints.weighty = .20;
		restrictionsConstraints.fill = GridBagConstraints.BOTH;
		add(restrictionsMVP.getView(), restrictionsConstraints);

		calculateButton.addActionListener(this);
		GridBagConstraints buttonConstraints = new GridBagConstraints();
		buttonConstraints.gridx = 0;
		buttonConstraints.gridy = 2;
		buttonConstraints.gridwidth = 2;
		buttonConstraints.weighty = .10;
		buttonConstraints.fill = GridBagConstraints.BOTH;
		add(calculateButton, buttonConstraints);

		resultTable.setFillsViewportHeight(true);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints resultTableConstraints = new GridBagConstraints();
		resultTableConstraints.gridx = 0;
		resultTableConstraints.gridy = 3;
		resultTableConstraints.gridwidth = 2;
		resultTableConstraints.weighty = .60;
		resultTableConstraints.fill = GridBagConstraints.BOTH;
		add(scrollPane, resultTableConstraints);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		IBaseStats baseStats = new BaseStats(hpStatPanel.getStat(), defStatPanel.getStat(), spDefStatPanel.getStat());
		Set<PokemonStats> calculate = distributionFinder.calculate(baseStats, restrictionsMVP.getModel(),
				factorsMVP.getModel());

		statsTableModel.setPokemonStats(calculate);

		resultTable.repaint();
	}

}
