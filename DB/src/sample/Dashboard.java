package sample;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Dashboard
{
    @FXML
    Button sign_out;
    @FXML
    Label user_name;

    @FXML
    Button insert_button;
    @FXML
    Button edit_button;
    @FXML
    Button search_button;
    @FXML
    Button remove_button;
    @FXML
    Button view_history_button;
    @FXML
    Button total_button;
    public void show(String user)
    {
        user_name.setText(user);
    }
    public void signOut(ActionEvent event) throws IOException
    {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("Login.fxml").openStream());
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void insert(ActionEvent event) throws IOException
    {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("Menu_of_Vehicles.fxml").openStream());
        

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void search_data(ActionEvent event) throws IOException
    {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("search_vehicles.fxml").openStream());
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void remove_data(ActionEvent event) throws IOException
    {
        
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("remove_Vehicle.fxml").openStream());
        

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
    
    
}
