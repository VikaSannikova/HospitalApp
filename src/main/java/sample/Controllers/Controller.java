package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Handlers.DatabaseHandler;
import sample.Patient;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private TextField password_field;

    @FXML
    private Button authSignInButton;

    @FXML
    private Label error_input_label;

    @FXML
    void initialize() {
        authSignInButton.setOnAction(actionEvent -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();
            if(!loginText.equals("")&&!loginPassword.equals("")){
                loginUser(loginText, loginPassword);
            } else {
                System.out.println("EMPTY LOGIN & PASSWORD");
            }
        });

       loginSignUpButton.setOnAction(actionEvent -> {
           loginSignUpButton.getScene().getWindow().hide();
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("signUp.fxml")));
           try {
               loader.load();
           } catch (IOException e) {
               e.printStackTrace();
           }
           Parent root = loader.getRoot();
           Stage stage = new Stage();
           stage.setScene(new Scene(root));
           stage.showAndWait();
       });
    }

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Patient patient = new Patient();
        patient.setUsername(loginText);
        patient.setPassword(loginPassword);
        ResultSet resultSet = databaseHandler.getPatient(patient);

        int counter = 0;
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            counter++;
        }
        if(counter >= 1){
            System.out.println("Такой пользователь есть!");
            error_input_label.setTextFill(Color.color(1,1,1));
        } else {
            error_input_label.setTextFill(Color.color(0,0,0));
        }
    }
}

