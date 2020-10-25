package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Const;
import sample.Doctor;
import sample.Handlers.DatabaseHandler;
import sample.Patient;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ProfileController {

    Patient patient = new Patient();
    Patient upd_patient = new Patient();
    final ObservableList docs_name_list = FXCollections.observableArrayList();
    Doctor doctor;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        this.upd_patient = new Patient(patient);
    }

    @FXML
    private Button exit_button;

    @FXML
    private TabPane tab_pane;

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
    private ComboBox<String> choose_a_doctor;

    @FXML
    private DatePicker choose_a_date;

    @FXML
    private Label fn_label;

    @FXML
    private Label ln_label;

    @FXML
    private TextField doc_fn_tf;

    @FXML
    private TextField doc_ln_tf;

    @FXML
    private Label speciality_label;

    @FXML
    private Label cab_label;

    @FXML
    private Label pn_label;

    @FXML
    private TextField speciality_tf;

    @FXML
    private TextField pn_tf;

    @FXML
    private TextField cab_tf;

    @FXML
    private ImageView doc_image;

    @FXML
    private Tab history;

    @FXML
    void initialize() {
        fn_tf.setOnMouseClicked(actionTvent -> {
            fn_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_patient.setFirstname(newValue);
            });
        });
        ln_tf.setOnMouseClicked(actionTvent -> {
            ln_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_patient.setLastname(newValue);
            });
        });
        login_tf.setOnMouseClicked(actionTvent -> {
            login_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_patient.setUsername(newValue);
            });
        });
        password_tf.setOnMouseClicked(actionTvent -> {
            password_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_patient.setPassword(newValue);
            });
        });
        dob_tf.setOnMouseClicked(actionTvent -> {
            dob_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_patient.setDob(newValue);
            });
        });
        male_but.setOnAction(actionEvent -> {
            update_button.setDisable(false);
            if (male_but.isSelected()) {
                upd_patient.setSex("m");
            }
        });
        female_but.setOnAction(actionEvent -> {
            update_button.setDisable(false);
            if (female_but.isSelected()) {
                upd_patient.setSex("f");
            }
        });
        phone_number_tf.setOnMouseClicked(actionEvent -> {
            phone_number_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_patient.setPhone_number(newValue);
            });
        });
        email_tf.setOnMouseClicked(actionEvent -> {
            email_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_patient.setEmail(newValue);
            });
        });
        update_button.setOnAction(actionEvent -> {
            updatePatient(patient, upd_patient);
            update_button.setDisable(true);
            tab_pane.getSelectionModel().select(user_data);
            System.out.println("Данные обновлены!");
        });
        choose_a_doctor.setOnAction(actionEvent -> {
            doctor = new Doctor(choose_a_doctor.getSelectionModel().getSelectedItem());
            DatabaseHandler databaseHandler = new DatabaseHandler();
            ResultSet resultSet = databaseHandler.findDoctor(doctor);
            String id = null;
            try {
                if (resultSet.next()){
                    id = resultSet.getString(Const.DOCTOR_ID);
                    String doc_fn = resultSet.getString(Const.DOCTOR_FIRSTNAME);
                    String doc_ln = resultSet.getString(Const.DOCTOR_LASTNAME);
                    String speciality = resultSet.getString(Const.DOCTOR_SPECIALITY);
                    String cabinet = resultSet.getString(Const.DOCTOR_CABINET);
                    String phone_number = resultSet.getString(Const.DOCTOR_PHONENUMBER);
                    Doctor doc = new Doctor(id, doc_fn, doc_ln, speciality,cabinet,phone_number);
                    doc_fn_tf.setText(doc.getFirstname());
                    doc_ln_tf.setText(doc.getLastname());
                    speciality_tf.setText(doc.getSpeciality());
                    cab_tf.setText(doc.getCabinet());
                    pn_tf.setText(doc.getPhone_number());
                }
            } catch (SQLException e) {
                e.printStackTrace();
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

    private void updatePatient(Patient old_patient, Patient new_patient) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.updatePatient(old_patient, new_patient);
    }

    public void setFields(Patient patient) {
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

    public void setDoctorsList() throws SQLException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getDoctorsList();
        while (resultSet.next()) {
            String first_name = resultSet.getString(Const.DOCTOR_FIRSTNAME);
            String last_name = resultSet.getString(Const.DOCTOR_LASTNAME);
            String speciality = resultSet.getString(Const.DOCTOR_SPECIALITY);
            docs_name_list.add(first_name + " " + last_name + " ("+speciality+")");
        }
        choose_a_doctor.setItems(docs_name_list);
    }
}
