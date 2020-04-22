package AddEventWindow;


import DatabaseConnector.DatabaseConnector;
import Event.MyEvent;
import Main.MainController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class AddEventController implements Initializable {
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    @FXML
    TableView<MyEvent> tableView;
    @FXML
    private TextField tfEvent;
    @FXML
    private DatePicker datePicker;

    @FXML
    public void addActivity(ActionEvent e) {
        addButton.setOnMouseClicked(ex -> {
            try {
                String eventContent = tfEvent.getText();
                LocalDate eventDate = datePicker.getValue();
                MyEvent event = new MyEvent(eventContent,eventDate);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Main/mainWindow.fxml"));
                MainController controller = loader.getController();
                tableView.getItems().add(event);
                addEventIntoDatabase(event);
            } catch (Exception ae)
            {
                alert();
                return;
            }

            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.close();
        });
    }



    @FXML
    public void cancel(ActionEvent e)
    {
        cancelButton.setOnMouseClicked(dungdeptrai -> {
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.close();
        });
    }

    public void alert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Error with Event");
        alert.show();
    }
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    public void getTableView(TableView<MyEvent> tb)
    {
        tableView = tb;
    }
    public void addEventIntoDatabase  (MyEvent event) throws SQLException {
        Connection conn = DatabaseConnector.Connector();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO ToDoList (id,content,deadline) VALUES (?,?,?)");
        ps.setString(1,event.getId());
        ps.setString(2, event.getContent());
        ps.setString(3, event.getDeadlineString());
        ps.executeUpdate();
        conn.close();
    }
}
