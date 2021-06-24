package frontend;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

import backend.SqlConnection;

@SuppressWarnings("serial")
public class UserDashboard extends JPanel implements ActionListener {
	private JLabel bedroomsText, bathroomsText, storiesText, purposeText;
	private JTextField areaField, priceField, bedroomField, bathroomField, storyField;
	private JComboBox<String> selectProvince, selectCity, selectLocation, selectColony, selectType, selectPurpose;
	private JButton postAd, addAdvertisment, houses, plots, filters, agents, userSettings, logout;
	ArrayList<String> provinceList;
	static JPanel currentPanel;
	static JPanel contentPanel;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UserDashboard() {
		Template.mainFrame.setSize(new Dimension(1280, 720));
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setPreferredSize(new Dimension(1280, 720));
		setLayout(new BorderLayout(0, 0));

		JPanel controlPanel = new JPanel();
		controlPanel.setPreferredSize(new Dimension(1280, 70));
		controlPanel.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		add(controlPanel, BorderLayout.NORTH);
		controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));

		addAdvertisment = new JButton("Post Ad");
		addAdvertisment.addActionListener(this);
		addAdvertisment.setFocusable(false);
		addAdvertisment.setFont(new Font("SansSerif", Font.PLAIN, 10));
		addAdvertisment.setPreferredSize(new Dimension(100, 60));
		addAdvertisment.setBackground(new Color(255, 255, 255));
		addAdvertisment.setIcon(new ImageIcon(UserDashboard.class.getResource("/images/add.png")));
		addAdvertisment.setVerticalTextPosition(JLabel.BOTTOM);
		addAdvertisment.setHorizontalTextPosition(JLabel.CENTER);
		controlPanel.add(addAdvertisment);

		houses = new JButton("Houses");
		houses.addActionListener(this);
		houses.setFocusable(false);
		houses.setFont(new Font("SansSerif", Font.PLAIN, 10));
		houses.setPreferredSize(new Dimension(100, 60));
		houses.setBackground(new Color(255, 255, 255));
		houses.setIcon(new ImageIcon(UserDashboard.class.getResource("/images/home.png")));
		houses.setVerticalTextPosition(JLabel.BOTTOM);
		houses.setHorizontalTextPosition(JLabel.CENTER);
		controlPanel.add(houses);

		plots = new JButton("Plots");
		plots.addActionListener(this);
		plots.setFocusable(false);
		plots.setFont(new Font("SansSerif", Font.PLAIN, 10));
		plots.setPreferredSize(new Dimension(100, 60));
		plots.setBackground(new Color(255, 255, 255));
		plots.setIcon(new ImageIcon(UserDashboard.class.getResource("/images/architect.png")));
		plots.setVerticalTextPosition(JLabel.BOTTOM);
		plots.setHorizontalTextPosition(JLabel.CENTER);
		controlPanel.add(plots);

		filters = new JButton("Filters");
		filters.addActionListener(this);
		filters.setFocusable(false);
		filters.setFont(new Font("SansSerif", Font.PLAIN, 10));
		filters.setPreferredSize(new Dimension(100, 60));
		filters.setBackground(new Color(255, 255, 255));
		filters.setIcon(new ImageIcon(UserDashboard.class.getResource("/images/filter.png")));
		filters.setVerticalTextPosition(JLabel.BOTTOM);
		filters.setHorizontalTextPosition(JLabel.CENTER);
		controlPanel.add(filters);

		agents = new JButton("Agents");
		agents.addActionListener(this);
		agents.setFocusable(false);
		agents.setFont(new Font("SansSerif", Font.PLAIN, 10));
		agents.setPreferredSize(new Dimension(100, 60));
		agents.setBackground(new Color(255, 255, 255));
		agents.setIcon(new ImageIcon(UserDashboard.class.getResource("/images/phone.png")));
		agents.setVerticalTextPosition(JLabel.BOTTOM);
		agents.setHorizontalTextPosition(JLabel.CENTER);
		controlPanel.add(agents);

		userSettings = new JButton("User Settings");
		userSettings.addActionListener(this);
		userSettings.setFocusable(false);
		userSettings.setFont(new Font("SansSerif", Font.PLAIN, 10));
		userSettings.setPreferredSize(new Dimension(100, 60));
		userSettings.setBackground(new Color(255, 255, 255));
		userSettings.setIcon(new ImageIcon(UserDashboard.class.getResource("/images/user.png")));
		userSettings.setVerticalTextPosition(JLabel.BOTTOM);
		userSettings.setHorizontalTextPosition(JLabel.CENTER);
		controlPanel.add(userSettings);
		
		
		logout = new JButton("Logout");
		logout.addActionListener(this);
		logout.setFocusable(false);
		logout.setFont(new Font("SansSerif", Font.PLAIN, 10));
		logout.setPreferredSize(new Dimension(100, 60));
		logout.setBackground(new Color(255, 255, 255));
		logout.setIcon(new ImageIcon(UserDashboard.class.getResource("/images/logout.png")));
		logout.setVerticalTextPosition(JLabel.BOTTOM);
		logout.setHorizontalTextPosition(JLabel.CENTER);
		controlPanel.add(logout);

		try {
			PreparedStatement provinces = SqlConnection.connectToDatabase()
					.prepareStatement("select province_name from province");
			ResultSet result = SqlConnection.findResult(provinces);
			provinceList = new ArrayList<String>();
			while (result.next())
				provinceList.add(result.getString(1));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String types[] = { "Plot", "House" };

		String purpose[] = { "Rent", "Sell" };

		currentPanel = new JPanel();
		currentPanel.setPreferredSize(new Dimension(1280, 650));
		currentPanel.setBackground(Color.white);
		add(currentPanel, BorderLayout.CENTER);
		currentPanel.setLayout(null);

		contentPanel = new JPanel();
		currentPanel.add(contentPanel);
		contentPanel.setPreferredSize(new Dimension(1280, 650));
		contentPanel.setBounds(0, 0, 1280, 650);
		contentPanel.setLayout(null);

		JLabel mainTitle = new JLabel("Post Advertisment");
		mainTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
		mainTitle.setHorizontalAlignment(SwingConstants.CENTER);
		mainTitle.setBounds(490, 25, 300, 30);
		contentPanel.add(mainTitle);

		areaField = new JTextField();
		areaField.setBounds(418, 150, 114, 25);
		contentPanel.add(areaField);
		areaField.setColumns(10);

		priceField = new JTextField();
		priceField.setBounds(418, 220, 114, 25);
		contentPanel.add(priceField);
		priceField.setColumns(10);

		bedroomField = new JTextField();
		bedroomField.setBounds(418, 290, 114, 25);
		contentPanel.add(bedroomField);
		bedroomField.setColumns(10);

		bathroomField = new JTextField();
		bathroomField.setColumns(10);
		bathroomField.setBounds(418, 360, 114, 25);
		contentPanel.add(bathroomField);

		storyField = new JTextField();
		storyField.setColumns(10);
		storyField.setBounds(418, 430, 114, 25);
		contentPanel.add(storyField);

		selectProvince = new JComboBox(provinceList.toArray());
		selectProvince.setSelectedItem(null);
		selectProvince.addActionListener(this);
		selectProvince.setBounds(860, 80, 250, 24);
		contentPanel.add(selectProvince);

		selectCity = new JComboBox<String>();
		selectCity.addActionListener(this);
		selectCity.setBounds(860, 150, 250, 24);
		contentPanel.add(selectCity);

		selectLocation = new JComboBox<String>();
		selectLocation.addActionListener(this);
		selectLocation.setBounds(860, 220, 250, 24);
		contentPanel.add(selectLocation);

		selectColony = new JComboBox<String>();
		selectColony.addActionListener(this);
		selectColony.setBounds(860, 290, 250, 24);
		contentPanel.add(selectColony);

		JLabel priceText = new JLabel("Price (Rs.)");
		priceText.setBounds(300, 220, 100, 15);
		contentPanel.add(priceText);

		JLabel typeText = new JLabel("Type");
		typeText.setBounds(300, 80, 70, 15);
		contentPanel.add(typeText);

		JLabel areaText = new JLabel("Area");
		areaText.setBounds(300, 150, 70, 15);
		contentPanel.add(areaText);

		bedroomsText = new JLabel("Bedrooms");
		bedroomsText.setBounds(300, 290, 105, 15);
		contentPanel.add(bedroomsText);

		bathroomsText = new JLabel("Bathrooms");
		bathroomsText.setBounds(300, 360, 105, 15);
		contentPanel.add(bathroomsText);

		storiesText = new JLabel("Stories");
		storiesText.setBounds(300, 430, 70, 15);
		contentPanel.add(storiesText);

		purposeText = new JLabel("Purpose");
		purposeText.setBounds(300, 500, 70, 15);
		contentPanel.add(purposeText);

		selectType = new JComboBox<String>(types);
		selectType.setSelectedIndex(1);
		selectType.addActionListener(this);
		selectType.setBounds(418, 75, 114, 24);
		contentPanel.add(selectType);

		selectPurpose = new JComboBox<String>(purpose);
		selectPurpose.setBounds(418, 500, 114, 24);
		contentPanel.add(selectPurpose);

		JLabel locationText = new JLabel("Location");
		locationText.setBounds(778, 220, 70, 15);
		contentPanel.add(locationText);

		JLabel provinceText = new JLabel("Province");
		provinceText.setBounds(778, 80, 92, 15);
		contentPanel.add(provinceText);

		JLabel cityText = new JLabel("City");
		cityText.setBounds(778, 150, 70, 15);
		contentPanel.add(cityText);

		JLabel colonyText = new JLabel("Colony");
		colonyText.setBounds(778, 290, 105, 15);
		contentPanel.add(colonyText);

		postAd = new JButton("Post Advertisement");
		postAd.setBounds(540, 570, 200, 30);
		contentPanel.add(postAd);

		contentPanel.setVisible(true);
		currentPanel.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ac) {
		if (ac.getSource() == selectProvince) {
			if (selectProvince.getSelectedItem() != null) {
				String query = "select city_name from city where province_id = (select province_id from province where province_name = ?)";
				dropdown(query, selectProvince, selectCity);
			} else {
				selectCity.setSelectedItem(null);
			}
		}

		else if (ac.getSource() == selectCity) {
			if (selectCity.getSelectedItem() != null) {
				String query = "select location_name from location where city_id = (select city_id from city where city_name = ?)";
				dropdown(query, selectCity, selectLocation);

			} else {
				selectLocation.setSelectedItem(null);
			}
		} else if (ac.getSource() == selectLocation) {
			if (selectLocation.getSelectedItem() != null) {
				String query = "select colony_name from colony where location_id = (select location_id from location where location_name = ?)";
				dropdown(query, selectLocation, selectColony);

			} else {
				selectColony.setSelectedItem(null);
			}
		} else if (ac.getSource() == selectType) {
			if (selectType.getSelectedItem().toString().equalsIgnoreCase("Plot")) {
				changeVisibility(false);
				postAd.setBounds(540, 380, 200, 30);

			} else {
				changeVisibility(true);
				postAd.setBounds(540, 570, 200, 30);
			}
		} else if (ac.getSource() == userSettings) {
			replaceContentPanel(new UserSettings());
		} else if (ac.getSource() == houses) {
			replaceContentPanel(new BrowseAds());
		}else if (ac.getSource() == plots) {
			replaceContentPanel(new BrowseAds());
		}else if (ac.getSource() == agents) {
			replaceContentPanel(new UserSettings());
		}else if (ac.getSource() == logout) {
			Template.mainFrame.setSize(460, 640);
			Template.changePanel(new Login());
		}else if (ac.getSource() == addAdvertisment) {
			Template.changePanel(new UserDashboard());
		}else if (ac.getSource() == userSettings) {
			replaceContentPanel(new UserSettings());
		}else if (ac.getSource() == postAd) {
			if (selectType.getSelectedIndex() == 1) {
				if (!(priceField.getText().isBlank() && areaField.getText().isBlank() && priceField.getText().isBlank()
						&& bedroomField.getText().isBlank() && bathroomField.getText().isBlank()
						&& storyField.getText().isBlank()) && selectCity.getSelectedItem() != null
						&& selectProvince.getSelectedItem() != null && selectLocation.getSelectedItem() != null
						&& selectColony.getSelectedItem() != null && selectPurpose != null) {
					try {

						PreparedStatement getColonyID = SqlConnection.connectToDatabase()
								.prepareStatement("select colony_id from colony natural join "
										+ "location natural join city natural join province where colony_name = ? and location_name = ? "
										+ "and city_name = ? and province_name = ?");
						getColonyID.setString(1, selectColony.getSelectedItem().toString());
						getColonyID.setString(2, selectLocation.getSelectedItem().toString());
						getColonyID.setString(3, selectCity.getSelectedItem().toString());
						getColonyID.setString(4, selectProvince.getSelectedItem().toString());
						
						PreparedStatement userCNIC = SqlConnection.connectToDatabase().prepareStatement("select CNIC from users where username = ?");
						userCNIC.setString(1, Login.currentUserID);
						ResultSet result = userCNIC.executeQuery();
						result.next();
						String cnic = result.getString(1);
						
						PreparedStatement idQuery = SqlConnection.connectToDatabase().prepareStatement("select count(*) from adverstisement");
						ResultSet queryResult = SqlConnection.findResult(idQuery);
						int count = 0;
						while(queryResult.next()) {
							count = queryResult.getInt(1);
						}
						String query = "insert into advertisement values (?, ?, ?, ?, ?, ?)";
						PreparedStatement addAdvertisementQuery = SqlConnection.connectToDatabase().prepareStatement(query);
						addAdvertisementQuery.setInt(1, ++count);
						addAdvertisementQuery.setString(2, "house");
						addAdvertisementQuery.setInt(1, Integer.parseInt(priceField.getText()));
						addAdvertisementQuery.setDate(4, new java.sql.Date(System.currentTimeMillis()));
						addAdvertisementQuery.setString(5, cnic);
						addAdvertisementQuery.setInt(5, ++count);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		revalidate();
		repaint();
	}

	private void dropdown(String Query, JComboBox<String> box, JComboBox<String> box2) {
		try {
			PreparedStatement queryStatement = SqlConnection.connectToDatabase().prepareStatement(Query);
			queryStatement.setString(1, box.getSelectedItem().toString());
			ResultSet result = SqlConnection.findResult(queryStatement);
			box2.removeAllItems();
			while (result.next()) {
				box2.addItem(result.getString(1));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void changeVisibility(boolean visible) {
		bathroomsText.setVisible(visible);
		bedroomsText.setVisible(visible);
		storiesText.setVisible(visible);
		purposeText.setVisible(visible);
		bathroomField.setVisible(visible);
		bedroomField.setVisible(visible);
		storyField.setVisible(visible);
		selectPurpose.setVisible(visible);
	}

	public static void replaceContentPanel(JPanel panel) {
		currentPanel.remove(contentPanel);
		contentPanel = panel;
		currentPanel.add(contentPanel);
		currentPanel.revalidate();
		currentPanel.repaint();
	}
}