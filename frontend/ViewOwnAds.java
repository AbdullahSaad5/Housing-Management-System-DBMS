package frontend;

import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

import backend.SqlConnection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class ViewOwnAds extends JPanel implements ActionListener {
    private static int pageNumber = 1, totalPages = 1, totalCount = 0;
    JLabel pageCount;
    static ArrayList<Ad> array = new ArrayList<>();
    JButton next, prev;
    private final JButton[] delete = new JButton[4];
    String query;
    JComboBox<String> selectType;   
    static int selectedIndex = 0;

    public ViewOwnAds() {

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


        JLabel typeLabel = new JLabel("Select Type");
        typeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        typeLabel.setBounds(520, 83, 130, 20);
        add(typeLabel);

        String types[] = {"Houses", "Plots"};
        selectType = new JComboBox<>(types);
        selectType.setSelectedIndex(selectedIndex);
        selectType.addActionListener(this);
        selectType.setBounds(640, 80, 130, 24);
        add(selectType);

        if(selectType.getSelectedIndex() == 0) {

            query = "select advertisement_price, first_name, last_name, colony_name, location_name, city_name,"
                    + " province_name, house_bedrooms, house_bathrooms, house_stories, house_area, advertisement_id"
                    + " from advertisement natural join property natural join colony natural join location natural join"
                    + " city natural join province natural join house natural join users where username = ? and " +
                    "advertisement_id in (select advertisement_id from advertisement minus select advertisement_id from selling_record)";
        }
        else if(selectType.getSelectedIndex() == 1) {

            query = "select advertisement_price, first_name, last_name, colony_name, location_name, city_name,"
                    + " province_name, plot_area, advertisement_id"
                    + " from advertisement natural join property natural join colony natural join location natural join"
                    + " city natural join province natural join plot natural join users where username = ? and " +
                    "advertisement_id in (select advertisement_id from advertisement minus select advertisement_id from selling_record)";
        }
        try {
            PreparedStatement houseAdvertisements = SqlConnection.connectToDatabase().prepareStatement(query);
            houseAdvertisements.setString(1, Login.currentUserID);
            ResultSet result = SqlConnection.findResult(houseAdvertisements);
            array.removeAll(array);
            while (result.next()) {
                Ad curr;
                if(selectType.getSelectedIndex() == 0) {
                     curr = new Ad(result.getInt(12), result.getInt(1), result.getString(2) + " " + result.getString(3),
                            result.getString(4) + ", " + result.getString(5) + ", " + result.getString(6) + ", "
                                    + result.getString(7),
                            result.getInt(8), result.getInt(9), result.getInt(10), result.getInt(11));
                }
                else{
                    curr = new Ad(result.getInt(9), result.getInt(1), result.getString(2) + " " + result.getString(3),
                            result.getString(4) + ", " + result.getString(5) + ", " + result.getString(6) + ", "
                                    + result.getString(7),
                            result.getInt(8));
                }
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

        titleLabel.setText( "Page: " + pageNumber + "/" + totalPages);
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
        housePicture.setIcon(new ImageIcon(Objects.requireNonNull(BrowseHouses.class.getResource("/images/stock land.jpeg"))));
        housePicture.setBounds(10, 10, 194, 200);
        adPanel.add(housePicture);

        JLabel priceLabel = new JLabel(String.valueOf(price));
        priceLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        priceLabel.setIcon(new ImageIcon(Objects.requireNonNull(BrowseHouses.class.getResource("/images/price.png"))));
        priceLabel.setBounds(214, 11, 276, 32);
        adPanel.add(priceLabel);

        JLabel ownerLabel = new JLabel(owner);
        ownerLabel.setIcon(new ImageIcon(Objects.requireNonNull(BrowseHouses.class.getResource("/images/owner.png"))));
        ownerLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        ownerLabel.setBounds(218, 50, 270, 21);
        adPanel.add(ownerLabel);

        JLabel locationLabel = new JLabel("<html>" + location + "</html>");
        locationLabel.setIcon(new ImageIcon(Objects.requireNonNull(BrowseHouses.class.getResource("/images/location.png"))));
        locationLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        locationLabel.setBounds(220, 75, 270, 30);
        adPanel.add(locationLabel);

        JLabel areaLabel = new JLabel(String.valueOf(area) + " Marla");
        areaLabel.setIcon(new ImageIcon(Objects.requireNonNull(BrowseHouses.class.getResource("/images/area.png"))));
        areaLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        areaLabel.setBounds(224, 118, 100, 21);
        adPanel.add(areaLabel);

        return adPanel;
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
                JPanel panel;
                if(selectType.getSelectedIndex() == 0) {
                    panel = createAdvertisement(curr.price, curr.owner, curr.location, curr.bedrooms, curr.bathrooms,
                            curr.stories, curr.area);
                }
                else{
                    panel = createAdvertisement(curr.price, curr.owner, curr.location, curr.area);
                }

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
                }
                else if (index % 4 == 3) {
                    panel.setBounds(700, 380, 500, 230);
                    createButton(3, panel);
                }
            } else {
                break;
            }
        }
    }

    public static void deleteAdvertisement(int advertisement_id){
        try {

            String addRecord = "delete from advertisement where advertisement_id = ?";

            PreparedStatement dltStmt = SqlConnection.connectToDatabase().prepareStatement(addRecord);
            dltStmt.setInt(1, advertisement_id);
            SqlConnection.alterResults(dltStmt);
            UserDashboard.replaceContentPanel(new ViewOwnAds());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == selectType){
            if(selectType.getSelectedIndex() == 0){
                selectedIndex = 0;
            }
            else{
                selectedIndex = 1;
            }
            UserDashboard.replaceContentPanel(new ViewOwnAds());
        }
        else if (e.getSource() == next || e.getSource() == prev) {
            if (e.getSource() == next && pageNumber < totalPages) {
                pageNumber++;
                removeAll();
                UserDashboard.replaceContentPanel(new ViewOwnAds());
                viewAds();
                pageCount.repaint();
            } else if (e.getSource() == prev && pageNumber > 1) {
                pageNumber--;
                removeAll();
                UserDashboard.replaceContentPanel(new ViewOwnAds());
                viewAds();
                pageCount.repaint();
            }

        } else if (e.getSource() == delete[0]) {
            int index = (pageNumber - 1) * 4;
            deleteAdvertisement(array.get(index).id);
        } else if (e.getSource() == delete[1]) {
            int index = (pageNumber - 1) * 4 + 1;
            deleteAdvertisement(array.get(index).id);
        } else if (e.getSource() == delete[2]) {
            int index = (pageNumber - 1) * 4 + 2;
            deleteAdvertisement(array.get(index).id);
        } else if (e.getSource() == delete[3]) {
            int index = (pageNumber - 1) * 4 + 3;
            deleteAdvertisement(array.get(index).id);
        } else {
            System.out.println(e.getID());
        }
    }

    public void createButton(int index, JPanel panel){
        delete[index] = new JButton("Delete");
        delete[index].addActionListener(this);
        delete[index].setFont(new Font("SansSerif", Font.PLAIN, 10));
        delete[index].setBounds(260, 196, 120, 23);
        panel.add(delete[index]);
    }
}
