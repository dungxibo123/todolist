package AddEventWindow;


import Event.MyEvent;
import Main.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class AddEventController implements Initializable {
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    @FXML
    TableView<MyEvent> tableView;
    @FXML
    private TextField tfActivity;
    @FXML
    private DatePicker datePicker;

    @FXML
    public void addActivity(ActionEvent e) {
        addButton.setOnMouseClicked(ex -> {
            MyEvent event = new MyEvent(tfActivity.getText(),datePicker.getValue(),new Integer(tableView.getItems().size() + 1));
            System.out.println(event.getContent());
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Main/mainWindow.fxml"));
                MainController controller = loader.getController();
                tableView.getItems().add(event);

            } catch (Exception xe)
            {
                xe.printStackTrace();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    public void getTableView(TableView<MyEvent> tb)
    {
        tableView = tb;
    }

}
