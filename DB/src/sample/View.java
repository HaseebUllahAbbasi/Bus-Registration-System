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

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class View implements Initializable
{
    @FXML TextField search_text_field;
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

    public void show(String user)
    {
        this.User_Label = user;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
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
            connection = DriverManager.getConnection("jdbc:sqlite:/home/peaceseeker/DB_project/Base.db");
            //connection = DriverManager.getConnection("jdbc:sqlite:/D:/CS IBA/Semester 4/DBMS/Project/Git_Prok/DB_project/Base.db");
            statement = connection.createStatement();
            statement.execute("SELECT * from [Customer] INNER join [seats] using(cnic)");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next())
            {
                data.add(new Customer(resultSet.getString("CusName"),resultSet.getString("Cnic"),resultSet.getString("Rout"),resultSet.getString("Bus"),resultSet.getString("IssueDate"),resultSet.getString("seatNO"),resultSet.getString("time"),resultSet.getString("CusID")));
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
}