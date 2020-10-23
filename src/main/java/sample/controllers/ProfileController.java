package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Handlers.DatabaseHandler;
import sample.Patient;

public class ProfileController {

    Patient patient = new Patient();
    Patient upd_patient = new Patient();

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        this.upd_patient =  new Patient(patient);
    }

    @FXML
    private Tab user_data;

    @FXML
    private Button update_button;

    @FXML
    private TextField fn_tf;

    @FXML
    private TextField ln_tf;

    @FXML
    private TextField dob_tf;

    @FXML
    private ToggleButton male_but;

    @FXML
    private ToggleButton female_but;

    @FXML
    private TextField login_tf;

    @FXML
    private TextField phone_number_tf;

    @FXML
    private TextField email_tf;

    @FXML
    private PasswordField password_tf;

    @FXML
    private Tab add_doctor;

    @FXML
    private Tab history;

    @FXML
    void initialize() {
        fn_tf.setOnMouseClicked(actionTvent ->{
            fn_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_patient.setFirstname(newValue);
            });
        });
        ln_tf.setOnMouseClicked(actionTvent ->{
            ln_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_patient.setLastname(newValue);
            });
        });
        login_tf.setOnMouseClicked(actionTvent ->{
            login_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_patient.setUsername(newValue);
            });
        });
        password_tf.setOnMouseClicked(actionTvent ->{
            password_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_patient.setPassword(newValue);
            });
        });
        dob_tf.setOnMouseClicked(actionTvent ->{
            dob_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_patient.setDob(newValue);
            });
        });
        male_but.setOnAction(actionEvent -> {
            update_button.setDisable(false);
            if(male_but.isSelected()){
                upd_patient.setSex("m");
            }
        });
        female_but.setOnAction(actionEvent -> {
            update_button.setDisable(false);
            if(female_but.isSelected()){
                upd_patient.setSex("f");
            }
        });
        phone_number_tf.setOnMouseClicked(actionEvent ->{
            phone_number_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_patient.setPhone_number(newValue);
            });
        });
        email_tf.setOnMouseClicked(actionEvent ->{
            email_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_patient.setEmail(newValue);
            });
        });

        update_button.setOnAction(actionEvent -> {
            updatePatient(patient, upd_patient);
            update_button.setDisable(true);
            System.out.println("Данные обновлены!");
        });
    }

    void updatePatient(Patient old_patient, Patient new_patient){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.updatePatient(old_patient, new_patient);
    }

    void setFields(Patient patient) {
        fn_tf.setText(patient.getFirstname());
        ln_tf.setText(patient.getLastname());
        dob_tf.setText(patient.getDob());
        if (patient.getSex().equals("m")) {
            male_but.setSelected(true);
        } else {
            female_but.setSelected(true);
        }
        login_tf.setText(patient.getUsername());
        password_tf.setText(patient.getPassword());
        phone_number_tf.setText(patient.getPhone_number());
        email_tf.setText(patient.getEmail());
    }
}
