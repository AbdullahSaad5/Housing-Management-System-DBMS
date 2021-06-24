package frontend;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.UIManager;

import backend.SqlConnection;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AdminControlPanel extends JPanel implements ActionListener, MouseListener {

	private JComboBox<String> selectProvince, selectCity, selectLocation, selectType;
	private JTextField newLocation;
	private JLabel colonyLabel, locationLabel, cityLabel, provinceLabel;
	private ArrayList<String> provinceList;
	private JButton submitButton;

	/**
	 * Create the panel.
	 */
	public AdminControlPanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel controlPanel = new JPanel();
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setHgap(50);
		controlPanel.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		controlPanel.setPreferredSize(new Dimension(1280, 70));
		add(controlPanel, BorderLayout.NORTH);

		JButton addLocations = new JButton("Add Locations");
		addLocations.setSelectedIcon(new ImageIcon(AdminControlPanel.class.getResource("/images/world.png")));
		addLocations.setFocusable(false);
		addLocations.setFont(new Font("SansSerif", Font.PLAIN, 9));
		addLocations.setPreferredSize(new Dimension(100, 60));
		addLocations.setBackground(new Color(255, 255, 255));
		addLocations.setIcon(new ImageIcon(AdminControlPanel.class.getResource("/images/world.png")));
		addLocations.setVerticalTextPosition(JLabel.BOTTOM);
		addLocations.setHorizontalTextPosition(JLabel.CENTER);
		controlPanel.add(addLocations);

		JButton blockUsers = new JButton("Block Users");
		blockUsers.setSelectedIcon(new ImageIcon(AdminControlPanel.class.getResource("/images/block.png")));
		blockUsers.setFocusable(false);
		blockUsers.setFont(new Font("SansSerif", Font.PLAIN, 10));
		blockUsers.setPreferredSize(new Dimension(100, 60));
		blockUsers.setBackground(new Color(255, 255, 255));
		blockUsers.setIcon(new ImageIcon(AdminControlPanel.class.getResource("/images/block.png")));
		blockUsers.setVerticalTextPosition(JLabel.BOTTOM);
		blockUsers.setHorizontalTextPosition(JLabel.CENTER);
		controlPanel.add(blockUsers);

		JButton viewRecords = new JButton("View Records");
		viewRecords.setFocusable(false);
		viewRecords.setFont(new Font("SansSerif", Font.PLAIN, 10));
		viewRecords.setPreferredSize(new Dimension(100, 60));
		viewRecords.setBackground(new Color(255, 255, 255));
		viewRecords.setIcon(new ImageIcon(AdminControlPanel.class.getResource("/images/record.png")));
		viewRecords.setVerticalTextPosition(JLabel.BOTTOM);
		viewRecords.setHorizontalTextPosition(JLabel.CENTER);
		controlPanel.add(viewRecords);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(1280, 650));
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(null);
		contentPanel.setPreferredSize(new Dimension(1280, 650));
		panel.add(contentPanel);

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

		selectProvince = new JComboBox(provinceList.toArray());
		selectProvince.setBackground(Color.WHITE);
		selectProvince.setSelectedItem(null);
		selectProvince.addActionListener(this);
		contentPanel.setLayout(null);
		selectProvince.setBounds(670, 220, 130, 24);
		contentPanel.add(selectProvince);

		selectCity = new JComboBox<String>();
		selectCity.setBackground(Color.WHITE);
		selectCity.addActionListener(this);
		selectCity.setBounds(670, 290, 130, 24);
		contentPanel.add(selectCity);

		selectLocation = new JComboBox<String>();
		selectLocation.setBackground(Color.WHITE);
		selectLocation.addActionListener(this);
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

		provinceLabel = new JLabel("Province");
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

		String types[] = { "Province", "City", "Location", "Colony" };
		selectType = new JComboBox<String>(types);
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
		if (selectType.getSelectedIndex() == 0) {
			cityLabel.setVisible(false);
			locationLabel.setVisible(false);
			colonyLabel.setVisible(false);
			selectCity.setVisible(false);
			selectLocation.setVisible(false);
			selectProvince.setVisible(false);
			newLocation.setBounds(670, 220, 130, 24);

		} else if (selectType.getSelectedIndex() == 1) {
			selectProvince.removeAllItems();
			for (int i = 0; i < provinceList.size(); i++) {
				selectProvince.addItem(provinceList.get(i));
			}
			revalidate();
			repaint();
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
				String query = "select city_name from city province_id = (select province_id from province where province_name = ?)";
				dropdown(query, selectProvince, selectCity);

			} else {
				selectLocation.setSelectedItem(null);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == submitButton) {
			if (selectType.getSelectedIndex() == 0) {
				if (!newLocation.getText().isBlank()) {
					try {
						PreparedStatement totalProvince = SqlConnection.connectToDatabase()
								.prepareStatement("select count(*) from province");
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
}
