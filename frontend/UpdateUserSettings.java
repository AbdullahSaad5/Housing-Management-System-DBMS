package frontend;

import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import backend.SqlConnection;

@SuppressWarnings("serial")
public class UpdateUserSettings extends SignUp {

	/**
	 * Create the panel.
	 */
	public UpdateUserSettings() {
		setBounds(410, -100, 460, 740);
		remove(mainTitle);
		signupTitle.setText("Update User");
		getData(Login.currentUserID);
		remove(signupButton);

	}

	private void getData(String username) {
		PreparedStatement fetchData;
		try {
			fetchData = SqlConnection.connectToDatabase().prepareStatement(
					"select username, password, first_name, last_name, gender, CNIC, email_address, home_address, phone_number from users natural join account where username = ?");
			fetchData.setString(1, username);
			ResultSet result = SqlConnection.findResult(fetchData);
			result.next();
			usernameField.setText(result.getString(1));
			passwordField.setText(result.getString(2));
			firstnameField.setText(result.getString(3));
			lastnameField.setText(result.getString(4));
			CNIC_Field.setText(result.getString(6));
			emailField.setText(result.getString(7));
			homeField.setText(result.getString(8));
			phoneField.setText(result.getString(9));
			loginButton.setBounds(170, 670, 120, 25);
			loginButton.setText("Update Data");
			loginButton.addActionListener(this);

			String gender = result.getString(5);
			if (gender.equalsIgnoreCase("Male")) {
				genderBox.setSelectedIndex(0);
			} else {
				genderBox.setSelectedIndex(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@SuppressWarnings("deprecation")
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == loginButton) {
			if (usernameField.getText().length() != 0 && passwordField.getText().length() != 0
					&& firstnameField.getText().length() != 0 && lastnameField.getText().length() != 0
					&& CNIC_Field.getText().length() != 0 && homeField.getText().length() != 0
					&& phoneField.getText().length() != 0) {

				try {
					PreparedStatement updateAccountQuery = SqlConnection.connectToDatabase()
							.prepareStatement("update account set username = ?, password = ? where username = ?");

					PreparedStatement updateUserQuery = SqlConnection.connectToDatabase()
							.prepareStatement("update users set username = ?, first_name = ?, last_name = ?,"
									+ " gender = ?, CNIC = ?, email_address = ?, home_address = ?, phone_number = ? "
									+ "where username = ?");

					updateAccountQuery.setString(1, usernameField.getText());
					updateAccountQuery.setString(2, passwordField.getText());
					updateAccountQuery.setString(3, Login.currentUserID);

					updateUserQuery.setString(1, usernameField.getText());
					updateUserQuery.setString(2, firstnameField.getText());
					updateUserQuery.setString(3, lastnameField.getText());
					updateUserQuery.setString(4, genderBox.getSelectedItem().toString());
					updateUserQuery.setString(5, CNIC_Field.getText());
					updateUserQuery.setString(6, emailField.getText());
					updateUserQuery.setString(7, homeField.getText());
					updateUserQuery.setString(8, phoneField.getText());
					updateUserQuery.setString(9, Login.currentUserID);

					SqlConnection.alterResults(updateAccountQuery);
					SqlConnection.alterResults(updateUserQuery);
					JOptionPane.showMessageDialog(null, "Data Updated Successfully");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "An error occured while processing your information. Please try again!");
					e1.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Fill all the data fields!");
			}
		}
}

}
