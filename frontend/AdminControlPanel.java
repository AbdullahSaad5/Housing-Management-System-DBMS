package frontend;

import backend.SqlConnection;
import backend.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class AdminControlPanel extends JPanel implements ActionListener, MouseListener {

	private final JComboBox selectProvince;
	private final JComboBox<String> selectCity;
	private final JComboBox<String> selectLocation;
	private final JComboBox<String> selectType;
	private final JTextField newLocation;
	private final JLabel colonyLabel;
	private final JLabel locationLabel;
	private final JLabel cityLabel;
	private ArrayList<String> provinceList;
	private final JButton submitButton;
	private final JButton addLocations;
	private final JButton blockUsers;
	private final JButton viewRecords;
	private final JButton logout;
	private final JButton updateLocation;
	private final JButton deleteLocation;
	private static JPanel currentPanel, contentPanel;

	/**
	 * Create the panel.
	 */
	public AdminControlPanel() {

		Template.mainFrame.setSize(new Dimension(1280, 720));
		setLayout(new BorderLayout(0, 0));

		JPanel controlPanel = new JPanel();
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setHgap(50);
		controlPanel.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		controlPanel.setPreferredSize(new Dimension(1280, 70));
		add(controlPanel, BorderLayout.NORTH);

		addLocations = new JButton("Add Locations");
		addLocations.addMouseListener(this);
		addLocations.setSelectedIcon(new ImageIcon(Objects.requireNonNull(Objects.requireNonNull(AdminControlPanel.class.getResource("/images/world.png")))));
		addLocations.setFocusable(false);
		addLocations.setFont(new Font("SansSerif", Font.PLAIN, 9));
		addLocations.setPreferredSize(new Dimension(100, 60));
		addLocations.setBackground(new Color(255, 255, 255));
		addLocations.setIcon(new ImageIcon(Objects.requireNonNull(AdminControlPanel.class.getResource("/images/world.png"))));
		addLocations.setVerticalTextPosition(SwingConstants.BOTTOM);
		addLocations.setHorizontalTextPosition(SwingConstants.CENTER);
		controlPanel.add(addLocations);

		blockUsers = new JButton("Block Users");
		blockUsers.addMouseListener(this);
		blockUsers.setSelectedIcon(new ImageIcon(Objects.requireNonNull(AdminControlPanel.class.getResource("/images/block.png"))));
		blockUsers.setFocusable(false);
		blockUsers.setFont(new Font("SansSerif", Font.PLAIN, 10));
		blockUsers.setPreferredSize(new Dimension(100, 60));
		blockUsers.setBackground(new Color(255, 255, 255));
		blockUsers.setIcon(new ImageIcon(Objects.requireNonNull(AdminControlPanel.class.getResource("/images/block.png"))));
		blockUsers.setVerticalTextPosition(SwingConstants.BOTTOM);
		blockUsers.setHorizontalTextPosition(SwingConstants.CENTER);
		controlPanel.add(blockUsers);

		viewRecords = new JButton("View Records");
		viewRecords.addMouseListener(this);
		viewRecords.setFocusable(false);
		viewRecords.setFont(new Font("SansSerif", Font.PLAIN, 10));
		viewRecords.setPreferredSize(new Dimension(100, 60));
		viewRecords.setBackground(new Color(255, 255, 255));
		viewRecords.setIcon(new ImageIcon(Objects.requireNonNull(AdminControlPanel.class.getResource("/images/record.png"))));
		viewRecords.setVerticalTextPosition(SwingConstants.BOTTOM);
		viewRecords.setHorizontalTextPosition(SwingConstants.CENTER);
		controlPanel.add(viewRecords);

		logout = new JButton("Logout");
		logout.addMouseListener(this);
		logout.setFocusable(false);
		logout.setFont(new Font("SansSerif", Font.PLAIN, 10));
		logout.setPreferredSize(new Dimension(100, 60));
		logout.setBackground(new Color(255, 255, 255));
		logout.setIcon(new ImageIcon(Objects.requireNonNull(UserDashboard.class.getResource("/images/logout.png"))));
		logout.setVerticalTextPosition(SwingConstants.BOTTOM);
		logout.setHorizontalTextPosition(SwingConstants.CENTER);
		controlPanel.add(logout);

		currentPanel = new JPanel();
		currentPanel.setBackground(Color.WHITE);
		add(currentPanel, BorderLayout.CENTER);
		currentPanel.setPreferredSize(new Dimension(1280, 650));
		currentPanel.setLayout(new GridLayout(0, 1, 0, 0));

		contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(null);
		contentPanel.setPreferredSize(new Dimension(1280, 650));
		currentPanel.add(contentPanel);

		try {
			PreparedStatement provinces = SqlConnection.connectToDatabase()
					.prepareStatement("select province_name from province");
			ResultSet result = SqlConnection.findResult(provinces);
			provinceList = new ArrayList<>();
			while (result.next())
				provinceList.add(result.getString(1));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		selectProvince = new JComboBox(provinceList.toArray());
		selectProvince.setBackground(Color.WHITE);
		selectProvince.setSelectedItem(null);
		selectProvince.addActionListener(this);
		contentPanel.setLayout(null);
		selectProvince.setBounds(670, 220, 130, 24);
		contentPanel.add(selectProvince);

		selectCity = new JComboBox<>();
		selectCity.setBackground(Color.WHITE);
		selectCity.addActionListener(this);
		selectCity.setBounds(670, 290, 130, 24);
		contentPanel.add(selectCity);

		selectLocation = new JComboBox<>();
		selectLocation.setBackground(Color.WHITE);
		selectLocation.setBounds(670, 360, 130, 24);
		contentPanel.add(selectLocation);

		newLocation = new JTextField();
		newLocation.setBackground(Color.WHITE);
		newLocation.setBounds(670, 430, 130, 24);
		contentPanel.add(newLocation);

		JLabel mainLabel = new JLabel("Add New Locations");
		mainLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setBounds(10, 50, 1260, 30);
		contentPanel.add(mainLabel);

		deleteLocation = new JButton("Delete Locations");
		deleteLocation.addMouseListener(this);
		deleteLocation.setBounds(990, 70, 160, 25);
		deleteLocation.setFocusable(false);
		contentPanel.add(deleteLocation);

		updateLocation = new JButton("Update Locations");
		updateLocation.addMouseListener(this);
		updateLocation.setBounds(990, 120, 160, 25);
		updateLocation.setFocusable(false);
		contentPanel.add(updateLocation);

		JLabel provinceLabel = new JLabel("Province");
		provinceLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		provinceLabel.setBounds(483, 220, 100, 20);
		contentPanel.add(provinceLabel);

		cityLabel = new JLabel("City");
		cityLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		cityLabel.setBounds(483, 290, 100, 20);
		contentPanel.add(cityLabel);

		locationLabel = new JLabel("Location");
		locationLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		locationLabel.setBounds(483, 360, 100, 20);
		contentPanel.add(locationLabel);

		colonyLabel = new JLabel("Colony");
		colonyLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		colonyLabel.setBounds(483, 430, 100, 20);
		contentPanel.add(colonyLabel);

		String[] types = { "Province", "City", "Location", "Colony" };
		selectType = new JComboBox<>(types);
		selectType.setSelectedIndex(3);
		selectType.addActionListener(this);
		selectType.setBounds(670, 150, 130, 24);
		contentPanel.add(selectType);

		JLabel locationType = new JLabel("Location Type");
		locationType.setFont(new Font("SansSerif", Font.BOLD, 14));
		locationType.setBounds(483, 150, 130, 20);
		contentPanel.add(locationType);

		submitButton = new JButton("Submit");
		submitButton.setBounds(590, 500, 100, 20);
		submitButton.addMouseListener(this);
		contentPanel.add(submitButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == selectProvince) {
			if (selectProvince.getSelectedItem() != null) {
				String query = "select city_name from city where province_id = (select province_id from province where province_name = ?)";
				dropdown(query, selectProvince, selectCity);
			}
		} else if (e.getSource() == selectCity) {
			if (selectCity.getSelectedItem() != null) {
				String query = "select location_name from location where city_id = (select city_id from city where city_name = ?)";
				dropdown(query, selectCity, selectLocation);
			}
		}

		else if (selectType.getSelectedIndex() == 0) {
			cityLabel.setVisible(false);
			locationLabel.setVisible(false);
			colonyLabel.setVisible(false);
			selectCity.setVisible(false);
			selectLocation.setVisible(false);
			selectProvince.setVisible(false);
			newLocation.setBounds(670, 220, 130, 24);

		} else if (selectType.getSelectedIndex() == 1) {

			cityLabel.setVisible(true);
			locationLabel.setVisible(false);
			colonyLabel.setVisible(false);
			selectCity.setVisible(false);
			selectLocation.setVisible(false);
			selectProvince.setVisible(true);
			newLocation.setBounds(670, 290, 130, 24);

		} else if (selectType.getSelectedIndex() == 2) {
			cityLabel.setVisible(true);
			locationLabel.setVisible(true);
			colonyLabel.setVisible(false);
			selectCity.setVisible(true);
			selectLocation.setVisible(false);
			selectProvince.setVisible(true);
			newLocation.setBounds(670, 360, 130, 24);
			if (selectProvince.getSelectedItem() != null) {
				String query = "select city_name from city where province_id = (select province_id from province where province_name = ?)";
				dropdown(query, selectProvince, selectCity);
			} else {
				selectCity.setSelectedItem(null);
			}

		} else if (selectType.getSelectedIndex() == 3) {
			cityLabel.setVisible(true);
			locationLabel.setVisible(true);
			colonyLabel.setVisible(true);
			selectCity.setVisible(true);
			selectLocation.setVisible(true);
			selectProvince.setVisible(true);
			newLocation.setBounds(670, 430, 130, 24);
			if (selectCity.getSelectedItem() != null) {
				String query = "select location_name from location where city_id = (select city_id from city where city_name = ?)";
				dropdown(query, selectCity, selectLocation);
			} else {
				selectLocation.setSelectedItem(null);
			}
		}
	}

	private void dropdown(String Query, JComboBox<String> box, JComboBox<String> box2) {
		try {
			PreparedStatement queryStatement = SqlConnection.connectToDatabase().prepareStatement(Query);
			queryStatement.setString(1, Objects.requireNonNull(box.getSelectedItem()).toString());
			ResultSet result = SqlConnection.findResult(queryStatement);
			box2.removeAllItems();
			while (result.next()) {
				box2.addItem(result.getString(1));
			}
		} catch (Exception e1) {
			System.out.println("Error");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == submitButton) {
			if (selectType.getSelectedIndex() == 0) {
				int total = 0;
				try {
					PreparedStatement checkName = SqlConnection.connectToDatabase().prepareStatement("select * from province where upper(province_name) = ?");
					checkName.setString(1, newLocation.getText().toUpperCase());
					total = SqlConnection.alterResults(checkName);
					System.out.println(total);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if(total == 0) {
					if (!newLocation.getText().isBlank()) {
						if(!Utilities.checkStringWithSpaces(newLocation.getText())){
							JOptionPane.showMessageDialog(null, "Name can have letters and spaces only!");
						}
						else {
							try {
								PreparedStatement totalProvince = SqlConnection.connectToDatabase().prepareStatement(
										"select max(province_id) from province");
								ResultSet result = totalProvince.executeQuery();
								int count = 0;
								while (result.next()) {
									count = result.getInt(1);
								}
								PreparedStatement addProvince = SqlConnection.connectToDatabase()
										.prepareStatement("insert into province values (?, ?)");
								addProvince.setInt(1, ++count);
								addProvince.setString(2, newLocation.getText());
								addProvince.executeUpdate();
								JOptionPane.showMessageDialog(null, "Province Added to Database!");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "New Location Entry is Blank. Try Again!");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Province already exists!");
				}
			} else if (selectType.getSelectedIndex() == 1) {
				int total = 0;
				try {
					PreparedStatement checkName = SqlConnection.connectToDatabase().prepareStatement("select * from city where upper(city_name) = ?");
					checkName.setString(1, newLocation.getText().toUpperCase());
					total = SqlConnection.alterResults(checkName);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if(total == 0) {
				if (!newLocation.getText().isBlank()) {
					if(!Utilities.checkStringWithSpaces(newLocation.getText())){
						JOptionPane.showMessageDialog(null, "Name can have letters and spaces only!");
					}
					else {
						try {
							PreparedStatement totalCity = SqlConnection.connectToDatabase().prepareStatement(
									"select max(city_id) from city");
							PreparedStatement provinceID = SqlConnection.connectToDatabase()
									.prepareStatement("select province_id from province where province_name = ?");
							provinceID.setString(1, Objects.requireNonNull(selectProvince.getSelectedItem()).toString());
							System.out.println(selectProvince.getSelectedItem().toString());

							ResultSet result = totalCity.executeQuery();
							int count = 0;
							while (result.next()) {
								count = result.getInt(1);
							}

							result = provinceID.executeQuery();
							int province_id = 0;
							while (result.next()) {
								province_id = result.getInt(1);
							}
							PreparedStatement addCity = SqlConnection.connectToDatabase()
									.prepareStatement("insert into city values (?, ?, ?)");
							addCity.setInt(1, ++count);
							addCity.setString(2, newLocation.getText());
							addCity.setInt(3, province_id);
							addCity.executeUpdate();
							JOptionPane.showMessageDialog(null, "City Added to Database!");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "New Location Entry is Blank. Try Again!");
				}
				}else{
					JOptionPane.showMessageDialog(null, "City already exists!");
				}
			} else if (selectType.getSelectedIndex() == 2) {
					int total = 0;
					try {
						PreparedStatement checkName = SqlConnection.connectToDatabase().prepareStatement("select * from location where upper(location_name) = ?");
						checkName.setString(1, newLocation.getText().toUpperCase());
						total = SqlConnection.alterResults(checkName);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					if(total == 0) {

						if (!newLocation.getText().isBlank()) {
							if(!Utilities.checkStringWithSpaces(newLocation.getText())){
								JOptionPane.showMessageDialog(null, "Name can have letters and spaces only!");
							}
							else {
								try {
									PreparedStatement totalLocation = SqlConnection.connectToDatabase().prepareStatement(
											"select max(location_id) from location");
									PreparedStatement cityID = SqlConnection.connectToDatabase()
											.prepareStatement("select city_id from city where city_name = ?");
									cityID.setString(1, Objects.requireNonNull(selectCity.getSelectedItem()).toString());

									ResultSet result = totalLocation.executeQuery();
									int count = 0;
									while (result.next()) {
										count = result.getInt(1);
									}

									result = cityID.executeQuery();
									int city_id = 0;
									while (result.next()) {
										city_id = result.getInt(1);
									}
									PreparedStatement addCity = SqlConnection.connectToDatabase()
											.prepareStatement("insert into location values (?, ?, ?)");
									addCity.setInt(1, ++count);
									addCity.setString(2, newLocation.getText());
									addCity.setInt(3, city_id);
									addCity.executeUpdate();
									JOptionPane.showMessageDialog(null, "Location Added to Database!");
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}

						}else {
							JOptionPane.showMessageDialog(null, "New Location Entry is Blank. Try Again!");
						}
					}else{
						JOptionPane.showMessageDialog(null, "Location already exists!");
					}
			} else if (selectType.getSelectedIndex() == 3) {
					int total = 0;
					try {
						PreparedStatement checkName = SqlConnection.connectToDatabase().prepareStatement("select * from colony where upper(colony_name) = ?");
						checkName.setString(1, newLocation.getText().toUpperCase());
						total = SqlConnection.alterResults(checkName);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					if(total == 0) {
						if (!newLocation.getText().isBlank()) {
							if(!Utilities.checkStringWithSpaces(newLocation.getText())){
								JOptionPane.showMessageDialog(null, "Name can have letters and spaces only!");
							}
							else {
								try {
									PreparedStatement totalColony = SqlConnection.connectToDatabase().prepareStatement(
											"sselect max(colony_id) from colony");
									PreparedStatement locationID = SqlConnection.connectToDatabase()
											.prepareStatement("select location_id from location where location_name = ?");
									locationID.setString(1, Objects.requireNonNull(selectLocation.getSelectedItem()).toString());

									ResultSet result = totalColony.executeQuery();
									int count = 0;
									while (result.next()) {
										count = result.getInt(1);
									}

									result = locationID.executeQuery();
									int location_id = 0;
									while (result.next()) {
										location_id = result.getInt(1);
									}
									PreparedStatement addColony = SqlConnection.connectToDatabase()
											.prepareStatement("insert into colony values (?, ?, ?)");
									addColony.setInt(1, ++count);
									addColony.setString(2, newLocation.getText());
									addColony.setInt(3, location_id);
									addColony.executeUpdate();
									JOptionPane.showMessageDialog(null, "Colony Added to Database!");
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}
						}else {
							JOptionPane.showMessageDialog(null, "New Location Entry is Blank. Try Again!");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Colony already exists!");
					}
			}

		} else if (e.getSource() == addLocations) {
			Template.changePanel(new AdminControlPanel());
		} else if (e.getSource() == blockUsers) {
			AdminControlPanel.replaceContentPanel(new BlockUsers());
		} else if (e.getSource() == viewRecords) {
			AdminControlPanel.replaceContentPanel(new ViewRecords());
		} else if (e.getSource() == logout) {
			Template.mainFrame.setSize(460, 640);
			Template.changePanel(new Login());
		} else if (e.getSource() == deleteLocation) {
			AdminControlPanel.replaceContentPanel(new DeleteLocation());
		} else if (e.getSource() == updateLocation) {
			AdminControlPanel.replaceContentPanel(new UpdateLocation());
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

	public static void replaceContentPanel(JPanel panel) {
		currentPanel.remove(contentPanel);
		contentPanel = panel;
		currentPanel.add(contentPanel);
		currentPanel.revalidate();
		currentPanel.repaint();
	}
}
