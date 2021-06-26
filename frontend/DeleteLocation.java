package frontend;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import backend.SqlConnection;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.management.Query;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class DeleteLocation extends JPanel implements ActionListener {

	JComboBox<String> selectType;
	private JTextField keywordField;
	private JTextField idField;
	private JScrollPane mainWindow;
	private JTextArea textArea;
	private JButton search;

	public DeleteLocation() {
		setLayout(null);

		JLabel mainLabel = new JLabel("Delete Locations");
		mainLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setBounds(10, 42, 1260, 30);
		add(mainLabel);

		String types[] = { "Province", "City", "Location", "Colony" };
		selectType = new JComboBox<String>(types);
		selectType.setSelectedIndex(3);
		selectType.addActionListener(this);
		selectType.setBounds(570, 150, 140, 24);
		add(selectType);

		JLabel locationType = new JLabel("Location Type");
		locationType.setFont(new Font("SansSerif", Font.BOLD, 14));
		locationType.setBounds(390, 150, 130, 20);
		add(locationType);

		JLabel keyword = new JLabel("Keyword:");
		keyword.setFont(new Font("SansSerif", Font.BOLD, 14));
		keyword.setBounds(390, 193, 130, 20);
		add(keyword);

		keywordField = new JTextField();
		keywordField.setBounds(570, 195, 140, 20);
		add(keywordField);
		keywordField.setColumns(10);

		JLabel locationID = new JLabel("Location ID:");
		locationID.setFont(new Font("SansSerif", Font.BOLD, 14));
		locationID.setBounds(390, 238, 130, 20);
		add(locationID);

		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(570, 240, 140, 20);
		add(idField);

		JButton delete = new JButton("Delete");
		delete.setBounds(790, 239, 100, 23);
		add(delete);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(390, 317, 500, 322);

		mainWindow = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainWindow.getVerticalScrollBar().setBackground(new Color(0x1E214A));
		mainWindow.getBackground().darker();
		mainWindow.setBounds(390, 317, 500, 322);
		add(mainWindow);

		search = new JButton("Search");
		search.setBounds(790, 194, 100, 23);
		add(search);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == selectType) {
			String query;
			String text = "Location ID\t\t Location Name\n\n";
			if (selectType.getSelectedIndex() == 0) {
				try {
					query = "select * from province order by province_id";
					PreparedStatement getProvinces = SqlConnection.connectToDatabase().prepareStatement(query);
					ResultSet result = getProvinces.executeQuery();
					while (result.next()) {
						text += result.getInt(1) + "\t\t" + result.getString(2) + "\n";
					}
					textArea.setText(text);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
			if (selectType.getSelectedIndex() == 1) {
				try {
					query = "select city_id, city_name, province_name from city natural join province order by city_id";
					PreparedStatement getProvinces = SqlConnection.connectToDatabase().prepareStatement(query);
					ResultSet result = getProvinces.executeQuery();
					while (result.next()) {
						text += result.getInt(1) + "\t\t" + result.getString(2) + ", " + result.getString(3) + "\n";
					}
					textArea.setText(text);

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
			if (selectType.getSelectedIndex() == 2) {
				try {
					query = "select location_id, location_name, city_name, province_name from location natural join "
							+ "city natural join province order by location_id";
					PreparedStatement getProvinces = SqlConnection.connectToDatabase().prepareStatement(query);
					ResultSet result = getProvinces.executeQuery();
					while (result.next()) {
						text += result.getInt(1) + "\t\t" + result.getString(2) + ", " + result.getString(3) + ", " + result.getString(4) + "\n";
					}
					textArea.setText(text);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
			if (selectType.getSelectedIndex() == 3) {
				try {
					query = "select colony_id, colony_name, location_name, city_name, province_name from "
							+ "colony natural join location natural join city natural join province order by colony_id";
					PreparedStatement getProvinces = SqlConnection.connectToDatabase().prepareStatement(query);
					ResultSet result = getProvinces.executeQuery();
					while (result.next()) {
						text += result.getInt(1) + "\t\t" + result.getString(2) + ", " + result.getString(3) + ", " + result.getString(4) + ", " + result.getString(5) + "\n";
					}
					textArea.setText(text);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		}

	}
}
