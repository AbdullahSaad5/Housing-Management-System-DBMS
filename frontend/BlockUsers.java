package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class BlockUsers extends JPanel implements ActionListener {

	/**
	 * Create the panel.
	 */

	private JComboBox<String> selectType;
	public BlockUsers() {
		setBackground(Color.WHITE);
		setLayout(null);


		String types[] = { "Unblocked", "Blocked"};
		selectType = new JComboBox<>(types);
		selectType.setSelectedIndex(0);
		selectType.addActionListener(this);
		selectType.setBounds(670, 50, 130, 24);
		add(selectType);

		JLabel typeLabel = new JLabel("Select Type");
		typeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		typeLabel.setBounds(483, 50, 130, 20);
		add(typeLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(50, 153, 300, 150);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(10, 15, 280, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Original Name");
		lblNewLabel_1.setBounds(10, 45, 280, 14);
		panel.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Block");
		btnNewButton.setBounds(100, 110, 100, 23);
		panel.add(btnNewButton);

		JLabel lblNewLabel_1_1 = new JLabel("Contact Number");
		lblNewLabel_1_1.setBounds(10, 75, 280, 14);
		panel.add(lblNewLabel_1_1);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
