package frontend;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class TestRunner {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(new Dimension(1280, 720));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 1280, 720);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frame.getContentPane().add(new BrowseAds());
		frame.setVisible(true);
	}
}
