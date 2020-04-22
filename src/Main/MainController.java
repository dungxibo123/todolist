package Main;


import AddEventWindow.AddEventController;
import DatabaseConnector.DatabaseConnector;
import Event.MyEvent;
import FixEventWindow.FixEventController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonList;

    @FXML
    private Button fixButton;
    @FXML
    private Button removeButton;
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



    public void fix(ActionEvent event) {
        fixButton.setOnMouseClicked(e -> {
            fixButton.setCursor(Cursor.HAND);
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FixEventWindow/FixPage.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                FixEventController controller = loader.getController();

                controller.connectTableView(tvEvent);
                stage.show();


            } catch (IOException xe) {
                xe.printStackTrace();
          }

        });
    }

    public void remove(ActionEvent event) throws SQLException{
        removeButton.setOnMouseClicked(e -> {
            try {
                removeAlert();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            tvEvent.refresh();
        });
    }

    public void connectTableView(TableView<MyEvent> t) {
        tvEvent = t;
    }



    private void alert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Error with Event");
        alert.show();
    }

    private void removeAlert() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType buttonConfirm = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonConfirm,buttonCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonConfirm) {
            removeEvent(tvEvent.getSelectionModel().getSelectedItem().getId());
            tvEvent.getItems().remove(tvEvent.getSelectionModel().getSelectedItem());

        }
        else {
            return;
        }
        return;
    }

    public ObservableList<MyEvent> DatabaseGetter() throws SQLException{
        Connection conn = DatabaseConnector.Connector();
        ObservableList<MyEvent> myEvents = FXCollections.observableArrayList();
        if(conn != null)
        {
            String query = "SELECT id, content, deadline FROM ToDoList";
            //PreparedStatement preparedStatement;
            ResultSet resultSet;
            //preparedStatement = conn.prepareStatement(query);
            //resultSet = preparedStatement.getResultSet();
            Statement  st = conn.createStatement();
            resultSet = st.executeQuery(query);
            ArrayList<String> arrayContent = new ArrayList<>();
            ArrayList<String> arrayDeadline = new ArrayList<>();
            ArrayList<String> arrayId = new ArrayList<>();
            while(resultSet.next()) {
                System.out.println(resultSet.getString("content") + "\n");
                arrayId.add(resultSet.getString("id"));
                arrayContent.add(resultSet.getString("content"));
                arrayDeadline.add(resultSet.getString("deadline"));
            }
            System.out.println(arrayContent.size());
            for (int i = 0; i < arrayContent.size(); i++) {
                MyEvent e = new MyEvent(arrayId.get(i),arrayContent.get(i), arrayDeadline.get(i));

                myEvents.add(e);
                System.out.println(myEvents.size());
            }
            conn.close();
            return myEvents;
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        leftPane.prefHeightProperty().bind(anchorPane.heightProperty());
        tvEvent.prefWidthProperty().bind(anchorPane.widthProperty());
        tvEvent.prefHeightProperty().bind(anchorPane.heightProperty());

        tableColumnDeadline.setCellValueFactory(new PropertyValueFactory<>("deadlineString"));
        tableColumnContent.setCellValueFactory(new PropertyValueFactory<>("content"));
        try {
            ObservableList<MyEvent> my = DatabaseGetter();
            this.tvEvent.setItems(my);
            this.tvEvent.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeEvent(String id) throws SQLException {
        Connection conn = DatabaseConnector.Connector();
        String query = "DELETE FROM ToDoList WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
        conn.close();
    }

}
