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
import sample.Doctor;
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
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("signUp.fxml")));
           try {
               loader.load();
           } catch (IOException e) {
               e.printStackTrace();
           }
           Parent root = loader.getRoot();
           Stage curstage = (Stage) loginSignUpButton.getScene().getWindow();
           curstage.setScene(new Scene(root));
       });
    }

    private void loginUser(String loginText, String loginPassword) throws SQLException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Patient patient = new Patient();
        patient.setUsername(loginText);
        patient.setPassword(loginPassword);
        Doctor doctor = new Doctor();
        doctor.setUsername(loginText);
        doctor.setPassword(loginPassword);
        ResultSet resultSetPatients = databaseHandler.getPatient(patient);
        ResultSet resultSetDoctors = databaseHandler.getDoctor(doctor);

        if (resultSetPatients.next()) {
            // если несколько пользователей - то первый самый
            String id = resultSetPatients.getString(Const.PATIENT_ID);
            String fn = resultSetPatients.getString(Const.PATIENT_FIRSTNAME);
            String ln = resultSetPatients.getString(Const.PATIENT_LASTNAME);
            String login = resultSetPatients.getString(Const.PATIENT_USERNAME);
            String password = resultSetPatients.getString(Const.PATIENT_PASSWORD);
            String sex = resultSetPatients.getString(Const.PATIENT_SEX);
            String dob = resultSetPatients.getString(Const.PATIENT_DOB);
            String phone_number = resultSetPatients.getString(Const.PATIENT_PHONENUMBER);
            String email = resultSetPatients.getString(Const.PATIENT_EMAIL);
            Patient pat = new Patient(id, fn, ln, login, password, sex, dob, phone_number, email);
            System.out.println("Такой пациент есть!");
            error_input_label.setTextFill(Color.color(1, 1, 1));
            openPatientProfile(pat);
        } else if (resultSetDoctors.next()){
            // если несколько пользователей - то первый самый
            String id = resultSetDoctors.getString(Const.DOCTOR_ID);
            String fn = resultSetDoctors.getString(Const.DOCTOR_FIRSTNAME);
            String ln = resultSetDoctors.getString(Const.DOCTOR_LASTNAME);
            String login = resultSetDoctors.getString(Const.DOCTOR_USERNAME);
            String password = resultSetDoctors.getString(Const.DOCTOR_PASSWORD);
            String speciality = resultSetDoctors.getString(Const.DOCTOR_SPECIALITY);
            String cabinet = resultSetDoctors.getString(Const.DOCTOR_CABINET);
            String phone_number = resultSetDoctors.getString(Const.DOCTOR_PHONENUMBER);
            Doctor doc = new Doctor(id, fn, ln,login,password,speciality,cabinet,phone_number);
            System.out.println("Такой доктор есть!");
            error_input_label.setTextFill(Color.color(1, 1, 1));
            openDoctorProfile(doc);
        } else {
            error_input_label.setTextFill(Color.color(0,0,0));
        }
    }

    private void openPatientProfile(Patient patient){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("patient_profile.fxml")));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage curstage = (Stage) authSignInButton.getScene().getWindow();
        curstage.setScene(new Scene(root));
        // соединение с другой формой
        PatientProfileController patientProfileController = loader.getController(); //получаем контроллер для второй формы
        patientProfileController.setPatient(patient);
        patientProfileController.setFields(patient);
        patientProfileController.fillRequestTable();
        try {
            patientProfileController.setDoctorsList();// передаем необходимые параметры
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openDoctorProfile(Doctor doctor){
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("patient_profile.fxml")));
//        try {
//            loader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Parent root = loader.getRoot();
//        Stage curstage = (Stage) authSignInButton.getScene().getWindow();
//        curstage.setScene(new Scene(root));
//        // соединение с другой формой
//        ProfileController profileController = loader.getController(); //получаем контроллер для второй формы
//        profileController.setPatient(patient);
//        profileController.setFields(patient);
//        try {
//            profileController.setDoctorsList();// передаем необходимые параметры
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        System.out.println("Открывается профиль доктора");
    }
}

