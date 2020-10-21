package sample.controllers;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Const;
import sample.Handlers.DatabaseHandler;
import sample.Patient;

import javax.print.DocFlavor;

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
    private PasswordField password_field;

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
                try {
                    loginUser(loginText, loginPassword);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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

    private void loginUser(String loginText, String loginPassword) throws SQLException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Patient patient = new Patient();
        patient.setUsername(loginText);
        patient.setPassword(loginPassword);
        ResultSet resultSet = databaseHandler.getPatient(patient);

        if (resultSet.next()) {
            // если несколько пользователей - то первый самый
            int id = resultSet.getInt(Const.PATIENT_ID);
            String fn = resultSet.getString(Const.PATIENT_FIRSTNAME);
            String ln = resultSet.getString(Const.PATIENT_LASTNAME);
            String login = resultSet.getString(Const.PATIENT_USERNAME);
            String password = resultSet.getString(Const.PATIENT_PASSWORD);
            String sex = resultSet.getString(Const.PATIENT_SEX);
            String dob = resultSet.getString(Const.PATIENT_DOB);
            String phone_number = resultSet.getString(Const.PATIENT_PHONENUMBER);
            String email = resultSet.getString(Const.PATIENT_EMAIL);
            Patient pat = new Patient(fn, ln, login, password, sex, dob, phone_number, email);
            System.out.println("Такой пользователь есть!");
            error_input_label.setTextFill(Color.color(1, 1, 1));
            openProfile(pat);
        } else {
            error_input_label.setTextFill(Color.color(0,0,0));
        }
    }

    private void openProfile(Patient patient){
        authSignInButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("profile.fxml")));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        // соединение с другой формой
        ProfileController profileController = loader.getController(); //получаем контроллер для второй формы
        profileController.setFields(patient); // передаем необходимые параметры
        stage.showAndWait();
    }
}

