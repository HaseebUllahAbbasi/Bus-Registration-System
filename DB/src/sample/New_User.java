package sample;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class New_User
{
    @FXML TextField name;
    @FXML TextField cnic;
    @FXML PasswordField passwordField;
    @FXML PasswordField passwordField2;
    @FXML Alert alert;
    @FXML
    FontAwesomeIcon login_screen;
    @FXML Button new_user;

    @FXML
    private void back_to_login(MouseEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("Login.fxml").openStream());
        root.getStylesheets().add(getClass().getResource("Login.css").toString());
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void register_method()
    {
        //if fields are empty then user is alerted
        if (name.getText().equals("") || name.getText().equals("") || passwordField.getText().equals("")|| passwordField2.getText().equals(""))
        {
            alert = new Alert(Alert.AlertType.WARNING, "Please Enter the All Required Data !", ButtonType.OK);
            alert.showAndWait();
        }
        //if password length is less then 5 then alert
        else if (passwordField.getText().length() < 5)
        {
            alert = new Alert(Alert.AlertType.WARNING, "Please Enter with more than four letters !", ButtonType.OK);
            alert.showAndWait();
        }
        else if(passwordField.getText()!=passwordField2.getText())
        {
            alert = new Alert(Alert.AlertType.WARNING, "Please Enter same password !", ButtonType.OK);
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

                statement.execute("INSERT INTO Admin(Password,CNIC,Name) VALUES ('" + passwordField.getText() + "'," + cnic.getText() + ",'" + name.getText() + "')");

                alert = new Alert(Alert.AlertType.INFORMATION, "New User " +name.getText()+" is Added !", ButtonType.OK);
                alert.showAndWait();
            } catch (SQLException sqlException)
            {
                alert = new Alert(Alert.AlertType.ERROR, "Error Occurred in Entering New User !", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }
}
