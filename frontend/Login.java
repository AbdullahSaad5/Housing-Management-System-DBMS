package frontend;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import backend.SqlConnection;

import java.awt.event.*;

public class Login extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton, signupButton, showPassword;
	private char defaultChar;
	private JCheckBox chckbxAdmin;
	public static String currentUserID;

	public Login() {

		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		setPreferredSize(new Dimension(460, 640));

		JLabel mainTitle = new JLabel("   Housing Management System");
		mainTitle.setHorizontalAlignment(SwingConstants.CENTER);
		mainTitle.setIcon(new ImageIcon(Login.class.getResource("/images/house.png")));
		mainTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
		mainTitle.setBounds(10, 32, 440, 66);
		add(mainTitle);

		JLabel loginTitle = new JLabel("Login Form");
		loginTitle.setLabelFor(loginTitle);
		loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
		loginTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		loginTitle.setBounds(10, 150, 440, 31);
		add(loginTitle);

		JLabel usernameTitle = new JLabel("Username");
		usernameTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
		usernameTitle.setBounds(119, 244, 89, 14);
		add(usernameTitle);

		JLabel passwordTitle = new JLabel("Password");
		passwordTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
		passwordTitle.setBounds(119, 324, 75, 14);
		add(passwordTitle);

		usernameField = new JTextField();
		usernameTitle.setLabelFor(usernameField);
		usernameField.setBounds(119, 279, 210, 20);
		add(usernameField);
		usernameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordTitle.setLabelFor(passwordField);
		passwordField.setBounds(119, 349, 210, 20);
		add(passwordField);

		loginButton = new JButton("Login");
		loginButton.addMouseListener(this);
		loginButton.setBounds(119, 476, 89, 23);
		add(loginButton);
		loginButton.setFocusable(false);

		signupButton = new JButton("Sign Up");
		signupButton.addMouseListener(this);
		signupButton.setBounds(240, 476, 89, 23);
		add(signupButton);
		signupButton.setFocusable(false);

		showPassword = new JButton("");
		showPassword.setIcon(new ImageIcon(Login.class.getResource("/images/hide-password.png")));
		showPassword.setBounds(364, 349, 28, 23);
		add(showPassword);
		showPassword.addMouseListener(this);
		showPassword.setBackground(null);
		showPassword.setForeground(null);
		showPassword.setFocusable(false);
		showPassword.setBorder(null);

		chckbxAdmin = new JCheckBox("  Admin");
		chckbxAdmin.setFocusable(false);
		chckbxAdmin.setBackground(Color.WHITE);
		chckbxAdmin.setBounds(115, 407, 129, 23);
		add(chckbxAdmin);

		setVisible(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == showPassword) {
			defaultChar = passwordField.getEchoChar();
			passwordField.setEchoChar((char) 0);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		passwordField.setEchoChar(defaultChar);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == signupButton) {
			Template.changePanel(new SignUp());
		} else if (e.getSource() == loginButton) {
			if (usernameField.getText().length() != 0 && passwordField.getText().length() != 0) {
				try {

					String query = "";
					if (chckbxAdmin.isSelected()) {
						query = "select * from account where username = ? and password = ? and user_type = 'admin'";
					} else {
						query = "select * from account where username = ? and password = ? and user_type = 'user'";
					}
					PreparedStatement checkCredsQuery = SqlConnection.connectToDatabase().prepareStatement(query);
					checkCredsQuery.setString(1, usernameField.getText());
					checkCredsQuery.setString(2, passwordField.getText());

					if (SqlConnection.alterResults(checkCredsQuery) == 0) {
						JOptionPane.showMessageDialog(null, "Invalid Username or Password!");
					} else {
						ResultSet result = SqlConnection.findResult(checkCredsQuery);
						String blocked_status = "Y";
						while (result.next()) {
							blocked_status = result.getString(3);
						}

						if (blocked_status.equals("N")) {
							if (chckbxAdmin.isSelected()) {
								Template.changePanel(new AdminControlPanel());

							} else {
								currentUserID = usernameField.getText();
								Template.changePanel(new UserDashboard());
							}
						} else {
							JOptionPane.showMessageDialog(null, "Your Account has been blocked by the Admins!");

						}
					}
				}

				catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Fill all the data fields!");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == showPassword) {
			showPassword.setIcon(new ImageIcon(Login.class.getResource("/images/show-password1.png")));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == showPassword) {
			showPassword.setIcon(new ImageIcon(Login.class.getResource("/images/hide-password.png")));
		}
	}
}
