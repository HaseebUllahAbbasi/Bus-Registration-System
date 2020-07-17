package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.scene.control.*;


public class Insertion implements Initializable
{
    @FXML TextField name;
    @FXML TextField cnic;
    @FXML DatePicker date_id;
    @FXML ComboBox bus_box;
    @FXML Button back_butt;
    Alert alert;
    String User_Label;
    public void show(String user)
    {
        this.User_Label = user;
    }

    @FXML private ComboBox<String> rout_box;

    private ObservableList<String> cities = FXCollections.observableArrayList("Karachi","Peshawar","Multan");
    private ObservableList<String> buses = FXCollections.observableArrayList("Madrid Exp","City Exp","Bayern Exp");
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        rout_box.setItems(cities);
        bus_box.setItems(buses);
    }
    public String getCnic(){
        return cnic.getText();
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
    public void  insert(ActionEvent ae) throws SQLException
    {
        Connection connection = null;
        Statement statement = null;

        try
        {
            //connection = DriverManager.getConnection("jdbc:sqlite:/home/peaceseeker/DB_project/Base.db");

            connection = DriverManager.getConnection("jdbc:sqlite:D:/CS IBA/Semester 4/DBMS/Project/Git_Prok/DB_project/Base.db");
            statement = connection.createStatement();


            if(name.getText().equals("")||cnic.getText().equals("")||date_id.getValue()==null||bus_box.getValue()==null||rout_box.getValue()==null)
            {
                alert = new Alert(Alert.AlertType.ERROR,"Please Enter the All Required Data  !",ButtonType.OK);
                alert.showAndWait();
                return;
            }

            statement.execute("INSERT INTO Customer(CusName,Cnic,IssueDate,Rout,Bus) VALUES ('"+name.getText()+"',"+cnic.getText()+",'"+date_id.getValue()+"','"+bus_box.getValue()+"','"+rout_box.getValue()+"')");

            ((Node) ae.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("booking.fxml").openStream());

            Booking ob = loader.getController();
            ob.getVal(cnic.getText(), (String)bus_box.getValue());

            Scene scene = new Scene(root);
            primaryStage.setTitle("Booking Seats");
            primaryStage.setScene(scene);
            primaryStage.show();



        }
        catch (SQLException | IOException throwables)
        {

            alert = new Alert(Alert.AlertType.ERROR,"ERROR OCCURRED !",ButtonType.OK);
            alert.showAndWait();

            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
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
}
