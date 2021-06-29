package frontend;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class ViewRecords extends JPanel {

	private JTextArea textArea;
	private JScrollPane mainWindow;
	private JTextField keywordField;
	public ViewRecords() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("View Selling Records");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 31, 1260, 30);
		add(lblNewLabel);


		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(100, 160, 1080, 400);

		mainWindow = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainWindow.getVerticalScrollBar().setBackground(new Color(0x1E214A));
		mainWindow.getBackground().darker();
		mainWindow.setBounds(100, 160, 1080, 400);
		add(mainWindow);

		JLabel keyword = new JLabel("Keyword:");
		keyword.setHorizontalAlignment(SwingConstants.TRAILING);
		keyword.setFont(new Font("SansSerif", Font.BOLD, 14));
		keyword.setBounds(491, 110, 100, 20);
		add(keyword);

		keywordField = new JTextField();
		keywordField.setBounds(624, 110, 133, 20);
		add(keywordField);
		keywordField.setColumns(10);

	}

}