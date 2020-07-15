package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class Prog_Bar implements Initializable
{
    int number = 0;
    @FXML ProgressBar progressBar;
    @FXML Label comletion_status;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        progressBar.setProgress(0);

    }
    public void load()
    {
        progressBar.setProgress(number/100.0);
        for (int i=0; i<100; i++)
        {
            try
            {
                Thread.sleep(100);
                progressBar.setProgress(number/100.0);
                number++;
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
