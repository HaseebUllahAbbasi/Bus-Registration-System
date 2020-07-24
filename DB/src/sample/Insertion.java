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

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.*;


public class Insertion implements Initializable
{
    @FXML TextField name;
    @FXML TextField cnic;
    @FXML DatePicker date_id;
    @FXML ComboBox bus_box;
    @FXML Button back_butt;
    @FXML ComboBox time;
    Alert alert;
    String User_Label;
    public void show(String user)
    {
        this.User_Label = user;
    }

    @FXML private ComboBox<String> rout_box;

    private ObservableList<String> cities = FXCollections.observableArrayList("KHR - SUK","LHR - ISL","MUL - KHR","SUK - ISL","ISL - SUK","KHR - MUL");
    private ObservableList<String> buses = FXCollections.observableArrayList("Madrid Exp","City Exp","Bayern Exp","Juventus Exp","Paris Exp","Barca Exp");
    private ObservableList<String> timeList = FXCollections.observableArrayList("05:00 PM","11:00 PM","09:00 PM");
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        rout_box.setItems(cities);
        bus_box.setItems(buses);
        time.setItems(timeList);
    }
    public String getCnic(){
        return cnic.getText();
    }

    public void  insert(ActionEvent ae) throws SQLException
    {
        Connection connection = null;
        Statement statement = null;
        ArrayList<Integer> SeatNoD = new ArrayList<Integer>();
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

            if (date_id.getValue().isBefore(java.time.LocalDate.now()))
            {
                alert = new Alert(Alert.AlertType.ERROR,"Please Enter current date or after  !",ButtonType.OK);
                alert.showAndWait();
                return;
            }

            statement.execute("INSERT INTO Customer(CusName,Cnic,IssueDate,Rout,Bus) VALUES ('"+name.getText()+"',"+cnic.getText()+",'"+date_id.getValue()+"','"+rout_box.getValue()+"','"+bus_box.getValue()+"')");
            statement.execute("SELECT * FROM [Seats] Where Bus='"+(String) bus_box.getValue()+"' AND IssueDate = '"+date_id.getValue()+"' AND Time='"+time.getValue()+"'");
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next())
            {
                SeatNoD.add(Integer.parseInt(resultSet.getString("SeatNo")));
            }

            ((Node) ae.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("booking.fxml").openStream());

            Booking ob = loader.getController();
            ob.getVal(cnic.getText(), (String)bus_box.getValue(),SeatNoD,date_id.getValue(), (String) time.getValue());

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
    public void check_route(ActionEvent actionEvent)
    {
        if(rout_box.getValue()!=null)
        {

            if(rout_box.getValue().equals("KHR - SUK"))
                bus_box.setValue("Madrid Exp");
            else if(rout_box.getValue().equals("LHR - ISL"))
                bus_box.setValue("City Exp");
            else if(rout_box.getValue().equals("MUL - KHR"))
                bus_box.setValue("Bayern Exp");
            else if(rout_box.getValue().equals("SUK - ISL"))
                bus_box.setValue("Juventus Exp");
            else if(rout_box.getValue().equals("ISL - SUK"))
                bus_box.setValue("Paris Exp");
            else if(rout_box.getValue().equals("KHR - MUL"))
                bus_box.setValue("Barca Exp");
        }
    }

    public void check_bus(ActionEvent actionEvent)
    {
        if(bus_box.getValue()!=null)
        {
            if(bus_box.getValue().equals("Madrid Exp"))
                rout_box.setValue("KHR - SUK");
            else if(bus_box.getValue().equals("City Exp"))
                rout_box.setValue("LHR - ISL");
            else if(bus_box.getValue().equals("Bayern Exp"))
                rout_box.setValue("MUL - KHR");
            else if(bus_box.getValue().equals("Juventus Exp"))
                rout_box.setValue("SUK - ISL");
            else if(bus_box.getValue().equals("Paris Exp"))
                rout_box.setValue("ISL - SUK");
            else if(bus_box.getValue().equals("Barca Exp"))
                rout_box.setValue("KHR - MUL");
        }
    }
}
