package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Update implements Initializable
{
    @FXML TextField name_field;
    @FXML TextField cnic_field;
    @FXML TextField search_text_field;
    String User_Label;
    @FXML PieChart pieChart;
    @FXML private ComboBox<String> choice_box;
    @FXML private TableView<Customer> tableView;
    @FXML private TableColumn<Customer,String> name;
    @FXML private TableColumn<Customer,String> cnic;
    @FXML private TableColumn<Customer,String> bus;
    @FXML private TableColumn<Customer,String> route;
    @FXML private TableColumn<Customer,String> date;
    @FXML private TableColumn<Customer,String> seat;
    @FXML private TableColumn<Customer,String> time;
    int user_found = 0;
    Customer to_be_updated;
    Alert alert;
    private final ObservableList<Customer> data = FXCollections.observableArrayList();

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


        choice_box.setItems(choice);
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
            connection = DriverManager.getConnection("jdbc:sqlite:/home/peaceseeker/DB_project/Base.db");
            //connection = DriverManager.getConnection("jdbc:sqlite:/D:/CS IBA/Semester 4/DBMS/Project/Git_Prok/DB_project/Base.db");
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
                    data.add(new Customer(resultSet.getString("CusName"),resultSet.getString("Cnic"),resultSet.getString("Rout"),resultSet.getString("Bus"),resultSet.getString("IssueDate"),resultSet.getString("seatNO"),resultSet.getString("time")));
                    user_found++;
                }
                else if(choice_box.getValue().equals("CNIC"))
                {
                    data.add(new Customer(resultSet.getString("CusName"),resultSet.getString("Cnic"),resultSet.getString("Rout"),resultSet.getString("Bus"),resultSet.getString("IssueDate"),resultSet.getString("seatNO"),resultSet.getString("time")));
                    user_found++;
                }
                else if(choice_box.getValue().equals("Bus"))
                {
                    data.add(new Customer(resultSet.getString("CusName"),resultSet.getString("Cnic"),resultSet.getString("Rout"),resultSet.getString("Bus"),resultSet.getString("IssueDate"),resultSet.getString("seatNO"),resultSet.getString("time")));
                    user_found++;
                }
                else if(choice_box.getValue().equals("Route"))
                {
                    data.add(new Customer(resultSet.getString("CusName"),resultSet.getString("Cnic"),resultSet.getString("Rout"),resultSet.getString("Bus"),resultSet.getString("IssueDate"),resultSet.getString("seatNO"),resultSet.getString("time")));
                    user_found++;
                }
                else if(choice_box.getValue().equals("Date"))
                {
                    data.add(new Customer(resultSet.getString("CusName"),resultSet.getString("Cnic"),resultSet.getString("Rout"),resultSet.getString("Bus"),resultSet.getString("IssueDate"),resultSet.getString("seatNO"),resultSet.getString("time")));
                    user_found++;
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
    public void update()
    {
        if(to_be_updated.getCnic()==null || name_field.getText().equals("") || cnic_field.getText().equals(""))
        {
            alert = new Alert(Alert.AlertType.WARNING,"Please Search and Then Write new Changes to Update", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        alert = new Alert(Alert.AlertType.WARNING,"Do You Really Want to Update ", ButtonType.YES,ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.NO)
        {
            return;
        }
        Connection connection = null;
        Statement statement = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:/home/peaceseeker/DB_project/Base.db");
            //connection = DriverManager.getConnection("jdbc:sqlite:/D:/CS IBA/Semester 4/DBMS/Project/Git_Prok/DB_project/Base.db");
            statement = connection.createStatement();

            statement.execute("UPDATE Customer\n" +
                    "SET CusName = '"+ name_field.getText()+"', Cnic = '"+ cnic_field.getText()+"' \n" +
                    "WHERE Cnic = "+ to_be_updated.getCnic()+";");

            alert = new Alert(Alert.AlertType.WARNING,"User With  "+ to_be_updated.getCnic()+" CNINC is Updated", ButtonType.OK);
            alert.showAndWait();

        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    public void click(javafx.scene.input.MouseEvent mouseEvent)
    {
        if(mouseEvent.getClickCount()==2)
        {
            Customer item = tableView.getFocusModel().getFocusedItem();
            this.to_be_updated = item;
            cnic_field.setText(to_be_updated.getCnic());
            name_field.setText(to_be_updated.getName());

        }
    }
}