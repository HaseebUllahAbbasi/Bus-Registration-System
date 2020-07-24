package sample;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Dashboard implements Initializable {
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

    @FXML
    private LineChart<?, ?> LineChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML ImageView img;
    @FXML Image img_src;

    Alert alert;
    String User_Label;
    @FXML Label earned;
    @FXML Label total_buses;
    @FXML Label totol_booking;
    @FXML PieChart pieChart;


    public void show(String user) {
        this.User_Label = user;
        user_name.setText(user);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        int sum = 0;
        int count = 0;
        Connection connection = null;
        Statement statement = null;
        int city = 0;
        int juventus = 0;
        int madrid = 0;
        int paris = 0;
        int barca = 0;
        int bayern = 0;
        try {
            //connection = DriverManager.getConnection("jdbc:sqlite:/home/peaceseeker/DB_project/Base.db");
            connection = DriverManager.getConnection("jdbc:sqlite:D:/CS IBA/Semester 4/DBMS/Project/Git_Prok/DB_project/Base.db");
            statement = connection.createStatement();
            statement.execute("Select * from [Seats] where IssueDate = '"+java.time.LocalDate.now()+"';");
            //statement.execute("Select * from [Seats]");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next())
            {
                switch (resultSet.getString("Bus"))
                {
                    case "Madrid Exp":
                        madrid++;
                        break;
                    case "City Exp":
                        city++;
                        break;
                    case "Barca Exp":
                        barca++;
                        break;
                    case "Bayern Exp":
                        bayern++;
                        break;
                    case "Juventus Exp":
                        juventus++;
                        break;
                    case "Paris Exp":
                        paris++;
                        break;
                }
                count++;
                sum+=Integer.parseInt(resultSet.getString("price"));
            }
            // System.out.println("total seats booked are "+count);
            //System.out.println("total booking earning is "+sum);
            totol_booking.setText(Integer.toString(count));
            earned.setText(Integer.toString(sum));

            ObservableList<PieChart.Data> pie_chart_data = FXCollections.observableArrayList(
                    new PieChart.Data("City EXP",city), new PieChart.Data("Madrid EXP",madrid),
                    new PieChart.Data("Juventus EXP",juventus),
                    new PieChart.Data("Bayern EXP",bayern),
                    new PieChart.Data("Paris EXP",paris),
                    new PieChart.Data("Barca EXP",barca));
            pieChart.setData(pie_chart_data);

            for (final PieChart.Data data : pieChart.getData())
            {
                int finalCount = count;
                data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                        new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent e) {
                                //System.out.println(String.valueOf(data.getPieValue()) + "%");
                                pieChart.setTitle(data.getName()+" : "+Math.round(data.getPieValue()/ finalCount*100)+"%");
                            }
                        });
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        finally {
            if(connection!=null)
            {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException sqlException)
                {
                    sqlException.printStackTrace();
                }
            }
        }
       XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Juventus Exp",juventus*100));
        series.getData().add(new XYChart.Data("City Exp",city*100));
        series.getData().add(new XYChart.Data("Bayern Exp",bayern*100));
        series.getData().add(new XYChart.Data("Paris Exp",paris*100));
        series.getData().add(new XYChart.Data("Barcelona Exp",barca*100));
        series.getData().add(new XYChart.Data( "Madrid Exp",madrid*100));
        LineChart.getData().addAll(series);
    }


    public void signOut(ActionEvent event) throws IOException {
        alert = new Alert(Alert.AlertType.CONFIRMATION, " Are you really want to Logout !", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        // confirmation for the long out
        if (alert.getResult() == ButtonType.YES) {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("Login.fxml").openStream());
            Scene scene = new Scene(root);

            primaryStage.setTitle("Login Screen");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    public void insert(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
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

    public void search_data(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
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

    public void remove_data(ActionEvent event) throws IOException {

        ((Node) event.getSource()).getScene().getWindow().hide();
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

    public void update_date(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
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

    public void view_data(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
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
        ((Node) event.getSource()).getScene().getWindow().hide();
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
    public void base_button(MouseEvent mouseEvent)
    {
        ((Button)mouseEvent.getSource()).setStyle("-fx-background-color: lightgreen");
    }
    public void changed_button(MouseEvent mouseEvent)
    {
        ((Button)mouseEvent.getSource()).setStyle("-fx-background-color: lightblue");
    }
    public void base_info(MouseEvent mouseEvent)
    {
        ((VBox)mouseEvent.getSource()).setStyle("-fx-background-color:  #bd77be");
    }
    public void changed_info(MouseEvent mouseEvent)
    {
        ((VBox)mouseEvent.getSource()).setStyle("-fx-background-color: pink");
    }
}