package AddEventWindow;


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

import java.net.URL;
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
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("Main/mainWindow.fxml"));
                    MainController controller = loader.getController();
                    tableView.getItems().add(event);

                } catch (Exception xe)
                {
                    xe.printStackTrace();
                }
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

}
