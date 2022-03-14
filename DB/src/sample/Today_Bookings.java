package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class Today_Bookings implements Initializable
{
    @FXML Button print;
    String User_Label;
    @FXML private TableView<Customer> tableView;
    @FXML private TableColumn<Customer,String> name;
    @FXML private TableColumn<Customer,String> cnic;
    @FXML private TableColumn<Customer,String> bus;
    @FXML private TableColumn<Customer,String> route;
    @FXML private TableColumn<Customer,String> date;
    @FXML private TableColumn<Customer,String> seat;
    @FXML private TableColumn<Customer,String> time;
    @FXML private TableColumn<Customer,String> id;

    int user_found = 0;
    Alert alert;
    private final ObservableList<Customer> data = FXCollections.observableArrayList();
    String data_to_print = "";
    public void show(String user)
    {
        this.User_Label = user;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        data_to_print += "ID \t Name \t CNIC \t Route \t Bus \t SeatNO \t Time\n";
        data_to_print += "*********************************************************\n";
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        route.setCellValueFactory(new PropertyValueFactory<>("route"));
        bus.setCellValueFactory(new PropertyValueFactory<>("bus"));
        cnic.setCellValueFactory(new PropertyValueFactory<>("cnic"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        seat.setCellValueFactory(new PropertyValueFactory<>("seat"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        Connection connection = null;
        Statement statement = null;
        data.clear();
        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:Base.db");
            statement = connection.createStatement();
            statement.execute("SELECT * from [Customer] INNER join [seats] using(cnic) where seats.IssueDate = '"+java.time.LocalDate.now()+"';");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next())
            {

                    data_to_print += ((resultSet.getString("CusId")+"\t"+resultSet.getString("CusName")+"\t"+ resultSet.getString("Cnic")+"\t"+ resultSet.getString("Rout")+"\t"+ resultSet.getString("Bus")+"\t"+ resultSet.getString("IssueDate")+"\t"+ resultSet.getString("seatNO")+"\t"+ resultSet.getString("time")+" " )+"\n");
                    data_to_print+="_________________________________________________________\n";
                    data.add(new Customer(resultSet.getString("CusName"), resultSet.getString("Cnic"), resultSet.getString("Rout"), resultSet.getString("Bus"), resultSet.getString("IssueDate"), resultSet.getString("seatNO"), resultSet.getString("time"), resultSet.getString("CusID")));
                    user_found++;
            }
            if(user_found==0)
            {
                alert = new Alert(Alert.AlertType.WARNING,"NOT Found any Result ", ButtonType.OK);
                alert.showAndWait();
            }
            else if(user_found>0)
            {
                tableView.setItems(data);
            }
        }
        catch (SQLException throwable)
        {
            throwable.printStackTrace();
        }
        finally
        {
            if (connection != null)
            {
                assert statement != null;
                try
                {
                    statement.close();
                    connection.close();
                }
                catch (SQLException sqlException)
                {
                    sqlException.printStackTrace();
                }
            }
        }
    }
    public void back(ActionEvent ae) throws IOException
    {
        ((Node)ae.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("Menu.fxml").openStream());
        root.getStylesheets().add(getClass().getResource("dashboard.css").toExternalForm());

        Dashboard dashboard = loader.getController();
        dashboard.show(User_Label);

        Scene scene = new Scene(root);
        primaryStage.setTitle("DashBoard");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public void print_data() throws PrinterException
    {
        System.out.println(data_to_print);
        JTextArea obj= new JTextArea(data_to_print);
        if(obj.print()!=true)
        {
            alert = new Alert(Alert.AlertType.ERROR,"ERROR OCCURRED !",ButtonType.OK);
            alert.showAndWait();
        }
        else {
            alert = new Alert(Alert.AlertType.INFORMATION,"Printed !",ButtonType.OK);
            alert.showAndWait();

        }
    }
}