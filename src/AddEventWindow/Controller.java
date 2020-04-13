package AddEventWindow;

import Event.MyEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


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
            try {
                System.out.println("added");
                Parent root = FXMLLoader.load(getClass().getResource("/Main/mainWindow.fxml"));
                if (tfActivity.getText().length() == 0) {
                    return;
                }
                MyEvent event = new MyEvent(tfActivity.getText());
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                scene.getRoot().applyCss();

                AnchorPane anchpane = (AnchorPane) scene.lookup(".main");
                ScrollPane scrpane = (ScrollPane) anchpane.lookup(".scrpane");
                if(scrpane == null)
                {
                    System.out.println("khong co gia tri tra ve");
                    return;
                }

                TextArea eve = new TextArea(event.getContent());

                try {
                    scrpane.getChildrenUnmodifiable().add((Node)eve);
                }
                catch (Exception le)
                {
                    le.printStackTrace();
                }

                stage.setScene(scene);

                Stage curStage = (Stage)((Node) e.getSource()).getScene().getWindow();
                stage.close();
                curStage.close();
            } catch (IOException exc) {
                exc.printStackTrace();
            }



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
