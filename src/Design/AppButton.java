package Design;

import javafx.scene.control.Button;



public class AppButton extends Button {
    public AppButton() {
        getStylesheets().add(getClass().getResource("Main.css").toExternalForm());
        getStyleClass().add("button");
    }
}