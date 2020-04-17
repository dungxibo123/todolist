package Main;


import AddEventWindow.AddEventController;
import Event.MyEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonList;
    @FXML
    private Pane leftPane;
    @FXML
    private TableView<MyEvent> tvEvent;
    @FXML
    private TableColumn<MyEvent,Integer> tableColumnNumber;
    @FXML
    private TableColumn<MyEvent,String> tableColumnContent;
    @FXML
    private TableColumn<MyEvent,String> tableColumnDeadline;


    @FXML
    private AnchorPane anchorPane;


    public void AddEvent(ActionEvent event) {
        buttonAdd.setOnMouseClicked(e -> {
            buttonAdd.setCursor(Cursor.HAND);
            FXMLLoader grand = new FXMLLoader();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddEventWindow/AddPage.fxml"));
            Parent root = null;
            try {
                root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Add Event");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);

                AddEventController controller = loader.getController();

                controller.getTableView(this.tvEvent);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        leftPane.prefHeightProperty().bind(anchorPane.heightProperty());
        tvEvent.prefWidthProperty().bind(anchorPane.widthProperty());
        tvEvent.prefHeightProperty().bind(anchorPane.heightProperty());

        tableColumnDeadline.setCellValueFactory(new PropertyValueFactory<>("deadlineString"));
        tableColumnContent.setCellValueFactory(new PropertyValueFactory<>("content"));
        tableColumnNumber.setCellValueFactory(new PropertyValueFactory<>("id"));

    }


    //setTable
    public TableView<MyEvent> getTableView()
    {
        return tvEvent;
    }




}
