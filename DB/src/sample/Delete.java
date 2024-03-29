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


public class Delete implements Initializable
{
    @FXML TextField search_text_field;
    String User_Label;
    @FXML private ComboBox<String> choice_box;
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
    Customer to_be_del;
    Alert alert;
    private final ObservableList<Customer> data
            = FXCollections.observableArrayList();

    private ObservableList<String> choice = FXCollections.observableArrayList("Name","CNIC","Date","Bus","Route");
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


        choice_box.setItems(choice);

    }
    public void back(ActionEvent ae) throws IOException
    {
        ((Node) ae.getSource()).getScene().getWindow().hide();
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
        public void search_data(ActionEvent ae) throws SQLException
    {
        if(search_text_field.getText().equals("")||choice_box.getValue().equals(""))
        {
            alert = new Alert(Alert.AlertType.WARNING,"Please Enter Name/CNIC and Select Name/CNIC ", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        Connection connection = null;
        Statement statement = null;
        data.clear();
        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:Base.db");

            statement = connection.createStatement();
            if(choice_box.getValue().equals("Name"))
                statement.execute("select * from [Customer] INNER join [seats] using(cnic) where CusName like '"+search_text_field.getText()+"%'");
            else if(choice_box.getValue().equals("Bus"))
                statement.execute("select * from [Customer] INNER join [seats] using(cnic) where Customer.Bus like '"+search_text_field.getText()+"%'");
            else if(choice_box.getValue().equals("Route"))
                statement.execute("select * from [Customer] INNER join [seats] using(cnic) where Customer.Rout like '"+search_text_field.getText()+"%'");
            else if(choice_box.getValue().equals("Date"))
                statement.execute("select * from [Customer] INNER join [seats] using(cnic) where Customer.IssueDate like '"+search_text_field.getText()+"%'");
            else if(choice_box.getValue().equals("CNIC"))
                statement.execute("select * from [Customer] INNER join [seats] using(cnic) where Customer.cnic like '"+search_text_field.getText()+"%'");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next())
            {
                if(choice_box.getValue().equals("Name"))
                {
                    data.add(new Customer(resultSet.getString("CusName"),resultSet.getString("Cnic"),resultSet.getString("Rout"),resultSet.getString("Bus"),resultSet.getString("IssueDate"),resultSet.getString("seatNO"),resultSet.getString("time"),resultSet.getString("CusID")));                    user_found++;
                }
                else if(choice_box.getValue().equals("CNIC"))
                {
                    data.add(new Customer(resultSet.getString("CusName"),resultSet.getString("Cnic"),resultSet.getString("Rout"),resultSet.getString("Bus"),resultSet.getString("IssueDate"),resultSet.getString("seatNO"),resultSet.getString("time"),resultSet.getString("CusID")));                    user_found++;
                }
                else if(choice_box.getValue().equals("Bus"))
                {
                    data.add(new Customer(resultSet.getString("CusName"),resultSet.getString("Cnic"),resultSet.getString("Rout"),resultSet.getString("Bus"),resultSet.getString("IssueDate"),resultSet.getString("seatNO"),resultSet.getString("time"),resultSet.getString("CusID")));                    user_found++;
                }
                else if(choice_box.getValue().equals("Route"))
                {
                    data.add(new Customer(resultSet.getString("CusName"),resultSet.getString("Cnic"),resultSet.getString("Rout"),resultSet.getString("Bus"),resultSet.getString("IssueDate"),resultSet.getString("seatNO"),resultSet.getString("time"),resultSet.getString("CusID")));                    user_found++;
                }
                else if(choice_box.getValue().equals("Date"))
                {
                    data.add(new Customer(resultSet.getString("CusName"),resultSet.getString("Cnic"),resultSet.getString("Rout"),resultSet.getString("Bus"),resultSet.getString("IssueDate"),resultSet.getString("seatNO"),resultSet.getString("time"),resultSet.getString("CusID")));                    user_found++;
                }
            }
            if(user_found==0)
            {
                alert = new Alert(Alert.AlertType.WARNING,"NOT Found any One with "+search_text_field.getText(), ButtonType.OK);
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
                statement.close();
                connection.close();
            }
        }
    }
    public void click(javafx.scene.input.MouseEvent mouseEvent)
    {
        if(mouseEvent.getClickCount()==2)
        {
            Customer item = tableView.getFocusModel().getFocusedItem();
            this.to_be_del = item;
        }
    }
    public void delete()
    {
        if(to_be_del.getCnic()==null)
        {
            alert = new Alert(Alert.AlertType.WARNING,"Please Search and Then Select any Row to Delete", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        alert = new Alert(Alert.AlertType.WARNING,"Do You Really Want to Delete ", ButtonType.YES,ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.NO)
        {
            return;
        }

        alert = new Alert(Alert.AlertType.WARNING,"Do  Want to Delete All the Seats with "+to_be_del.getName(), ButtonType.YES,ButtonType.NO);
        alert.showAndWait();
        Connection connection = null;
        Statement statement = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:Base.db");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * FROM Seats WHERE Cnic = '"+to_be_del.getCnic()+"'");
            int count = 0;
            while (resultSet.next())
            {
                count++;
            }
            if(alert.getResult() == ButtonType.YES)
            {
                statement.execute("DELETE FROM Customer WHERE Cnic = '"+to_be_del.getCnic()+"'");
                statement.execute("DELETE FROM Seats WHERE Cnic = '"+to_be_del.getCnic()+"'");
            }
            else if(alert.getResult() == ButtonType.NO)
            {

                statement.execute("DELETE FROM Seats WHERE Cnic = '"+to_be_del.getCnic()+"' and [SeatNo] = '"+to_be_del.getSeat()+"'");
                if(count==1)
                {
                    statement.execute("DELETE FROM Customer WHERE Cnic = '"+to_be_del.getCnic()+"'");
                }
            }
            alert = new Alert(Alert.AlertType.WARNING,"User With  "+to_be_del.getCnic()+" CNIC , Name = "+to_be_del.getName()+" is Deleted", ButtonType.OK);
            alert.showAndWait();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}