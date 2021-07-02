package frontend;

import backend.SqlConnection;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class ViewRecords extends JPanel implements ActionListener {

	protected JTextArea textArea;
	protected JScrollPane mainWindow;
	protected JTextField keywordField;
	protected JButton search;
	JLabel mainLabel, keyword;
	String query;
	public ViewRecords() {
		setLayout(null);

		mainLabel = new JLabel("View Selling Records");
		mainLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setBounds(10, 31, 1260, 30);
		add(mainLabel);


		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(100, 160, 1080, 400);

		mainWindow = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainWindow.getVerticalScrollBar().setBackground(new Color(0x1E214A));
		mainWindow.getBackground().darker();
		mainWindow.setBounds(100, 160, 1080, 400);
		add(mainWindow);

		query = "select record_id, advertisement_id, record_date, username, first_name, last_name, CNIC from selling_record natural join users order by record_id, advertisement_id";
		writeRecords();

		keyword = new JLabel("Username:");
		keyword.setHorizontalAlignment(SwingConstants.TRAILING);
		keyword.setFont(new Font("SansSerif", Font.BOLD, 14));
		keyword.setBounds(491, 110, 100, 20);
		add(keyword);

		keywordField = new JTextField();
		keywordField.setBounds(624, 110, 133, 20);
		add(keywordField);
		keywordField.setColumns(10);

		search = new JButton("Search");
		search.addActionListener(this);
		search.setBounds(780, 106, 100, 23);
		add(search);

		setVisible(true);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(!keywordField.getText().isBlank()){
			query = "select record_id, advertisement_id, record_date, username, first_name, last_name, CNIC from selling_record natural join users where username like '%" + keywordField.getText() + "%' order by record_id, advertisement_id";
		}
		else{
			query = "select record_id, advertisement_id, record_date, username, first_name, last_name, CNIC from selling_record natural join users order by record_id, advertisement_id";
		}
			writeRecords();
	}

	public void writeRecords(){
		try {
			textArea.setText("\tRecord ID\tAd ID\tBuy Date\tUsername\tBuyer Name\tCNIC\n\n");
			PreparedStatement records = SqlConnection.connectToDatabase().prepareStatement(query);
			ResultSet result = SqlConnection.findResult(records);
			while (result.next()){
				textArea.append("\t" + result.getInt(1) +"\t " + result.getInt(2) +"\t" + result.getDate(3) +"\t" + result.getString(4) + "\t" + result.getString(5) + " " + result.getString(6) +"\t" + result.getString(7) + "\n");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}