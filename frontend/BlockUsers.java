package frontend;

import backend.SqlConnection;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class BlockUsers extends JPanel implements ActionListener {

	/**
	 * Create the panel.
	 */

	private JComboBox<String> selectType;
	private JButton[] block = new JButton[6];
	private static int pageNumber = 1, totalPages = 1, totalCount = 0;
	private JLabel pageCount;
	JButton next, prev;
	ArrayList<User> array = new ArrayList<User>();
	public BlockUsers() {
		setBackground(Color.WHITE);
		setLayout(null);

		next = new JButton("");
		next.addActionListener(this);
		next.setIcon(new ImageIcon(BrowsePlots.class.getResource("/images/next.png")));
		next.setBackground(null);
		next.setBounds(735, 30, 40, 40);
		add(next);

		prev = new JButton("");
		prev.addActionListener(this);
		prev.setIcon(new ImageIcon(BrowsePlots.class.getResource("/images/prev.png")));
		prev.setBackground(null);
		prev.setBounds(515, 30, 40, 40);
		add(prev);

		pageCount = new JLabel("");
		pageCount.setHorizontalAlignment(SwingConstants.CENTER);
		pageCount.setFont(new Font("SansSerif", Font.BOLD, 20));
		pageCount.setBounds(573, 72, 144, 40);
		add(pageCount);

		String types[] = { "Unblocked", "Blocked"};
		selectType = new JComboBox<>(types);
		selectType.setSelectedIndex(0);
		selectType.addActionListener(this);
		selectType.setBounds(670, 50, 130, 24);
		add(selectType);

		JLabel typeLabel = new JLabel("Select Type");
		typeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		typeLabel.setBounds(483, 50, 130, 20);
		add(typeLabel);


		String query = "select username, first_name, last_name, phone_number from account natural join users";
		try {
			PreparedStatement houseAdvertisements = SqlConnection.connectToDatabase().prepareStatement(query);
			ResultSet result = SqlConnection.findResult(houseAdvertisements);
			array.removeAll(array);
			while (result.next()) {
				User curr = new User(result.getString(1), result.getString(2) + " "
						+ result.getString(3), result.getString(4));
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

		viewUsers();



	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == next || e.getSource() == prev) {
			if (e.getSource() == next && pageNumber < totalPages) {
				pageNumber++;
				removeAll();
				UserDashboard.replaceContentPanel(new BrowseHouses());
				viewUsers();
				pageCount.repaint();
			} else if (e.getSource() == prev && pageNumber > 1) {
				pageNumber--;
				removeAll();
				UserDashboard.replaceContentPanel(new BrowseHouses());
				viewUsers();
				pageCount.repaint();
			}

		} else if (e.getSource() == block[0]) {
			int index = (pageNumber - 1) * 4;
			System.out.println(array.get(index).username);
		} else if (e.getSource() == block[1]) {
			int index = (pageNumber - 1) * 4 + 1;
			System.out.println(array.get(index).username);
		} else if (e.getSource() == block[2]) {
			int index = (pageNumber - 1) * 4 + 2;
			System.out.println(array.get(index).username);
		} else if (e.getSource() == block[3]) {
			int index = (pageNumber - 1) * 4 + 3;
			System.out.println(array.get(index).username);
		} else if (e.getSource() == block[4]) {
			int index = (pageNumber - 1) * 4 + 4;
			System.out.println(array.get(index).username);
		}else if (e.getSource() == block[5]) {
			int index = (pageNumber - 1) * 4 + 5;
			System.out.println(array.get(index).username);
		}else {
			System.out.println(e.getID());
		}
	}

	public JPanel createUserProfile(String username_, String name, String contact){
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(50, 153, 300, 150);
		add(panel);
		panel.setLayout(null);

		JLabel username = new JLabel(username_);
		username.setBounds(10, 15, 280, 14);
		panel.add(username);

		JLabel originalName = new JLabel(name);
		originalName.setBounds(10, 45, 280, 14);
		panel.add(originalName);

		JLabel contactNumber = new JLabel(contact);
		contactNumber.setBounds(10, 75, 280, 14);
		panel.add(contactNumber);

		return panel;
	}

	public void viewUsers() {
		for (int i = 0; i < 6; i++) {
			int index = ((pageNumber - 1) * 6) + i;
			if (index < totalCount) {
				User curr = array.get(index);
				JPanel panel = createUserProfile(curr.username, curr.name, curr.contact);
				if (index % 6 == 0) {
					panel.setBounds(70, 200, 300, 150);
					createButton(0, panel);
				}
				if (index % 6 == 1) {
					panel.setBounds(470, 200, 300, 150);
					createButton(1, panel);
				}
				if (index % 6 == 2) {
					panel.setBounds(870, 200, 300, 150);
					createButton(2, panel);
				}
				else if (index % 6 == 3) {
					panel.setBounds(70, 400, 300, 150);
					createButton(3, panel);
				}
				else if (index % 6 == 4) {
					panel.setBounds(470, 400, 300, 150);
					createButton(4, panel);
				}
				else if (index % 6 == 5) {
					panel.setBounds(870, 400, 300, 150);
					createButton(5, panel);
				}
			} else {
				break;
			}
		}
	}

	public void createButton(int index, JPanel panel){
		block[index] = new JButton("Buy");
		block[index].addActionListener(this);
		block[index].setBounds(100, 110, 100, 23);
		panel.add(block[index]);
	}
}
