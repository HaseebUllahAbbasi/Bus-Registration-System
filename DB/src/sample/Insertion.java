package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ResourceBundle;


public class Insertion implements Initializable
{
    @FXML
    TextField name;
    @FXML
    TextField cnic;
    @FXML
    DatePicker date_id;
    @FXML
    ComboBox bus_box;

    @FXML
    private ComboBox<String> rout_box;

    private ObservableList<String> dbTypeList = FXCollections.observableArrayList("Karachi","Peshawar","Multan");
    private ObservableList<String> dbTypeList2 = FXCollections.observableArrayList("Madrid Exp","City Exp","Bayern Exp");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rout_box.setItems(dbTypeList);
        bus_box.setItems(dbTypeList2);
    }
    public void  insert()
    {
        /* These are just useless comments added to the insertion class*/

    }
}
