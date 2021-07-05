package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Filters implements ActionListener {
    public static int selectedOption = 0;
    JComboBox<String> selection;
    JButton confirm;
    public static boolean browseHouses = true;
    JFrame mainFrame;

    public Filters(){

        mainFrame = new JFrame();
        mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(Template.class.getResource("/images/house.png")));
        mainFrame.setTitle("Filters");
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.setBounds(0, 0, 400, 200);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.getContentPane().setLayout(null);

        JLabel label = new JLabel("Filters ");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 18));
        label.setBounds(150, 20, 100, 20);
        mainFrame.getContentPane().add(label);

        confirm = new JButton("Confirm");
        confirm.setBounds(150, 120, 100, 20);
        confirm.addActionListener(this);
        mainFrame.getContentPane().add(confirm);
        String[] options;
        if(browseHouses) {
            options = new String[]{"No Filters", "Ascending Price", "Descending Price", "Most Bedrooms + Bathrooms", "Least Bedrooms + Bathrooms",
                    "Most Area", "Least Area"};
        }
        else{
            if(selectedOption > 4){
                selectedOption = 0;
            }
            options = new String[]{"No Filters", "Ascending Price", "Descending Price", "Most Area", "Least Area"};
        }
        selection = new JComboBox<>(options);
        selection.setSelectedIndex(selectedOption);
        selection.setBounds(50, 70, 300, 30);
        mainFrame.getContentPane().add(selection);

        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirm){
            if(selection.getSelectedItem() != null) {
                selectedOption = selection.getSelectedIndex();
                if (browseHouses) {
                    UserDashboard.replaceContentPanel(new BrowseHouses());
                }else{
                    UserDashboard.replaceContentPanel(new BrowsePlots());
                }
                mainFrame.dispose();
            }
        }
    }
}
