package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Patient;

public class ProfileController {

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

    }

    void setFields(Patient patient){
        fn_tf.setText(patient.getFirstname());
        ln_tf.setText(patient.getLastname());
        dob_tf.setText(patient.getDob());
        System.out.println(patient.getSex());
        if (patient.getSex().equals("m")){
            male_but.setSelected(true);
        } else {
            female_but.setSelected(true);
        }
        System.out.println(male_but.isSelected());
        System.out.println(female_but.isSelected());
        login_tf.setText(patient.getUsername());
        password_tf.setText(patient.getPassword());
        phone_number_tf.setText(patient.getPhone_number());
        email_tf.setText(patient.getEmail());
    }
}
