package frontend;

import backend.SqlConnection;
import backend.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddAgents extends AdminControlPanel{
    protected JLabel firstname, lastname, phone, email;
    protected JTextField firstnameField, lastnameField, phoneField, emailField;

    public AddAgents(){
        super();
        selectType.setVisible(false);
        locationType.setVisible(false);
        mainLabel.setText("Add Agent");
        colonyLabel.setVisible(false);
        newLocation.setVisible(false);
        updateLocation.setVisible(false);

        provinceLabel.setBounds(483, 120, 100, 20);
        selectProvince.setBounds(670, 120, 130, 24);

        cityLabel.setBounds(483, 170, 100, 20);
        selectCity.setBounds(670, 170, 130, 24);

        locationLabel.setBounds(483, 220, 100, 20);
        selectLocation.setBounds(670, 220, 130, 24);

        firstname = new JLabel("First Name: ");
        firstname.setBounds(483, 270, 130, 24);
        firstname.setFont(new Font("SansSerif", Font.BOLD, 14));
        contentPanel.add(firstname);

        firstnameField = new JTextField();
        firstnameField.setBackground(Color.WHITE);
        firstnameField.setBounds(670, 270, 130, 24);
        contentPanel.add(firstnameField);

        lastname = new JLabel("Last Name: ");
        lastname.setBounds(483, 320, 130, 24);
        lastname.setFont(new Font("SansSerif", Font.BOLD, 14));
        contentPanel.add(lastname);

        lastnameField = new JTextField();
        lastnameField.setBackground(Color.WHITE);
        lastnameField.setBounds(670, 320, 130, 24);
        contentPanel.add(lastnameField);

        phone = new JLabel("Phone Number: ");
        phone.setBounds(483, 370, 130, 24);
        phone.setFont(new Font("SansSerif", Font.BOLD, 14));
        contentPanel.add(phone);

        phoneField = new JTextField();
        phoneField.setBackground(Color.WHITE);
        phoneField.setBounds(670, 370, 130, 24);
        contentPanel.add(phoneField);

        email = new JLabel("Email: ");
        email.setBounds(483, 420, 130, 24);
        email.setFont(new Font("SansSerif", Font.BOLD, 14));
        contentPanel.add(email);

        emailField = new JTextField();
        emailField.setBackground(Color.WHITE);
        emailField.setBounds(670, 420, 130, 24);
        contentPanel.add(emailField);

        deleteLocation.setText("Delete Agents");

    }

    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == submitButton){
            if(selectCity.getSelectedItem() == null || selectLocation.getSelectedItem() == null || selectProvince.getSelectedItem() == null){
                JOptionPane.showMessageDialog(null, "Please select a valid location");
            }
            else {
                if (!(emailField.getText().isBlank() || phoneField.getText().isBlank() || firstnameField.getText().isBlank() || lastnameField.getText().isBlank())){
                    if(!Utilities.checkEmail(emailField.getText())){
                        JOptionPane.showMessageDialog(null, "Email format is invalid. Only Gmail mails are supported!");
                    }
                    else if(!Utilities.checkStringWithSpaces(firstnameField.getText())){
                        JOptionPane.showMessageDialog(null,"First name must include letters and spaces only!");
                    }
                    else if(!Utilities.checkStringWithSpaces(lastnameField.getText())){
                        JOptionPane.showMessageDialog(null,"Last name must include letters and spaces only!");
                    }
                    else if(!Utilities.checkNumber(phoneField.getText())){
                        JOptionPane.showMessageDialog(null,"Phone number must include digits only!");
                    }
                    else{
                        try {
                            PreparedStatement maxID = SqlConnection.connectToDatabase().prepareStatement("select max(agent_id) from agent");
                            int agent_id = 0;
                            ResultSet result = maxID.executeQuery();
                            while (result.next()){
                                agent_id = result.getInt(1);
                            }
                            PreparedStatement locationID = SqlConnection.connectToDatabase().prepareStatement("select location_id from location natural join" +
                                    " city natural join province where location_name = ? and city_name = ? and province_name = ?");
                            locationID.setString(1, selectLocation.getSelectedItem().toString());
                            locationID.setString(2, selectCity.getSelectedItem().toString());
                            locationID.setString(3, selectProvince.getSelectedItem().toString());

                            int locID = 0;
                            result = locationID.executeQuery();
                            while (result.next()){
                                locID = result.getInt(1);
                            }

                            PreparedStatement insertAgent = SqlConnection.connectToDatabase().prepareStatement("insert into agent values (?, ?, ?, ?, ?)");
                            insertAgent.setInt(1,++agent_id);
                            insertAgent.setString(2, firstnameField.getText());
                            insertAgent.setString(3, lastnameField.getText());
                            insertAgent.setString(4, phoneField.getText());
                            insertAgent.setString(5, emailField.getText());
                            insertAgent.executeUpdate();

                            PreparedStatement insertContains = SqlConnection.connectToDatabase().prepareStatement("insert into contains values (?, ?)");
                            insertContains.setInt(1, locID);
                            insertContains.setInt(2, agent_id);
                            insertContains.executeUpdate();

                            JOptionPane.showMessageDialog(null, "Agent added successfully");

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please fill all the data fields");
                }
            }
        }
        else if(e.getSource() == deleteLocation){
            AdminControlPanel.replaceContentPanel(new DeleteAgent());
        }
        else{
            super.mouseClicked(e);
        }
    }
}
