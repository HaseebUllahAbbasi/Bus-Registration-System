package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class View_Buses
{
    @FXML
    Button bkbtn;
    @FXML
    ToggleButton t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20;
    ArrayList<Integer> SeatNoD = new ArrayList<Integer>();
    String cnicNum, busName;
    LocalDate IssueDate;
    private String user_label;
    private String date;

    public void getVal(String user, String user2, ArrayList<Integer> Arr, LocalDate date)
    {
        this.cnicNum=user;
        this.busName=user2;
        this.SeatNoD=Arr;
        this.IssueDate=date;
        Dred();
        Dis();
    }


    public ArrayList<String> SeatNo = new ArrayList<String>();
    public void Back(ActionEvent event) throws IOException
    {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("buses_menu.fxml").openStream());

        Buses_Menu buses_menu = loader.getController();
        buses_menu.show(user_label);
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("Register Customer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void Dis()
    {
        for(int i=0 ; i<SeatNoD.size(); i++)
        {
            if(SeatNoD.get(i)==1) t1.setDisable(true);
            if(SeatNoD.get(i)==2) t2.setDisable(true);
            if(SeatNoD.get(i)==3) t3.setDisable(true);
            if(SeatNoD.get(i)==4) t4.setDisable(true);
            if(SeatNoD.get(i)==5) t5.setDisable(true);
            if(SeatNoD.get(i)==6) t6.setDisable(true);
            if(SeatNoD.get(i)==7) t7.setDisable(true);
            if(SeatNoD.get(i)==8) t8.setDisable(true);
            if(SeatNoD.get(i)==9) t9.setDisable(true);
            if(SeatNoD.get(i)==10) t10.setDisable(true);
            if(SeatNoD.get(i)==11) t11.setDisable(true);
            if(SeatNoD.get(i)==12) t12.setDisable(true);
            if(SeatNoD.get(i)==13) t13.setDisable(true);
            if(SeatNoD.get(i)==14) t14.setDisable(true);
            if(SeatNoD.get(i)==15) t15.setDisable(true);
            if(SeatNoD.get(i)==16) t16.setDisable(true);
            if(SeatNoD.get(i)==17) t17.setDisable(true);
            if(SeatNoD.get(i)==18) t18.setDisable(true);
            if(SeatNoD.get(i)==19) t19.setDisable(true);
            if(SeatNoD.get(i)==20) t20.setDisable(true);
            // System.out.println(SeatNoD.get(i));
        }
    }
    public void Dred()
    {
        for(int i=0 ; i<SeatNoD.size(); i++)
        {
            if(SeatNoD.get(i)==1) t1.setStyle("-fx-background-color: Red; -fx-text-fill: White");
            if(SeatNoD.get(i)==2) t2.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            if(SeatNoD.get(i)==3) t3.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            if(SeatNoD.get(i)==4) t4.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            if(SeatNoD.get(i)==5) t5.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            if(SeatNoD.get(i)==6) t6.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            if(SeatNoD.get(i)==7) t7.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            if(SeatNoD.get(i)==8) t8.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            if(SeatNoD.get(i)==9) t9.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            if(SeatNoD.get(i)==10) t10.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            if(SeatNoD.get(i)==11) t11.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            if(SeatNoD.get(i)==12) t12.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            if(SeatNoD.get(i)==13) t13.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            if(SeatNoD.get(i)==14) t14.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            if(SeatNoD.get(i)==15) t15.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            if(SeatNoD.get(i)==16) t16.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            if(SeatNoD.get(i)==17) t17.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            if(SeatNoD.get(i)==18) t18.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            if(SeatNoD.get(i)==19) t19.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            if(SeatNoD.get(i)==20) t20.setStyle("-fx-background-color: Red;-fx-text-fill: White");
            // System.out.println(SeatNoD.get(i));
        }
    }
    public void set_date(String date)
    {
        this.date = date;
    }
    public void show(String user_label) 
    {
        this.user_label = user_label;
    }
}
