package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller
{
    @FXML
    private Button btn;
    @FXML
    private TextField txt;
    public void initlize()
    {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                btn.setText(txt.getText());
            }
        });

    }
    public void set_attribute(ActionEvent ae)
    {
        btn.setText(txt.getText());

    }
}
