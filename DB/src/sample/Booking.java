package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Preloader;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;


public class Booking implements Initializable{


    @FXML Button bkbtn;
    @FXML ToggleButton t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20;
    ArrayList<Integer> SeatNoD = new ArrayList<Integer>();
    String cnicNum, busName, Time;
    LocalDate IssueDate;
    Alert alert;
    String User_Label;
    public void show(String user)
    {
        this.User_Label = user;
    }

    public void getVal(String user, String user2, ArrayList<Integer> Arr, LocalDate date, String time)
    {
        this.cnicNum=user;
        this.busName=user2;
        this.SeatNoD=Arr;
        this.IssueDate=date;
        this.Time=time;
        Dred();
        Dis();
    }
    public void getVal(ArrayList<Integer> Arr,String Bus)
    {
        this.busName=Bus;
        this.SeatNoD=Arr;
        bkbtn.setDisable(true);
        Dred();
        Dis();
    }



    public ArrayList<String> SeatNo = new ArrayList<String>();
    public void toggle_1(ActionEvent event){
        ToggleButton toggl =(ToggleButton)event.getSource();
        boolean status=toggl.isSelected();
        if(status) {
            toggl.setStyle("-fx-background-color: Blue");
            SeatNo.add(toggl.getText());
        }
        else {
            toggl.setStyle(null);
        }
    }
    public void Bookbtn(ActionEvent event) throws SQLException {

        Connection connection = null;
        Statement statement = null;

        try
        {

            connection = DriverManager.getConnection("jdbc:sqlite:/home/peaceseeker/DB_project/Base.db");
            //connection = DriverManager.getConnection("jdbc:sqlite:D:/CS IBA/Semester 4/DBMS/Project/Git_Prok/DB_project/Base.db");
            statement = connection.createStatement();

            /*This statements needs to be improved, */
            for(int i =0 ; i<SeatNo.size(); i++)
            {
                String str;
                str=SeatNo.get(i).substring(5).replaceAll("\\D+","");
                //System.out.println(Integer.parseInt(cnicNum)+"  "+);
                statement.execute("INSERT INTO seats (Cnic , SeatNo, Bus, IssueDate, Time) VALUES ("+Integer.parseInt(cnicNum)+","+Integer.parseInt(str)+",'"+busName+"', '"+IssueDate+"','"+Time+"');");
            }


            alert = new Alert(Alert.AlertType.INFORMATION,"Seats are Booked !",ButtonType.OK);
            alert.showAndWait();
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("Menu.fxml").openStream());


            Scene scene = new Scene(root);
            primaryStage.setTitle("Menu Screen");
            primaryStage.setScene(scene);
            primaryStage.show();


        }catch (SQLException | IOException ax){
            System.out.println(ax.getMessage());
        }
        finally
        {
            if (connection!=null)
            {
                statement.close();
                connection.close();
            }
        }

    }

    public void Back(ActionEvent event) throws IOException{
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("Insert.fxml").openStream());
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    //t3.setDisable(true);
    //Dis();
    }
}
