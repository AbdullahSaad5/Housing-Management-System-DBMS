package frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import backend.SqlConnection;

@SuppressWarnings("serial")
public class UserSettings extends JPanel implements ActionListener {

	JButton updateProfile, deleteAccount;
	public UserSettings() {
		setLayout(null);
		setBounds(0, 0, 1280, 650);
		JLabel lblNewLabel = new JLabel("User Profile Controls");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 70, 1260, 40);
		add(lblNewLabel);

		updateProfile = new JButton("Update Profile");
		updateProfile.setBackground(new Color(56, 142, 60));
		updateProfile.setForeground(Color.white);
		updateProfile.setFont(new Font("SansSerif", Font.BOLD, 15));
		updateProfile.setBounds(538, 222, 200, 50);
		updateProfile.setFocusable(false);
		updateProfile.addActionListener(this);
		add(updateProfile);

		deleteAccount = new JButton("Delete Account");
		deleteAccount.setBackground(new Color(239, 108, 0));
		deleteAccount.setForeground(Color.white);
		deleteAccount.setFont(new Font("SansSerif", Font.BOLD, 15));
		deleteAccount.setBounds(538, 391, 200, 50);
		deleteAccount.setFocusable(false);
		deleteAccount.addActionListener(this);
		add(deleteAccount);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == deleteAccount) {
			JPasswordField getPassword = new JPasswordField();
			int choice = JOptionPane.showConfirmDialog(null, getPassword, "Enter Password to Confirm Deletion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION);
			if(choice == 1) {
				try {
					PreparedStatement verifyPassword = SqlConnection.connectToDatabase().prepareStatement("Select * from account where username = ? and password = ?");
					verifyPassword.setString(1, Login.currentUserID);
					verifyPassword.setString(2, getPassword.getText());
					int count = verifyPassword.executeUpdate();

					if(count == 1) {
						PreparedStatement deleteAccount = SqlConnection.connectToDatabase().prepareStatement("delete from account where username = ?");
						deleteAccount.setString(1, Login.currentUserID);
						deleteAccount.executeUpdate();
						JOptionPane.showMessageDialog(null, "Account deleted!");
						Template.mainFrame.setPreferredSize(new Dimension(460, 640));
						Template.changePanel(new Login());
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		}
		else if(e.getSource() == updateProfile) {
			UserDashboard.replaceContentPanel(new UpdateUserSettings());
		}
	}
}
