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

public class Update implements Initializable
{
    @FXML TextField name_field;
    @FXML TextField cnic_field;
    @FXML ComboBox<String> buses;
    @FXML ComboBox<String> routes;
    @FXML TextField search_text_field;
    String User_Label;
    @FXML private ComboBox<String> choice_box;
    @FXML private TableView<Customer> tableView;
    @FXML private TableColumn<Customer,String> name;
    @FXML private TableColumn<Customer,String> cnic;
    @FXML private TableColumn<Customer,String> bus;
    @FXML private TableColumn<Customer,String> route;
    int user_found = 0;
    Customer to_be_updated;
    Alert alert;
    private final ObservableList<Customer> data
            = FXCollections.observableArrayList();

    private ObservableList<String> choice = FXCollections.observableArrayList("Name","CNIC");
    private ObservableList<String> cities = FXCollections.observableArrayList("Karachi","Peshawar","Multan");
    private ObservableList<String> bus_list = FXCollections.observableArrayList("Madrid Exp","City Exp","Bayern Exp");

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
        choice_box.setItems(choice);
        buses.setItems(bus_list);
        routes.setItems(cities);
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
                primaryStage.setScene(scene);
                primaryStage.show();
    }
    public void search(ActionEvent ae) throws SQLException
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

            statement.execute("Select * from [Customer]");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next())
            {
                // IF Name is Selected
                if(choice_box.getValue().equals("Name"))
                {
                    if (search_text_field.getText().equalsIgnoreCase(resultSet.getString("CusName")))
                    {
                        data.add(new Customer(resultSet.getString("CusName"),resultSet.getString("Cnic"),resultSet.getString("Rout"),resultSet.getString("Bus")));
                        user_found++;
                    }
                }
                // if CNIC is selected
                else if(choice_box.getValue().equals("CNIC"))
                {
                    if (search_text_field.getText().equalsIgnoreCase(resultSet.getString("Cnic")))
                    {
                        data.add(new Customer(resultSet.getString("CusName"),resultSet.getString("Cnic"),resultSet.getString("Rout"),resultSet.getString("Bus")));
                        user_found++;
                    }
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
  //  public void click(MouseEvent event)
    //{


    //}
    public void update()
    {
        if(to_be_updated.getCnic()==null)
        {
            alert = new Alert(Alert.AlertType.WARNING,"Please Search and Then Select any Row to Update", ButtonType.OK);
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
                    "SET CusName = '"+ name_field.getText()+"', Cnic = '"+ cnic_field.getText()+"', Rout = '"+ routes.getValue()+"', Bus = '"+ buses.getValue()+"' \n" +
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
            System.out.println(to_be_updated);
            cnic_field.setText(to_be_updated.getCnic());
            name_field.setText(to_be_updated.getName());
        }
    }
}