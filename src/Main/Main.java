package Main;

import Event.MyEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void init()
    {
        System.out.println("hello");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AddEventWindow/AddPage.fxml"));
            Scene scene = new Scene(root);
            scene.getRoot().applyCss();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
            primaryStage.setTitle("To-Do List");
            //primaryStage.setFullScreen(true);

            primaryStage.setScene(new Scene(root, primaryStage.getMinWidth(), primaryStage.getMinHeight()));
            primaryStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

        launch(args);
    }
}
