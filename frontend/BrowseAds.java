package frontend;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.LineBorder;

import backend.SqlConnection;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class BrowseAds extends JPanel {

	/**
	 * Create the panel.
	 */
	public BrowseAds() {
		setBackground(Color.WHITE);
		setBounds(0, 0, 1280, 650);
		setLayout(null);
		JLabel titleLabel = new JLabel("Browse Advertisements");
		titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 22));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(505, 30, 270, 30);
		add(titleLabel);
		
		String query = "select advertisement_price, first_name, last_name, colony_name, location_name, city_name,"
				+ " province_name, house_bedrooms, house_bathrooms, house_stories, house_area"
				+ " from advertisement natural join property natural join colony natural join location natural join"
				+ " city natural join province natural join house natural join users";
		try {
			PreparedStatement houseAdvertisements = SqlConnection.connectToDatabase().prepareStatement(query);
			ResultSet result = SqlConnection.findResult(houseAdvertisements);
			int count = 0;
			while(result.next() && count < 4) {
				int price = result.getInt(1);
				String name = result.getString(2) + " " + result.getString(3);
				String location = result.getString(4) + ", " + result.getString(5) + ", " + result.getString(6) + ", " + result.getString(7);
				int bedrooms = result.getInt(8);
				int bathrooms = result.getInt(9);
				int stories = result.getInt(10);
				int area = result.getInt(11);
				JPanel curr = createAdvertisement(price, name, location, bedrooms, bathrooms, stories, area, true);
				add(curr);
				if(count % 4 == 0) {
					curr.setBounds(70, 138, 500, 230);
				}
				if(count % 4 == 1) {
					curr.setBounds(700, 138, 500, 230);
				}
				if(count % 4 == 2) {
					curr.setBounds(70, 380, 500, 230);
				}
				else if(count % 4 == 3){
					curr.setBounds(700, 380, 500, 230);
				}
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		


	}

	public JPanel createAdvertisement(int price, String owner, String location, int bedrooms, int bathrooms,
			int stories, int area, boolean house) {
		JPanel adPanel = new JPanel();
		adPanel.setBackground(Color.WHITE);
		adPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		adPanel.setBounds(50, 138, 500, 230);
		add(adPanel);
		adPanel.setLayout(null);

		JLabel housePicture = new JLabel("");
		housePicture.setIcon(new ImageIcon(BrowseAds.class.getResource("/images/stock house.jpg")));
		housePicture.setBounds(10, 0, 194, 200);
		adPanel.add(housePicture);

		JLabel priceLabel = new JLabel(String.valueOf(price));
		priceLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		priceLabel.setIcon(new ImageIcon(BrowseAds.class.getResource("/images/price.png")));
		priceLabel.setBounds(214, 11, 276, 32);
		adPanel.add(priceLabel);

		JLabel ownerLabel = new JLabel(owner);
		ownerLabel.setIcon(new ImageIcon(BrowseAds.class.getResource("/images/owner.png")));
		ownerLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		ownerLabel.setBounds(218, 50, 270, 21);
		adPanel.add(ownerLabel);

		JLabel locationLabel = new JLabel("<html>"+ location +"</html>");
		locationLabel.setIcon(new ImageIcon(BrowseAds.class.getResource("/images/location.png")));
		locationLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		locationLabel.setBounds(220, 75, 270, 30);
		adPanel.add(locationLabel);

		JLabel bedroomLabel = new JLabel(String.valueOf(bedrooms) + " Rooms");
		bedroomLabel.setIcon(new ImageIcon(BrowseAds.class.getResource("/images/bedroom.png")));
		bedroomLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		bedroomLabel.setBounds(224, 118, 100, 21);
		adPanel.add(bedroomLabel);

		JLabel bathroomLabel = new JLabel(String.valueOf(bathrooms) + " Baths");
		bathroomLabel.setIcon(new ImageIcon(BrowseAds.class.getResource("/images/bathroom.png")));
		bathroomLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		bathroomLabel.setBounds(350, 118, 100, 21);
		adPanel.add(bathroomLabel);

		JLabel storiesLabel = new JLabel(String.valueOf(stories) + " Stories");
		storiesLabel.setIcon(new ImageIcon(BrowseAds.class.getResource("/images/floors.png")));
		storiesLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		storiesLabel.setBounds(224, 150, 100, 21);
		adPanel.add(storiesLabel);

		JLabel areaLabel = new JLabel(String.valueOf(area) + " Marla");
		areaLabel.setIcon(new ImageIcon(BrowseAds.class.getResource("/images/area.png")));
		areaLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		areaLabel.setBounds(350, 150, 100, 21);
		adPanel.add(areaLabel);

		JButton btnNewButton = new JButton("Buy");
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnNewButton.setBounds(214, 196, 120, 23);
		adPanel.add(btnNewButton);

		JButton btnAddToFavorite = new JButton("Add to Favorite");
		btnAddToFavorite.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnAddToFavorite.setBounds(360, 196, 120, 23);
		adPanel.add(btnAddToFavorite);

		if (!house) {
			housePicture.setIcon(new ImageIcon(BrowseAds.class.getResource("/images/stock land.jpeg")));
			bedroomLabel.setVisible(false);
			bathroomLabel.setVisible(false);
			storiesLabel.setVisible(false);
			areaLabel.setBounds(224, 118, 100, 21);
		}

		return adPanel;
	}
}
