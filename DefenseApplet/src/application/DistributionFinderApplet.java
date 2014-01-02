package application;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;

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
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		distributionFinder.calculate(hpStatPanel.getStat(),
				defStatPanel.getStat(), spDefStatPanel.getStat());

	}

}
