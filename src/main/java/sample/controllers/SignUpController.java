package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label exist_label;

    @FXML
    void initialize() {

        su_button.setOnAction(actionEvent -> {
            if (su_first_name.getText().trim().isEmpty() || su_last_name.getText().trim().isEmpty() ||
                    su_user_name.getText().trim().isEmpty() || su_password.getText().trim().isEmpty() ||
                    su_phone_number.getText().trim().isEmpty() || su_email.getText().trim().isEmpty()) {
                exist_label.setText("Данные для регистрации введены не полностью!");
            } else {
                signUpNewUser();
            }

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

    private void openProfile(Patient patient) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("patient_profile.fxml")));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage curstage = (Stage) su_button.getScene().getWindow();
        curstage.setScene(new Scene(root));
        PatientProfileController patientProfileController = loader.getController(); //получаем контроллер для второй формы
        patientProfileController.setPatient(patient);
        patientProfileController.setFields(patient);
        try {
            patientProfileController.setDoctorsList();// передаем необходимые параметры
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void signUpNewUser() {
        exist_label.setText("");
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
            Patient patient = new Patient(firstname, lastname, username, password, sex, dob, phone_number, email);
            if (!databaseHandler.isExist(username)) {
                databaseHandler.signUpUser(patient);
                openProfile(patient);
            } else {
                exist_label.setText("Пользователь с таким логином уже существует!");
            }
        } catch (ParseException e) {
            exist_label.setText("Дата рождения введена неверно!");
        }
    }

}
