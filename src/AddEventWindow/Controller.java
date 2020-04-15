package AddEventWindow;

import Event.MyEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sun.misc.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class Controller {
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField tfActivity;

    @FXML
    public void addActivity(ActionEvent e) {
        addButton.setOnMouseClicked(ex -> {
            MyEvent event = new MyEvent(tfActivity.getText());
            System.out.println(event.getContent());
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





}
