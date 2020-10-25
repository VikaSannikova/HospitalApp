package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Handlers.DatabaseHandler;
import sample.Patient;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class SignUpController {

    @FXML
    private Button exit_button;

    @FXML
    private TextField su_first_name;

    @FXML
    private TextField su_last_name;

    @FXML
    private Button su_button;

    @FXML
    private TextField su_phone_number;

    @FXML
    private TextField su_dob;

    @FXML
    private TextField su_email;

    @FXML
    private RadioButton su_male;

    @FXML
    private RadioButton su_female;

    @FXML
    private TextField su_user_name;

    @FXML
    private TextField su_password;

    @FXML
    void initialize() {

        su_button.setOnAction(actionEvent -> {
            signUpNewUser();
        });
        exit_button.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("sample.fxml")));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage curstage = (Stage) exit_button.getScene().getWindow();
            curstage.setScene(new Scene(root));
        });
    }

    private void openProfile(Patient patient){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("profile.fxml")));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage curstage = (Stage) su_button.getScene().getWindow();
        curstage.setScene(new Scene(root));
        ProfileController profileController = loader.getController(); //получаем контроллер для второй формы
        profileController.setPatient(patient);
        profileController.setFields(patient);
        try {
            profileController.setDoctorsList();// передаем необходимые параметры
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void signUpNewUser() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String firstname = su_first_name.getText();
        String lastname = su_last_name.getText();
        String username = su_user_name.getText();
        String password = su_password.getText();
        String sex = "";
        String dob = "2000-01-01";
        String phone_number = su_phone_number.getText();
        String email = su_email.getText();

        if (su_male.isSelected()) {
            sex = "м";
        } else {
            sex = "ж";
        }

        String oldDateString = su_dob.getText();
        SimpleDateFormat oldDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = null;
        try {
            date = oldDateFormat.parse(oldDateString);
            dob = newDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Patient patient = new Patient(firstname, lastname, username, password, sex, dob, phone_number, email);

        databaseHandler.signUpUser(patient);
        openProfile(patient);
    }

}
