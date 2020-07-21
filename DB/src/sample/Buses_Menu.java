package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.print.Book;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Buses_Menu
{
    @FXML DatePicker datePicker;
    @FXML Button city;
    @FXML Button madrid;
    @FXML Button bayern;
    @FXML Button juve;
    @FXML Button paris;
    @FXML Button barca;

    String User_Label;
    public void show(String user)
    {
        this.User_Label = user;
    }


    public void back(ActionEvent ae) throws IOException
    {
        ((Node)ae.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("Menu.fxml").openStream());

        Dashboard dashboard = loader.getController();
        dashboard.show(User_Label);

        Scene scene = new Scene(root);
        primaryStage.setTitle("DashBoard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static String capitalizeWord(String str){
        String words[]=str.split("\\s");
        String capitalizeWord="";
        for(String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            capitalizeWord+=first.toUpperCase()+afterfirst+" ";
        }
        return capitalizeWord.trim();
    }
    public void display(ActionEvent actionEvent) throws IOException {
        //System.out.println(((Button)actionEvent.getSource()).getText());
        String BusName=capitalizeWord(((Button)actionEvent.getSource()).getText().toLowerCase());
        ArrayList<Integer> SeatNoD = new ArrayList<Integer>();
        if(datePicker.getValue()==null)
        {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR,"Set a date first!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        else
        {
            Connection connection = null;
            Statement statement = null;

            try {

                System.out.println(BusName);
                //connection = DriverManager.getConnection("jdbc:sqlite:/home/peaceseeker/DB_project/Base.db");
                connection = DriverManager.getConnection("jdbc:sqlite:D:/CS IBA/Semester 4/DBMS/Project/Git_Prok/DB_project/Base.db");
                statement = connection.createStatement();
                statement.execute("SELECT * FROM [Seats] Where Bus='"+BusName+"' AND IssueDate = '"+datePicker.getValue()+"'");
                ResultSet resultSet = statement.getResultSet();
                while(resultSet.next())
                {
                    SeatNoD.add(Integer.parseInt(resultSet.getString("SeatNo")));
                }
                System.out.println(SeatNoD);

                ((Node)actionEvent.getSource()).getScene().getWindow().hide();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("view_buses.fxml").openStream());

                View_Buses ob = loader.getController();
                ob.getVal(SeatNoD);

                Scene scene = new Scene(root);
                primaryStage.setTitle("Bookings");
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (SQLException throwables) {
                System.out.println(throwables.getMessage());
                throwables.printStackTrace();
            }
        }
    }
    public void changed(MouseEvent mouseEvent) throws IOException
    {

    }
}
