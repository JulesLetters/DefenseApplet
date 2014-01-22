package application;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LabelledSpinner extends JPanel implements ChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 146310106557898732L;

	private SpinnerNumberModel spinnerNumberModel;

	private ISpinnerListener listener;

	public LabelledSpinner(String label, int value, int min, int max, int step) {
		setLayout(new GridLayout(1, 2));

		add(new JLabel(label));

		spinnerNumberModel = new SpinnerNumberModel(value, min, max, step);
		JSpinner jSpinner = new JSpinner(spinnerNumberModel);
		jSpinner.addChangeListener(this);
		add(jSpinner);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		listener.valueChanged(this, (int) spinnerNumberModel.getValue());
	}

	public void setListener(ISpinnerListener listener) {
		this.listener = listener;
	}
}
