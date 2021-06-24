package frontend;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class DeleteLocation extends JPanel implements ActionListener {

	JComboBox<String> selectType;
	private JTextField keywordField;
	private JTextField idField;
	private JScrollPane mainWindow;
	private JTextArea textArea;
	private JButton search;
	public DeleteLocation() {
		setLayout(null);
		
		JLabel mainLabel = new JLabel("Delete Locations");
		mainLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setBounds(10, 42, 1260, 30);
		add(mainLabel);
		
		String types[] = { "Province", "City", "Location", "Colony" };
		selectType = new JComboBox<String>();
		selectType.setSelectedIndex(3);
		selectType.addActionListener(this);
		selectType.setBounds(570, 150, 140, 24);
		add(selectType);

		JLabel locationType = new JLabel("Location Type");
		locationType.setFont(new Font("SansSerif", Font.BOLD, 14));
		locationType.setBounds(390, 150, 130, 20);
		add(locationType);
		
		JLabel keyword = new JLabel("Keyword:");
		keyword.setFont(new Font("SansSerif", Font.BOLD, 14));
		keyword.setBounds(390, 193, 130, 20);
		add(keyword);
		
		keywordField = new JTextField();
		keywordField.setBounds(570, 195, 140, 20);
		add(keywordField);
		keywordField.setColumns(10);
		
		JLabel locationID = new JLabel("Location ID:");
		locationID.setFont(new Font("SansSerif", Font.BOLD, 14));
		locationID.setBounds(390, 238, 130, 20);
		add(locationID);
		
		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(570, 240, 140, 20);
		add(idField);
		
		JButton delete = new JButton("Delete");
		delete.setBounds(790, 239, 100, 23);
		add(delete);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(390, 317, 500, 322);
		
		mainWindow = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainWindow.getVerticalScrollBar().setBackground(new Color(0x1E214A));
		mainWindow.getBackground().darker();
		mainWindow.setBounds(390, 317, 500, 322);
		add(mainWindow);
		
		search = new JButton("Search");
		search.setBounds(790, 194, 100, 23);
		add(search);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
