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
    @FXML
    TextField name;
    @FXML
    TextField cnic;
    @FXML
    DatePicker date_id;
    @FXML
    ComboBox bus_box;

    @FXML
    Button back_butt;


    @FXML
    private ComboBox<String> rout_box;

    private ObservableList<String> dbTypeList = FXCollections.observableArrayList("Karachi","Peshawar","Multan");
    private ObservableList<String> dbTypeList2 = FXCollections.observableArrayList("Madrid Exp","City Exp","Bayern Exp");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rout_box.setItems(dbTypeList);
        bus_box.setItems(dbTypeList2);
    }
    public void back(ActionEvent ae) throws IOException
    {
        ((Node)ae.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("Menu.fxml").openStream());


        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void  insert(ActionEvent ae) throws SQLException
    {
        /* These are just useless comments added to the insertion class*/
        Connection connection = null;
        Statement statement = null;



        try
        {
            //connection = DriverManager.getConnection("jdbc:sqlite:/home/peaceseeker/DB_project/Base.db");
            connection = DriverManager.getConnection("jdbc:sqlite:D:/CS IBA/Semester 4/DBMS/Project/Git_Prok/DB_project/Base.db");
            statement = connection.createStatement();
            /*This statements needs to be improved, */
            statement.execute("INSERT INTO Customer(CusName,Cnic,IssueDate,Rout,Bus) VALUES ('"+name.getText()+"',"+cnic.getText()+",'"+date_id.getValue()+"','"+bus_box.getValue()+"','"+rout_box.getValue()+"')");
            System.out.println("Name : "+name.getText());
            System.out.println("Name : "+cnic.getText());
            System.out.println("Name : "+date_id.getValue());
            System.out.println("Name : "+bus_box.getValue());
            System.out.println("Name : "+rout_box.getValue());
            //ResultSet resultSet = statement.getResultSet();

        }
        catch (SQLException throwables)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"ERROR ACCURED!",ButtonType.OK);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                System.out.println("Dialogue box clicked!");
            }
            System.out.println(throwables.getMessage());
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
