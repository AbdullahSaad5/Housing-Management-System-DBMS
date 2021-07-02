package frontend;

import backend.SqlConnection;
import backend.Utilities;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteAgent extends DeleteLocation{
    String query;
    public DeleteAgent(){
        mainLabel.setText("Delete Agents");
        selectType.setVisible(false);
        locationType.setVisible(false);

        keyword.setText("Location: ");
        keyword.setBounds(390, 150, 130, 20);
        keywordField.setBounds(570, 150, 140, 20);
        search.setBounds(790, 150, 100, 23);


        locationID.setText("Agent ID: ");
        locationID.setBounds(390, 200, 130, 20);
        idField.setBounds(570, 200, 140, 20);

        delete.setBounds(790, 200, 100, 23);

        query = "select agent_id, agent_firstname, agent_lastname, agent_contact, agent_email, location_name, city_name, province_name" +
                " from agent natural join contains natural join location natural join city natural join province";
        writeRecords();

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == delete){
            if(!idField.getText().isBlank()){
                if(Utilities.checkNumber(idField.getText())){
                    try {
                        PreparedStatement checkID = SqlConnection.connectToDatabase().prepareStatement("select agent_id from agent where agent_id = ?");
                        checkID.setInt(1, Integer.parseInt(idField.getText()));
                        int total = SqlConnection.alterResults(checkID);
                        if(total == 0){
                            JOptionPane.showMessageDialog(null, "Invalid ID!");
                        }
                        else {
                            PreparedStatement deleteAgent = SqlConnection.connectToDatabase().prepareStatement("delete from contains where agent_id = ?");
                            deleteAgent.setInt(1, Integer.parseInt(idField.getText()));
                            deleteAgent.executeUpdate();

                            deleteAgent = SqlConnection.connectToDatabase().prepareStatement("delete from agent where agent_id = ?");
                            deleteAgent.setInt(1, Integer.parseInt(idField.getText()));
                            deleteAgent.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Agent Deleted");
                            writeRecords();
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                }
            }else{
                JOptionPane.showMessageDialog(null, "Fill the ID Field first");
            }
        }
        else if(e.getSource() == search){
            if(keywordField.getText().isBlank()){
                query = "select agent_id, agent_firstname, agent_lastname, agent_contact, agent_email, location_name, city_name, province_name" +
                        " from agent natural join contains natural join location natural join city natural join province";
            }
            else{
                query = "select agent_id, agent_firstname, agent_lastname, agent_contact, agent_email, location_name, city_name, province_name" +
                        " from agent natural join contains natural join location natural join city natural join province where location_name like '%" + keywordField.getText() +"%'";
            }
            writeRecords();
        }
    }
    public void writeRecords(){
        try {
            textArea.setText("ID\tAgent Name\tContact\tEmail\tLocation Supervised\n\n");
            PreparedStatement records = SqlConnection.connectToDatabase().prepareStatement(query);
            ResultSet result = SqlConnection.findResult(records);
            while (result.next()){
                textArea.append(result.getInt(1) + "\t" + result.getString(2) +" " + result.getString(3) +"\t" + result.getString(4) +"\t" + result.getString(5) + "\t" + result.getString(6) + ", " + result.getString(7) +", " + result.getString(8) + "\n");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
