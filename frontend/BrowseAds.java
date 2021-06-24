package frontend;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
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
		
		
		JPanel ad1, ad2, ad3, ad4;
		ad1 = createAdvertisement(true);
		ad2 = createAdvertisement(true);
		ad3 = createAdvertisement(false);
		ad4 = createAdvertisement(false);
		
		ad1.setBounds(70, 138, 500, 230);
		ad2.setBounds(700, 138, 500, 230);
		ad3.setBounds(70, 380, 500, 230);
		ad4.setBounds(700, 380, 500, 230);


	}
	
	// int price, String owner, String location, int bedrooms, int bathrooms, int stories, int area
	public JPanel createAdvertisement(boolean house) {
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
		
		JLabel priceLabel = new JLabel("Price");
		priceLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		priceLabel.setIcon(new ImageIcon(BrowseAds.class.getResource("/images/price.png")));
		priceLabel.setBounds(214, 11, 276, 32);
		adPanel.add(priceLabel);
		
		JLabel ownerLabel = new JLabel("Owner");
		ownerLabel.setIcon(new ImageIcon(BrowseAds.class.getResource("/images/owner.png")));
		ownerLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		ownerLabel.setBounds(218, 54, 270, 21);
		adPanel.add(ownerLabel);
		
		JLabel locationLabel = new JLabel("Location");
		locationLabel.setIcon(new ImageIcon(BrowseAds.class.getResource("/images/location.png")));
		locationLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		locationLabel.setBounds(220, 86, 270, 21);
		adPanel.add(locationLabel);
		
		JLabel bedroomLabel = new JLabel("Bedrooms");
		bedroomLabel.setIcon(new ImageIcon(BrowseAds.class.getResource("/images/bedroom.png")));
		bedroomLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		bedroomLabel.setBounds(224, 118, 100, 21);
		adPanel.add(bedroomLabel);
		
		JLabel bathroomLabel = new JLabel("Bathrooms");
		bathroomLabel.setIcon(new ImageIcon(BrowseAds.class.getResource("/images/bathroom.png")));
		bathroomLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		bathroomLabel.setBounds(390, 118, 100, 21);
		adPanel.add(bathroomLabel);
		
		JLabel storiesLabel = new JLabel("Stories");
		storiesLabel.setIcon(new ImageIcon(BrowseAds.class.getResource("/images/floors.png")));
		storiesLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		storiesLabel.setBounds(224, 150, 100, 21);
		adPanel.add(storiesLabel);
		
		JLabel areaLabel = new JLabel("Area");
		areaLabel.setIcon(new ImageIcon(BrowseAds.class.getResource("/images/area.png")));
		areaLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		areaLabel.setBounds(390, 150, 100, 21);
		adPanel.add(areaLabel);
		
		JButton btnNewButton = new JButton("Buy");
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnNewButton.setBounds(214, 196, 120, 23);
		adPanel.add(btnNewButton);
		
		JButton btnAddToFavorite = new JButton("Add to Favorite");
		btnAddToFavorite.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnAddToFavorite.setBounds(360, 196, 120, 23);
		adPanel.add(btnAddToFavorite);
		
		
		if(!house) {
			housePicture.setIcon(new ImageIcon(BrowseAds.class.getResource("/images/stock land.jpeg")));
			bedroomLabel.setVisible(false);
			bathroomLabel.setVisible(false);
			storiesLabel.setVisible(false);
			areaLabel.setBounds(224, 118, 100, 21);
		}
		
		return adPanel;
	}
}
