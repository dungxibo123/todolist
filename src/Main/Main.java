package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

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
