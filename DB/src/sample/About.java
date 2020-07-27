package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class About
{
    private String user_label;

    public void show(String user_label)
    {
        this.user_label = user_label;
    }
    public void back(ActionEvent ae) throws IOException
    {
        ((Node)ae.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("Menu.fxml").openStream());
        root.getStylesheets().add(getClass().getResource("dashboard.css").toExternalForm());

        Dashboard dashboard = loader.getController();
        dashboard.show(user_label);

        Scene scene = new Scene(root);
        primaryStage.setTitle("DashBoard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
