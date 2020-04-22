package FixEventWindow;

import DatabaseConnector.DatabaseConnector;
import Event.MyEvent;
import Main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FixEventController implements Initializable {

    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField tfEvent;
    @FXML
    TableView<MyEvent> tvMyEvent;

    private MyEvent event;

    public void connectEvent(MyEvent e) {
        this.event = e;
    }
    public void connectTableView(TableView<MyEvent> t) {
        tvMyEvent = t;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void confirm(javafx.event.ActionEvent event) {
        confirmButton.setOnMouseClicked(ev -> {

            try {
                LocalDate da = datePicker.getValue();
                String co = tfEvent.getText();
                tvMyEvent.getSelectionModel().getSelectedItem().setContent(co);
                tvMyEvent.getSelectionModel().getSelectedItem().setDeadline(da);
                System.out.println(tvMyEvent.getSelectionModel().getSelectedItem().getId());
                fixEventFromDatabase(tvMyEvent.getSelectionModel().getSelectedItem().getId(), tvMyEvent.getSelectionModel().getSelectedItem().getContent() , tvMyEvent.getSelectionModel().getSelectedItem().getDeadlineString());
                //refresh after updating
                tvMyEvent.refresh();
            }
            catch (Exception e)
            {
                alert();
                return;
            }

            // Close Window
            ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        });
    }

    public void alert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Error with Event");
        alert.show();
    }


    public void cancel(javafx.event.ActionEvent event) {
        cancelButton.setOnMouseClicked(ev -> {
            ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        });
    }

    private void fixEventFromDatabase(String id, String content, String deadline) throws SQLException {
        Connection conn = DatabaseConnector.Connector();
        String query = "UPDATE ToDoList SET content = ?, "
                + "deadline = ? "
                + "WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, content);
        ps.setString(2, deadline);
        ps.setString(3, id);
        ps.executeUpdate();
        conn.close();
    }

}
