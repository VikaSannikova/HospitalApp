package sample.components;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ProfileAnchorPane extends AnchorPane {
    public ProfileAnchorPane() {
        Button button = new Button("личные данные");
        button.setMinSize(200,200);
        this.getChildren().add(button);
    }
}
