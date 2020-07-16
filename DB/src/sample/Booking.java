package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Booking implements Initializable {


    @FXML Button bkbtn;
    public ArrayList<String> SeatNo = new ArrayList<String>();
    public void toggle_1(ActionEvent event){
        ToggleButton toggl =(ToggleButton)event.getSource();
        boolean status=toggl.isSelected();
        if(status) {
            toggl.setStyle("-fx-background-color: Blue");
            SeatNo.add(toggl.getText());
        }
        else {
            toggl.setStyle(null);
            SeatNo.remove(toggl.getText());
        }
    }
    public void Bookbtn(ActionEvent event) throws SQLException, IOException {

        Connection connection = null;
        Statement statement = null;

        try
        {

            connection = DriverManager.getConnection("jdbc:sqlite:/home/peaceseeker/DB_project/Base.db");
            connection = DriverManager.getConnection("jdbc:sqlite:D:/CS IBA/Semester 4/DBMS/Project/Git_Prok/DB_project/Base.db");
            statement = connection.createStatement();
            Insertion ob = new Insertion();
            System.out.println(ob.CNIC);

            /*This statements needs to be improved, */
            for(int i =0 ; i<=SeatNo.size(); i++)
            {
                statement.execute("INSERT INTO seats (Cnic , SeatNo) VALUES ("+ob.cnic+","+Integer.parseInt(SeatNo.get(i).substring(6))+");");
            }

            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION,"Seats is Booked !",ButtonType.OK);
            alert.showAndWait();

        }catch (SQLException ax){
            System.out.println(ax.getMessage());
        }
        finally
        {
            if (connection!=null)
            {
                statement.close();
                connection.close();
            }
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
