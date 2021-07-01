package frontend;

import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import backend.SqlConnection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class BrowseHouses extends JPanel implements ActionListener {

	/**
	 * Create the panel.
	 */
	private static int pageNumber = 1, totalPages = 1, totalCount = 0;
	JLabel pageCount;
	static ArrayList<Ad> array = new ArrayList<>();
	JButton next, prev;
	private final JButton[] buttons = new JButton[4];
	String query;

	public BrowseHouses() {

		setBackground(Color.WHITE);
		setBounds(0, 0, 1280, 650);
		setLayout(null);

		JLabel titleLabel = new JLabel("Browse Advertisements");
		titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 22));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(505, 30, 270, 30);
		add(titleLabel);

		next = new JButton("");
		next.addActionListener(this);
		next.setIcon(new ImageIcon(Objects.requireNonNull(BrowsePlots.class.getResource("/images/next.png"))));
		next.setBackground(null);
		next.setBounds(735, 30, 40, 40);
		add(next);

		prev = new JButton("");
		prev.addActionListener(this);
		prev.setIcon(new ImageIcon(Objects.requireNonNull(BrowsePlots.class.getResource("/images/prev.png"))));
		prev.setBackground(null);
		prev.setBounds(515, 30, 40, 40);
		add(prev);

		pageCount = new JLabel("");
		pageCount.setHorizontalAlignment(SwingConstants.CENTER);
		pageCount.setFont(new Font("SansSerif", Font.BOLD, 20));
		pageCount.setBounds(573, 72, 144, 40);
		add(pageCount);

		query = "select advertisement_price, first_name, last_name, colony_name, location_name, city_name,"
				+ " province_name, house_bedrooms, house_bathrooms, house_stories, house_area, advertisement_id"
				+ " from advertisement natural join property natural join colony natural join location natural join"
				+ " city natural join province natural join house natural join users where username != ? and " +
				"advertisement_id in (select advertisement_id from advertisement minus select advertisement_id from selling_record)";
		try {
			PreparedStatement houseAdvertisements = SqlConnection.connectToDatabase().prepareStatement(query);
			houseAdvertisements.setString(1, Login.currentUserID);
			ResultSet result = SqlConnection.findResult(houseAdvertisements);
			array.removeAll(array);
			while (result.next()) {
				Ad curr = new Ad(result.getInt(12), result.getInt(1), result.getString(2) + " " + result.getString(3),
						result.getString(4) + ", " + result.getString(5) + ", " + result.getString(6) + ", "
								+ result.getString(7),
						result.getInt(8), result.getInt(9), result.getInt(10), result.getInt(11));
				array.add(curr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		totalCount = array.size();
		if (totalCount % 4 != 0) {
			totalPages = (totalCount / 4) + 1;
		} else {
			totalPages = (totalCount / 4);
		}
		if(totalPages == 0){
			totalPages = 1;
		}

		titleLabel.setText(String.valueOf( "Page: " + pageNumber) + "/" + String.valueOf(totalPages));
		viewAds();
	}

	public JPanel createAdvertisement(int price, String owner, String location, int bedrooms, int bathrooms,
			int stories, int area) {
		JPanel adPanel = new JPanel();
		adPanel.setBackground(Color.WHITE);
		adPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		adPanel.setBounds(50, 138, 500, 230);
		this.add(adPanel);
		adPanel.setLayout(null);

		JLabel housePicture = new JLabel("");
		housePicture.setIcon(new ImageIcon(Objects.requireNonNull(BrowsePlots.class.getResource("/images/stock house.jpg"))));
		housePicture.setBounds(10, 0, 194, 200);
		adPanel.add(housePicture);

		JLabel priceLabel = new JLabel(String.valueOf(price));
		priceLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		priceLabel.setIcon(new ImageIcon(Objects.requireNonNull(BrowsePlots.class.getResource("/images/price.png"))));
		priceLabel.setBounds(214, 11, 276, 32);
		adPanel.add(priceLabel);

		JLabel ownerLabel = new JLabel(owner);
		ownerLabel.setIcon(new ImageIcon(Objects.requireNonNull(BrowsePlots.class.getResource("/images/owner.png"))));
		ownerLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		ownerLabel.setBounds(218, 50, 270, 21);
		adPanel.add(ownerLabel);

		JLabel locationLabel = new JLabel("<html>" + location + "</html>");
		locationLabel.setIcon(new ImageIcon(Objects.requireNonNull(BrowsePlots.class.getResource("/images/location.png"))));
		locationLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		locationLabel.setBounds(220, 75, 270, 30);
		adPanel.add(locationLabel);

		JLabel bedroomLabel = new JLabel(String.valueOf(bedrooms) + " Rooms");
		bedroomLabel.setIcon(new ImageIcon(Objects.requireNonNull(BrowsePlots.class.getResource("/images/bedroom.png"))));
		bedroomLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		bedroomLabel.setBounds(224, 118, 100, 21);
		adPanel.add(bedroomLabel);

		JLabel bathroomLabel = new JLabel(String.valueOf(bathrooms) + " Baths");
		bathroomLabel.setIcon(new ImageIcon(Objects.requireNonNull(BrowsePlots.class.getResource("/images/bathroom.png"))));
		bathroomLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		bathroomLabel.setBounds(350, 118, 100, 21);
		adPanel.add(bathroomLabel);

		JLabel storiesLabel = new JLabel(String.valueOf(stories) + " Stories");
		storiesLabel.setIcon(new ImageIcon(Objects.requireNonNull(BrowsePlots.class.getResource("/images/floors.png"))));
		storiesLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		storiesLabel.setBounds(224, 150, 100, 21);
		adPanel.add(storiesLabel);

		JLabel areaLabel = new JLabel(String.valueOf(area) + " Marla");
		areaLabel.setIcon(new ImageIcon(Objects.requireNonNull(BrowsePlots.class.getResource("/images/area.png"))));
		areaLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		areaLabel.setBounds(350, 150, 100, 21);
		adPanel.add(areaLabel);

		return adPanel;
	}

	public void viewAds() {
		for (int i = 0; i < 4; i++) {
			int index = ((pageNumber - 1) * 4) + i;
			if (index < totalCount) {
				Ad curr = array.get(index);
				JPanel panel = createAdvertisement(curr.price, curr.owner, curr.location, curr.bedrooms, curr.bathrooms,
						curr.stories, curr.area);
				if (index % 4 == 0) {
					panel.setBounds(70, 138, 500, 230);
					buttons[0] = new JButton("Buy");
					buttons[0].addActionListener(this);
					buttons[0].setFont(new Font("SansSerif", Font.PLAIN, 10));
					buttons[0].setBounds(260, 196, 120, 23);
					panel.add(buttons[0]);
				}
				if (index % 4 == 1) {
					panel.setBounds(700, 138, 500, 230);
					buttons[1] = new JButton("Buy");
					buttons[1].addActionListener(this);
					buttons[1].setFont(new Font("SansSerif", Font.PLAIN, 10));
					buttons[1].setBounds(260, 196, 120, 23);
					panel.add(buttons[1]);
				}
				if (index % 4 == 2) {
					panel.setBounds(70, 380, 500, 230);
					buttons[2] = new JButton("Buy/");
					buttons[2].addActionListener(this);
					buttons[2].setFont(new Font("SansSerif", Font.PLAIN, 10));
					buttons[2].setBounds(260, 196, 120, 23);
					panel.add(buttons[2]);
				} else if (index % 4 == 3) {
					panel.setBounds(700, 380, 500, 230);
					buttons[3] = new JButton("Buy");
					buttons[3].addActionListener(this);
					buttons[3].setFont(new Font("SansSerif", Font.PLAIN, 10));
					buttons[3].setBounds(260, 196, 120, 23);
					panel.add(buttons[3]);
				}
			} else {
				break;
			}
		}
	}

	public static void buyAdvertisement(int advertisement_id){
		try {
			String CNIC_Query = "select CNIC from users where username = ?";
			PreparedStatement stmt = SqlConnection.connectToDatabase().prepareStatement(CNIC_Query);
			stmt.setString(1, Login.currentUserID);
			ResultSet result = SqlConnection.findResult(stmt);
			String CNIC = "";
			while (result.next()){
				CNIC = result.getString(1);
			}
			PreparedStatement totalRecords = SqlConnection.connectToDatabase().prepareStatement(
					"select max(record_id) from selling_record");
			result = SqlConnection.findResult(totalRecords);
			int id = 0;
			while(result.next()){
				id = result.getInt(1);
			}
			String addRecord = "insert into selling_record values (?, ?, ?, ?)";

			PreparedStatement insertStmt = SqlConnection.connectToDatabase().prepareStatement(addRecord);
			insertStmt.setInt(1, ++id);
			insertStmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			insertStmt.setInt(3, advertisement_id);
			insertStmt.setString(4, CNIC);
			SqlConnection.alterResults(insertStmt);

			UserDashboard.replaceContentPanel(new BrowseHouses());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == next || e.getSource() == prev) {
			if (e.getSource() == next && pageNumber < totalPages) {
				pageNumber++;
				removeAll();
				UserDashboard.replaceContentPanel(new BrowseHouses());
				viewAds();
				pageCount.repaint();
			} else if (e.getSource() == prev && pageNumber > 1) {
				pageNumber--;
				removeAll();
				UserDashboard.replaceContentPanel(new BrowseHouses());
				viewAds();
				pageCount.repaint();
			}
			
		} else if (e.getSource() == buttons[0]) {
			int index = (pageNumber - 1) * 4;
			buyAdvertisement(array.get(index).id);
		} else if (e.getSource() == buttons[1]) {
			int index = (pageNumber - 1) * 4 + 1;
			buyAdvertisement(array.get(index).id);
		} else if (e.getSource() == buttons[2]) {
			int index = (pageNumber - 1) * 4 + 2;
			buyAdvertisement(array.get(index).id);
		} else if (e.getSource() == buttons[3]) {
			int index = (pageNumber - 1) * 4 + 3;
			buyAdvertisement(array.get(index).id);
		} else {
			System.out.println(e.getID());
		}
	}
}
