package restrictions;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import application.ISpinnerListener;
import application.LabelledSpinner;
import application.Nature;
import application.Nature.Possibility;

public class RestrictionsPanel extends JPanel implements ActionListener, ItemListener, ISpinnerListener {
	private static final long serialVersionUID = 2875517493533937867L;

	private IRestrictionsViewListener viewListener;

	private JButton allButton = new JButton("Select All");
	private JButton noneButton = new JButton("Deselect All");

	private final Map<JCheckBox, Nature> checkboxToNatureMap = new LinkedHashMap<>();

	private LabelledSpinner maxEVSpinner;
	private LabelledSpinner minEVSpinner;

	public RestrictionsPanel() {
		setLayout(new GridBagLayout());
		Border blackline = BorderFactory.createLineBorder(Color.black);
		TitledBorder border = BorderFactory.createTitledBorder(blackline, "Restrictions");
		setBorder(border);

		JPanel limitsPanel = new JPanel();
		limitsPanel.setLayout(new GridLayout(2, 1));
		maxEVSpinner = new LabelledSpinner("Max Sum:", 508, 0, 508, 4);
		minEVSpinner = new LabelledSpinner("Min Sum:", 508, 0, 508, 4);
		maxEVSpinner.setListener(this);
		minEVSpinner.setListener(this);
		limitsPanel.add(maxEVSpinner);
		limitsPanel.add(minEVSpinner);

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(2, 1));
		allButton.addActionListener(this);
		noneButton.addActionListener(this);
		buttons.add(allButton);
		buttons.add(noneButton);

		JPanel natureCheckboxes = new JPanel();
		natureCheckboxes.setLayout(new GridBagLayout());

		GridBagConstraints checkboxesConstraints = new GridBagConstraints();
		checkboxesConstraints.weightx = .25;
		checkboxesConstraints.weighty = .5;
		checkboxesConstraints.gridx = 0;
		checkboxesConstraints.gridy = 0;
		for (Possibility possibility : Nature.Possibility.values()) {
			Nature nature = possibility.getNature();
			JCheckBox checkbox = new JCheckBox(nature.toString());
			checkboxToNatureMap.put(checkbox, nature);
			checkbox.addItemListener(this);
			natureCheckboxes.add(checkbox, checkboxesConstraints);

			if (checkboxesConstraints.gridx == 3) {
				checkboxesConstraints.gridx = 1;
				checkboxesConstraints.gridy = 1;
			} else {
				checkboxesConstraints.gridx++;
			}
		}

		GridBagConstraints limitsConstraints = new GridBagConstraints();
		limitsConstraints.gridx = 0;
		limitsConstraints.gridy = 0;
		limitsConstraints.weighty = .50;
		add(limitsPanel, limitsConstraints);

		GridBagConstraints buttonConstraints = new GridBagConstraints();
		buttonConstraints.gridx = 0;
		buttonConstraints.gridy = 1;
		buttonConstraints.weightx = .20;
		buttonConstraints.weighty = .50;
		add(buttons, buttonConstraints);

		GridBagConstraints natureConstraints = new GridBagConstraints();
		natureConstraints.gridx = 1;
		natureConstraints.gridy = 1;
		natureConstraints.weightx = .80;
		natureConstraints.weighty = .50;
		natureConstraints.gridwidth = 2;
		natureConstraints.fill = GridBagConstraints.BOTH;
		natureConstraints.anchor = GridBagConstraints.LINE_START;
		add(natureCheckboxes, natureConstraints);
	}

	public void setViewListener(IRestrictionsViewListener viewListener) {
		this.viewListener = viewListener;
	}

	public void setSelectedNatures(Collection<Nature> natures) {
		for (Entry<JCheckBox, Nature> entry : checkboxToNatureMap.entrySet()) {
			JCheckBox checkbox = entry.getKey();
			Nature checkboxNature = entry.getValue();
			checkbox.setSelected(natures.contains(checkboxNature));
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();

		if (source == allButton) {
			selectAllCheckboxes();
		} else if (source == noneButton) {
			deselectAllCheckboxes();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		Nature changedNature = checkboxToNatureMap.get(arg0.getSource());

		if (arg0.getStateChange() == ItemEvent.SELECTED) {
			viewListener.natureAdded(changedNature);
		} else if (arg0.getStateChange() == ItemEvent.DESELECTED) {
			viewListener.natureRemoved(changedNature);
		}
	}

	@Override
	public void valueChanged(LabelledSpinner source, int value) {
		if (source == maxEVSpinner) {
			viewListener.maxEVsChanged(value);
		} else if (source == minEVSpinner) {
			viewListener.minEVsChanged(value);
		}
	}

	private void selectAllCheckboxes() {
		setAllCheckboxesTo(true);
	}

	private void deselectAllCheckboxes() {
		setAllCheckboxesTo(false);
	}

	private void setAllCheckboxesTo(boolean state) {
		for (JCheckBox checkbox : checkboxToNatureMap.keySet()) {
			checkbox.setSelected(state);
		}
	}
}
