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

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class Delete implements Initializable
{
    @FXML
    TextField search_text_field;
    String User_Label;
    @FXML
    private ComboBox<String> choice_box;
    @FXML private TableView<Customer> tableView;
    @FXML private TableColumn<Customer,String> name;
    @FXML private TableColumn<Customer,String> cnic;
    @FXML private TableColumn<Customer,String> bus;
    @FXML private TableColumn<Customer,String> route;
    int user_found = 0;
    Customer to_be_del;
    Alert alert;
    private final ObservableList<Customer> data
            = FXCollections.observableArrayList();

    private ObservableList<String> choice = FXCollections.observableArrayList("Name","CNIC");
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
        Connection connection = null;
        Statement statement = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:/home/peaceseeker/DB_project/Base.db");
            //connection = DriverManager.getConnection("jdbc:sqlite:/D:/CS IBA/Semester 4/DBMS/Project/Git_Prok/DB_project/Base.db");
            statement = connection.createStatement();

            statement.execute("DELETE FROM Customer WHERE Cnic = '"+to_be_del.getCnic()+"'");
            alert = new Alert(Alert.AlertType.WARNING,"User With  "+to_be_del.getCnic()+" CNINC is Deleted", ButtonType.OK);
            alert.showAndWait();

        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}