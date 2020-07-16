package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Booking implements Initializable {

    @FXML
    ToggleButton toggle1;

    public void toggle_1(ActionEvent event){
        boolean status = toggle1.isSelected();
        if(status)
        {
            toggle1.setText("ON");
        }
        else {
            toggle1.setText("ON");
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
