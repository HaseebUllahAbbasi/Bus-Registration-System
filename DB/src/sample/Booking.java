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
    String cnicNum, busName;
    Alert alert;
    public void getVal(String user, String user2)
    {
        this.cnicNum=user;
        this.busName=user2;
    }

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
        }
    }
    public void Bookbtn(ActionEvent event) throws SQLException {

        Connection connection = null;
        Statement statement = null;

        try
        {

           // connection = DriverManager.getConnection("jdbc:sqlite:/home/peaceseeker/DB_project/Base.db");
            connection = DriverManager.getConnection("jdbc:sqlite:D:/CS IBA/Semester 4/DBMS/Project/Git_Prok/DB_project/Base.db");
            statement = connection.createStatement();

            /*This statements needs to be improved, */
            for(int i =0 ; i<SeatNo.size(); i++)
            {
                String str;
                str=SeatNo.get(i).substring(5).replaceAll("\\D+","");
                //System.out.println(Integer.parseInt(cnicNum)+"  "+);
                statement.execute("INSERT INTO seats (Cnic , SeatNo, Bus) VALUES ("+Integer.parseInt(cnicNum)+","+Integer.parseInt(str)+",'"+busName+"');");
            }


            alert = new Alert(Alert.AlertType.INFORMATION,"Seats are Booked !",ButtonType.OK);
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

    public void Back(ActionEvent event) throws IOException{
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("Insert.fxml").openStream());
        Scene scene = new Scene(root);
        primaryStage.setTitle("Register Customer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
