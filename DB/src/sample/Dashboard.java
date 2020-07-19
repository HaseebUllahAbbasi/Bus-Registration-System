package sample;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Dashboard
{
    @FXML Button sign_out;
    @FXML Label user_name;
    @FXML Button insert_button;
    @FXML Button edit_button;
    @FXML Button search_button;
    @FXML Button remove_button;
    @FXML Button view_history_button;
    @FXML Button total_button;
    Alert alert;
    String User_Label;
    public void show(String user)
    {
        this.User_Label = user;
        user_name.setText(user);
    }
    public void signOut(ActionEvent event) throws IOException
    {
        alert = new Alert(Alert.AlertType.CONFIRMATION," Are you really want to Logout !", ButtonType.YES,ButtonType.NO);
        alert.showAndWait();
        // confirmation for the long out
        if(alert.getResult() == ButtonType.YES)
        {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("Login.fxml").openStream());
            Scene scene = new Scene(root);

            primaryStage.setTitle("Login Screen");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
    public void insert(ActionEvent event) throws IOException
    {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("Insert.fxml").openStream());
        
        Insertion insertion = loader.getController();
        insertion.show(User_Label);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Seat Booking");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void search_data(ActionEvent event) throws IOException
    {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("search.fxml").openStream());

        Search search = loader.getController();
        search.show(User_Label);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Search Record");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void remove_data(ActionEvent event) throws IOException
    {
        
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("delete.fxml").openStream());

        Delete delete = loader.getController();
        delete.show(User_Label);
        Scene scene = new Scene(root);

        primaryStage.setTitle("Delete Record");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void update_date(ActionEvent event) throws IOException
    {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("update.fxml").openStream());

        Update update = loader.getController();
        update.show(User_Label);
        Scene scene = new Scene(root);

        primaryStage.setTitle("Update Record");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void view_data(ActionEvent event) throws IOException
    {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("view.fxml").openStream());

        View view = loader.getController();
        view.show(User_Label);
        Scene scene = new Scene(root);

        primaryStage.setTitle("All Records");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void bus_menu(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("buses_menu.fxml").openStream());

        Buses_Menu buses_menu = loader.getController();
        buses_menu.show(User_Label);

        Scene scene = new Scene(root);
        primaryStage.setTitle("All Records");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}