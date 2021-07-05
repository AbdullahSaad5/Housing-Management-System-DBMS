package frontend;

import backend.SqlConnection;

import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAgents extends ViewRecords {
    public ViewAgents() {
        mainLabel.setText("View Agents");
        query = "select agent_firstname, agent_lastname, agent_contact, agent_email, location_name, city_name, province_name" +
                " from agent natural join contains natural join location natural join city natural join province";
        writeRecords();
        keyword.setText("Location:");
        revalidate();
        repaint();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(!keywordField.getText().isBlank()){
            query = "select agent_firstname, agent_lastname, agent_contact, agent_email, location_name, city_name, province_name" +
                    " from agent natural join contains natural join location natural join city natural join province where location_name like '%" + keywordField.getText() +"%'";
        }
        else {
            query = "select agent_firstname, agent_lastname, agent_contact, agent_email, location_name, city_name, province_name" +
                    " from agent natural join contains natural join location natural join city natural join province";
        }
        writeRecords();
    }

    public void writeRecords(){
        try {
            textArea.setText("Agent Name\tContact\tEmail\tLocation Supervised\n\n");
            PreparedStatement records = SqlConnection.connectToDatabase().prepareStatement(query);
            ResultSet result = SqlConnection.findResult(records);
            while (result.next()){
                textArea.append(result.getString(1) +" " + result.getString(2) +"\t" + result.getString(3) +"\t" + result.getString(4) + "\t" + result.getString(5) + ", " + result.getString(6) +", " + result.getString(7) + "\n");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
