package frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import backend.SqlConnection;
import backend.Utilities;

public class SignUp extends JPanel implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;
	protected JPasswordField passwordField;
	protected JTextField firstnameField, usernameField, lastnameField, CNIC_Field, emailField, homeField, phoneField;
	protected JLabel mainTitle, signupTitle;
	protected JButton signupButton, loginButton;
	JComboBox<String> genderBox;

	public SignUp() {
		setBackground(new Color(255, 255, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setPreferredSize(new Dimension(460, 640));
		setLayout(null);

		mainTitle = new JLabel("   Housing Management System");
		mainTitle.setBounds(10, 32, 440, 66);
		mainTitle.setHorizontalAlignment(SwingConstants.CENTER);
		mainTitle.setIcon(new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/house.png"))));
		mainTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
		add(mainTitle);

		signupTitle = new JLabel("SignUp Form");
		signupTitle.setBounds(10, 121, 440, 31);
		signupTitle.setLabelFor(signupTitle);
		signupTitle.setHorizontalAlignment(SwingConstants.CENTER);
		signupTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
		add(signupTitle);

		JLabel usernameTitle = new JLabel("Username");
		usernameTitle.setBounds(112, 183, 89, 14);
		usernameTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(usernameTitle);

		JLabel passwordTitle = new JLabel("Password");
		passwordTitle.setBounds(260, 183, 75, 14);
		passwordTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(passwordTitle);

		JLabel firstnameTitle = new JLabel("First Name");
		firstnameTitle.setBounds(112, 243, 106, 14);
		firstnameTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
		usernameTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(firstnameTitle);

		JLabel lastnameTitle = new JLabel("Last Name");
		lastnameTitle.setBounds(260, 243, 89, 14);
		lastnameTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
		passwordTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(lastnameTitle);

		JLabel CNIC_Title = new JLabel("CNIC");
		CNIC_Title.setBounds(112, 360, 210, 14);
		CNIC_Title.setFont(new Font("SansSerif", Font.BOLD, 13));
		CNIC_Title.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(CNIC_Title);

		JLabel emailTitle = new JLabel("Email Address");
		emailTitle.setBounds(112, 420, 210, 14);
		emailTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
		usernameTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(emailTitle);

		JLabel homeTitle = new JLabel("Home Address");
		homeTitle.setBounds(112, 480, 210, 14);
		homeTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
		usernameTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(homeTitle);

		JLabel phoneTitle = new JLabel("Phone Number");
		phoneTitle.setBounds(112, 540, 210, 14);
		phoneTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
		usernameTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(phoneTitle);

		firstnameField = new JTextField();
		firstnameField.setBounds(112, 259, 106, 20);
		usernameTitle.setLabelFor(firstnameField);
		add(firstnameField);
		firstnameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(260, 198, 106, 20);
		passwordTitle.setLabelFor(passwordField);
		add(passwordField);

		usernameField = new JTextField();
		usernameField.setBounds(112, 198, 106, 20);
		usernameField.setColumns(10);
		add(usernameField);

		lastnameField = new JTextField();
		lastnameField.setColumns(10);
		lastnameField.setBounds(260, 259, 106, 20);
		add(lastnameField);

		CNIC_Field = new JTextField();
		CNIC_Field.setColumns(10);
		CNIC_Field.setBounds(112, 376, 254, 20);
		add(CNIC_Field);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(112, 437, 254, 20);
		add(emailField);

		homeField = new JTextField();
		homeField.setColumns(10);
		homeField.setBounds(112, 495, 254, 20);
		add(homeField);

		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(112, 555, 254, 20);
		add(phoneField);

		signupButton = new JButton("Sign Up");
		signupButton.setBounds(112, 606, 89, 23);
		add(signupButton);
		signupButton.addMouseListener(this);
		signupButton.setFocusable(false);

		loginButton = new JButton("Login");
		loginButton.setBounds(277, 606, 89, 23);
		add(loginButton);
		loginButton.addMouseListener(this);
		loginButton.setFocusable(false);

		String[] genders = { "Male", "Female", "Other" };
		genderBox = new JComboBox<>(genders);
		genderBox.setBackground(Color.WHITE);
		genderBox.setBounds(260, 314, 106, 24);
		genderBox.addActionListener(this);
		add(genderBox);

		JLabel genderTitle = new JLabel("Gender:");
		genderTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
		genderTitle.setBounds(112, 318, 106, 14);
		add(genderTitle);

		setVisible(true);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == loginButton) {
			Template.changePanel(new Login());
		}
		if (e.getSource() == signupButton) {

			if (usernameField.getText().length() != 0 && passwordField.getText().length() != 0
					&& firstnameField.getText().length() != 0 && lastnameField.getText().length() != 0
					&& CNIC_Field.getText().length() != 0 && homeField.getText().length() != 0
					&& phoneField.getText().length() != 0) {

				try {
					if(!Utilities.checkUsername(usernameField.getText())){
						JOptionPane.showMessageDialog(null, "Username must contain letters and digits only!");
					}
					else if(!Utilities.checkUsername(passwordField.getText())){
						JOptionPane.showMessageDialog(null, "Password must contain letters and digits only!");
					}
					else if(!Utilities.checkStringWithSpaces(firstnameField.getText())){
						JOptionPane.showMessageDialog(null, "First Name must contain letters and spaces only!");
					}
					else if(!Utilities.checkStringWithSpaces(lastnameField.getText())){
						JOptionPane.showMessageDialog(null, "Last Name must contain letters and spaces only!");
					}
					else if(!Utilities.checkNumber(CNIC_Field.getText())){
						JOptionPane.showMessageDialog(null, "CNIC must contain digits only!");
					}
					else if(!Utilities.checkStringWithSpaces(homeField.getText())){
						JOptionPane.showMessageDialog(null, "Address must contain letters,digits and spaces only!");
					}
					else if(!Utilities.checkNumber(phoneField.getText())){
						JOptionPane.showMessageDialog(null, "Phone Number must contain letters only!");
					}
					else {
						PreparedStatement checkUsernameQuery = SqlConnection.connectToDatabase()
								.prepareStatement("select * from account where username = ?");

						PreparedStatement checkCNICQuery = SqlConnection.connectToDatabase()
								.prepareStatement("select * from users where CNIC = ?");

						PreparedStatement addAccountQuery = SqlConnection.connectToDatabase()
								.prepareStatement("insert into account values(?, ?, ?, ?)");

						PreparedStatement addUserQuery = SqlConnection.connectToDatabase()
								.prepareStatement("insert into users values(?, ?, ?, ?, ?, ?, ?, ?)");

						addAccountQuery.setString(1, usernameField.getText());
						addAccountQuery.setString(2, passwordField.getText());
						addAccountQuery.setString(3, "N");
						addAccountQuery.setString(4, "user");

						addUserQuery.setString(1, usernameField.getText());
						addUserQuery.setString(2, firstnameField.getText());
						addUserQuery.setString(3, lastnameField.getText());
						addUserQuery.setString(4, genderBox.getSelectedItem().toString());
						addUserQuery.setString(5, CNIC_Field.getText());
						addUserQuery.setString(6, emailField.getText());
						addUserQuery.setString(7, homeField.getText());
						addUserQuery.setString(8, phoneField.getText());

						checkUsernameQuery.setString(1, usernameField.getText());
						checkCNICQuery.setString(1, CNIC_Field.getText());


						if (SqlConnection.alterResults(checkUsernameQuery) != 0) {
							JOptionPane.showMessageDialog(null, "Username already registered. Try another one.");
						} else if (SqlConnection.alterResults(checkCNICQuery) != 0) {
							JOptionPane.showMessageDialog(null, "CNIC already in use. Try another one.");

						} else {
							SqlConnection.alterResults(addAccountQuery);
							SqlConnection.alterResults(addUserQuery);
							JOptionPane.showMessageDialog(null, "User Registered Successfully");
							Template.changePanel(new Login());
						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "An error occurred while processing your information. Please try again!");
					e1.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Fill all the data fields!");
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
