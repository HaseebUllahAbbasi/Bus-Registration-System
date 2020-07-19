package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Buses_Menu
{
    @FXML DatePicker datePicker;
    @FXML Button city;
    @FXML Button madrid;
    @FXML Button bayern;
    @FXML Button juve;
    @FXML Button paris;
    @FXML Button barca;

    String User_Label;
    public void show(String user)
    {
        this.User_Label = user;
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
    public void display(ActionEvent actionEvent) throws IOException {
        System.out.println(((Button)actionEvent.getSource()).getText());
        if(datePicker.getValue()!=null)
            System.out.println(datePicker.getValue());


        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("view_buses.fxml").openStream());

        View_Buses view_buses = loader.getController();
        view_buses.show(User_Label);

        Scene scene = new Scene(root);
        primaryStage.setTitle("DashBoard");
        primaryStage.setScene(scene);
        primaryStage.show();



    }
    public void changed(MouseEvent mouseEvent) throws IOException
    {

    }
}
