package application;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class BaseStatComponent extends JPanel implements ChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 146310106557898732L;
	
	private int baseStat = 100;

	private SpinnerNumberModel spinnerNumberModel;
	
	public BaseStatComponent(String label) {
		setLayout(new GridLayout(1,2));

		add(new JLabel(label));

		spinnerNumberModel = new SpinnerNumberModel(baseStat, 0, 255, 1);
		JSpinner jSpinner = new JSpinner(spinnerNumberModel);
		jSpinner.addChangeListener(this);
		add(jSpinner);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
          baseStat = (int) spinnerNumberModel.getValue();
	}
	
	public int getStat() { return baseStat; }
}
