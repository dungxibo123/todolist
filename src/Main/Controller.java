package Main;


import Event.MyEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {

    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonList;
    @FXML
    private Pane leftPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorPane;

    public void AddEvent(ActionEvent event) {
        buttonAdd.setOnMouseClicked(e -> {
            buttonAdd.setCursor(Cursor.HAND);
            FXMLLoader grand = new FXMLLoader();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/AddEventWindow/AddPage.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Add Event");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });



    }



//Adding the event handler




}
