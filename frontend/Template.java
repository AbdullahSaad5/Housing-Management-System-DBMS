package frontend;

import java.awt.*;
import javax.swing.*;

public class Template {

	public static JFrame mainFrame;
	private static JPanel currentPanel;
	
	public Template() {
		
		mainFrame = new JFrame();
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(Template.class.getResource("/images/house.png")));
		mainFrame.setTitle("Housing Management System");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(0, 0, 470, 700);
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
				
		
		currentPanel = new Login();
		mainFrame.getContentPane().add(currentPanel);
		mainFrame.setVisible(true);
		
		
	}
	
	public static void changePanel(JPanel panel) {
		mainFrame.getContentPane().remove(currentPanel);	
		currentPanel = panel;
		mainFrame.getContentPane().add(currentPanel);
		mainFrame.repaint();
		mainFrame.revalidate();	
	}
	
	public static void main(String[] args) {
		new Template();
	}

}
