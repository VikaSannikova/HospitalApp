package sample.components;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class DoctorsAnchorPane extends AnchorPane {
    public DoctorsAnchorPane() {
        Button button = new Button("Доктора");
        this.getChildren().add(button);
    }
}
