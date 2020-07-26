package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class Controller
{
    @FXML
    TextField user_name;
    @FXML
    PasswordField pass_word;
    @FXML Pane content_area;
    @FXML
    Button new_user_button;
    @FXML Button login_button;
    @FXML VBox vbox;

    Alert alert;
    int user_found = 0;




    @FXML
    public void new_user_button_method(ActionEvent event) throws IOException
    {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("new_user.fxml").openStream());

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        root.getStylesheets().add(getClass().getResource("Login.css").toString());
        primaryStage.show();

    }
    public void Login_button_method(ActionEvent event) throws SQLException
    {

        if(user_name.getText().equals("")||pass_word.getText().equals(""))
        {
            alert = new Alert(Alert.AlertType.ERROR,"Please Enter  User Name and Password  ", ButtonType.OK);
            alert.showAndWait();
        }
        else
        {
            Connection connection = null;
            Statement statement = null;
            try
            {
                connection = DriverManager.getConnection("jdbc:sqlite:/home/peaceseeker/DB_project/Base.db");
                //connection = DriverManager.getConnection("jdbc:sqlite:D:/CS IBA/Semester 4/DBMS/Project/Git_Prok/DB_project/Base.db");
                statement = connection.createStatement();
                statement.execute("Select * from [Admin]");
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next())
                {
                    if (user_name.getText().equalsIgnoreCase(resultSet.getString("id")) && pass_word.getText().equalsIgnoreCase(resultSet.getString("password")))
                    {

                        user_found++;

                        alert = new Alert(Alert.AlertType.INFORMATION,resultSet.getString("name")+" has Logged In ", ButtonType.OK);
                        alert.showAndWait();

                        ((Node) event.getSource()).getScene().getWindow().hide();
                        Stage primaryStage = new Stage();
                        FXMLLoader loader = new FXMLLoader();
                        Pane root = loader.load(getClass().getResource("Menu.fxml").openStream());
                        root.getStylesheets().add(getClass().getResource("dashboard.css").toString());

                        Dashboard dashboard = loader.getController();
                        dashboard.show(resultSet.getString("name"));

                        Scene scene = new Scene(root);
                        //scene.getStylesheets().add("/home/peaceseeker/DB_project/DB/src/sample/style.css");
                        primaryStage.setTitle("DashBoard");
                        primaryStage.setScene(scene);
                        primaryStage.show();

                    }
                }

                if(user_found==0)
                {
                    alert = new Alert(Alert.AlertType.ERROR,"Please Enter Correct User Name and Password  ", ButtonType.OK);
                    alert.showAndWait();
                }
            }
            catch (SQLException | IOException throwable)
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
    }
    public void Booking(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("booking.fxml").openStream());
        Scene scene = new Scene(root);
        primaryStage.setTitle("Booking");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}