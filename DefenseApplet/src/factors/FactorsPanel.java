package factors;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import application.ISpinnerListener;
import application.LabelledSpinner;

import com.google.common.eventbus.EventBus;

import factors.events.DefDenominatorChanged;
import factors.events.DefNumeratorChanged;
import factors.events.HPDenominatorChanged;
import factors.events.HPNumeratorChanged;
import factors.events.SpDefDenominatorChanged;
import factors.events.SpDefNumeratorChanged;

public class FactorsPanel extends JPanel implements ISpinnerListener {
	private static final long serialVersionUID = -6454373058554077717L;

	private EventBus eventBus;
	private LabelledSpinner hpNumerator;
	private LabelledSpinner hpDenominator;
	private LabelledSpinner defNumerator;
	private LabelledSpinner defDenominator;
	private LabelledSpinner spDefNumerator;
	private LabelledSpinner spDefDenominator;

	public FactorsPanel(EventBus eventBus) {
		this.eventBus = eventBus;

		setLayout(new GridLayout(1, 1));
		Border blackline = BorderFactory.createLineBorder(Color.black);
		TitledBorder border = BorderFactory.createTitledBorder(blackline, "Factors");
		setBorder(border);

		hpNumerator = new LabelledSpinner("HP:", 1, 0, 200, 1);
		hpDenominator = new LabelledSpinner("/", 1, 0, 200, 1);
		defNumerator = new LabelledSpinner("Def:", 1, 0, 200, 1);
		defDenominator = new LabelledSpinner("/", 1, 0, 200, 1);
		spDefNumerator = new LabelledSpinner("SpDef:", 1, 0, 200, 1);
		spDefDenominator = new LabelledSpinner("/", 1, 0, 200, 1);

		hpNumerator.setListener(this);
		hpDenominator.setListener(this);
		defNumerator.setListener(this);
		defDenominator.setListener(this);
		spDefNumerator.setListener(this);
		spDefDenominator.setListener(this);

		JPanel factorsPanel = new JPanel();
		factorsPanel.setLayout(new GridLayout(3, 2));
		factorsPanel.add(hpNumerator);
		factorsPanel.add(hpDenominator);
		factorsPanel.add(defNumerator);
		factorsPanel.add(defDenominator);
		factorsPanel.add(spDefNumerator);
		factorsPanel.add(spDefDenominator);
		add(factorsPanel);
	}

	@Override
	public void valueChanged(LabelledSpinner source, int value) {
		if (source == hpNumerator) {
			eventBus.post(new HPNumeratorChanged(value));
		} else if (source == hpDenominator) {
			eventBus.post(new HPDenominatorChanged(value));
		} else if (source == defNumerator) {
			eventBus.post(new DefNumeratorChanged(value));
		} else if (source == defDenominator) {
			eventBus.post(new DefDenominatorChanged(value));
		} else if (source == spDefNumerator) {
			eventBus.post(new SpDefNumeratorChanged(value));
		} else if (source == spDefDenominator) {
			eventBus.post(new SpDefDenominatorChanged(value));
		}
	}

}
