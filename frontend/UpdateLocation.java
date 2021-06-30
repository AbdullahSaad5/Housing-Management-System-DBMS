package frontend;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import backend.SqlConnection;

public class UpdateLocation extends DeleteLocation {
	JTextField newName;

	public UpdateLocation() {
		mainLabel.setText("Update Locations");
		delete.setText("Update");
		delete.setBounds(790, 285, 100, 23);

		JLabel nameLabel = new JLabel("Updated Name:");
		nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		nameLabel.setBounds(390, 283, 130, 20);
		add(nameLabel);

		newName = new JTextField();
		newName.setColumns(10);
		newName.setBounds(570, 285, 140, 20);
		add(newName);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == delete) {
			if (!idField.getText().isBlank()) {
				if (selectType.getSelectedIndex() == 0) {
					try {
						PreparedStatement updateName = SqlConnection.connectToDatabase()
								.prepareStatement("update province set province_name = ? where province_id = ?");
						updateName.setString(1, newName.getText());
						updateName.setInt(2, Integer.parseInt(idField.getText()));
						updateName.executeUpdate();
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Invalid ID entered. Please Try Again");
					}
				} else if (selectType.getSelectedIndex() == 1) {
					try {
						PreparedStatement updateName = SqlConnection.connectToDatabase()
								.prepareStatement("update city set city_name = ? where city_id = ?");
						updateName.setString(1, newName.getText());
						updateName.setInt(2, Integer.parseInt(idField.getText()));
						updateName.executeUpdate();
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Invalid ID entered. Please Try Again");
					}
				} else if (selectType.getSelectedIndex() == 2) {
					try {
						PreparedStatement updateName = SqlConnection.connectToDatabase()
								.prepareStatement("update location set location_name = ? where location_id = ?");
						updateName.setString(1, newName.getText());
						updateName.setInt(2, Integer.parseInt(idField.getText()));
						updateName.executeUpdate();
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Invalid ID entered. Please Try Again");
					}
				} else if (selectType.getSelectedIndex() == 3) {
					try {
						PreparedStatement updateName = SqlConnection.connectToDatabase()
								.prepareStatement("update colony set colony_name = ? where colony_id = ?");
						updateName.setString(1, newName.getText());
						updateName.setInt(2, Integer.parseInt(idField.getText()));
						updateName.executeUpdate();
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Invalid ID entered. Please Try Again");
					}
				}

			} else {
				JOptionPane.showMessageDialog(null, "No ID entered.");
			}
		} else {
			super.actionPerformed(e);
		}
	}
}
