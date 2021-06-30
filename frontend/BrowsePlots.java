package frontend;

import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

@SuppressWarnings("serial")
public class BrowsePlots extends JPanel implements ActionListener {

	/**
	 * Create the panel.
	 */
	private static int pageNumber = 1, totalPages = 1, totalCount = 0;
	JLabel pageCount;
	static ArrayList<Ad> array = new ArrayList<Ad>();
	JButton next, prev;
	JButton buyButton;
	private JButton buttons[] = new JButton[4];

	public BrowsePlots() {

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
		next.setIcon(new ImageIcon(BrowseHouses.class.getResource("/images/next.png")));
		next.setBackground(null);
		next.setBounds(735, 30, 40, 40);
		add(next);

		prev = new JButton("");
		prev.addActionListener(this);
		prev.setIcon(new ImageIcon(BrowseHouses.class.getResource("/images/prev.png")));
		prev.setBackground(null);
		prev.setBounds(515, 30, 40, 40);
		add(prev);

		pageCount = new JLabel("");
		pageCount.setHorizontalAlignment(SwingConstants.CENTER);
		pageCount.setFont(new Font("SansSerif", Font.BOLD, 20));
		pageCount.setBounds(573, 72, 144, 40);
		add(pageCount);

		String query = "select advertisement_price, first_name, last_name, colony_name, location_name, city_name,"
				+ " province_name, plot_area, advertisement_id"
				+ " from advertisement natural join property natural join colony natural join location natural join"
				+ " city natural join province natural join plot natural join users";
		try {
			PreparedStatement houseAdvertisements = SqlConnection.connectToDatabase().prepareStatement(query);
			ResultSet result = SqlConnection.findResult(houseAdvertisements);
			array.removeAll(array);
			while (result.next()) {
				Ad curr = new Ad(result.getInt(9), result.getInt(1), result.getString(2) + " " + result.getString(3),
						result.getString(4) + ", " + result.getString(5) + ", " + result.getString(6) + ", "
								+ result.getString(7),
						result.getInt(8));
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

		titleLabel.setText(String.valueOf( "Page: " + pageNumber) + "/" + String.valueOf(totalPages));
		viewAds();
	}

	public JPanel createAdvertisement(int price, String owner, String location, int area) {
		JPanel adPanel = new JPanel();
		adPanel.setBackground(Color.WHITE);
		adPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		adPanel.setBounds(50, 138, 500, 230);
		this.add(adPanel);
		adPanel.setLayout(null);

		JLabel housePicture = new JLabel("");
		housePicture.setIcon(new ImageIcon(BrowseHouses.class.getResource("/images/stock land.jpeg")));
		housePicture.setBounds(10, 10, 194, 200);
		adPanel.add(housePicture);

		JLabel priceLabel = new JLabel(String.valueOf(price));
		priceLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		priceLabel.setIcon(new ImageIcon(BrowseHouses.class.getResource("/images/price.png")));
		priceLabel.setBounds(214, 11, 276, 32);
		adPanel.add(priceLabel);

		JLabel ownerLabel = new JLabel(owner);
		ownerLabel.setIcon(new ImageIcon(BrowseHouses.class.getResource("/images/owner.png")));
		ownerLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		ownerLabel.setBounds(218, 50, 270, 21);
		adPanel.add(ownerLabel);

		JLabel locationLabel = new JLabel("<html>" + location + "</html>");
		locationLabel.setIcon(new ImageIcon(BrowseHouses.class.getResource("/images/location.png")));
		locationLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		locationLabel.setBounds(220, 75, 270, 30);
		adPanel.add(locationLabel);

		JLabel areaLabel = new JLabel(String.valueOf(area) + " Marla");
		areaLabel.setIcon(new ImageIcon(BrowseHouses.class.getResource("/images/area.png")));
		areaLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		areaLabel.setBounds(224, 118, 100, 21);
		adPanel.add(areaLabel);

		return adPanel;
	}

	public void viewAds() {
		for (int i = 0; i < 4; i++) {
			int index = ((pageNumber - 1) * 4) + i;
			if (index < totalCount) {
				Ad curr = array.get(index);
				JPanel panel = createAdvertisement(curr.price, curr.owner, curr.location, curr.area);
				if (index % 4 == 0) {
					panel.setBounds(70, 138, 500, 230);
					createButton(0, panel);
				}
				if (index % 4 == 1) {
					panel.setBounds(700, 138, 500, 230);
					createButton(1, panel);
				}
				if (index % 4 == 2) {
					panel.setBounds(70, 380, 500, 230);
					createButton(2, panel);
				} else if (index % 4 == 3) {
					panel.setBounds(700, 380, 500, 230);
					createButton(3, panel);
				}
			} else {
				break;
			}
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
			System.out.println(array.get(index).id);
		} else if (e.getSource() == buttons[1]) {
			int index = (pageNumber - 1) * 4 + 1;
			System.out.println(array.get(index).id);
		} else if (e.getSource() == buttons[2]) {
			int index = (pageNumber - 1) * 4 + 2;
			System.out.println(array.get(index).id);
		} else if (e.getSource() == buttons[3]) {
			int index = (pageNumber - 1) * 4 + 3;
			System.out.println(array.get(index).id);
		} else {
			System.out.println(e.getID());
		}
	}


	public void createButton(int index, JPanel panel){
		buttons[index] = new JButton("Buy");
		buttons[index].addActionListener(this);
		buttons[index].setFont(new Font("SansSerif", Font.PLAIN, 10));
		buttons[index].setBounds(260, 196, 120, 23);
		panel.add(buttons[index]);
	}
}
