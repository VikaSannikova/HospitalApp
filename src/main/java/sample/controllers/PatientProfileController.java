package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import sample.Const;
import sample.Doctor;
import sample.Handlers.DatabaseHandler;
import sample.Patient;
import sample.UserRequest;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PatientProfileController {

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
    private Button add_request;

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
    private TableView<UserRequest> request_table;

    @FXML
    private TableColumn<UserRequest, String> doc_col;

    @FXML
    private TableColumn<UserRequest, String> doc_fn_col;

    @FXML
    private TableColumn<UserRequest, String> doc_ln_col;

    @FXML
    private TableColumn<UserRequest, String> doc_spec_col;

    @FXML
    private TableColumn<UserRequest, String> diag_col;

    @FXML
    private TableColumn<UserRequest, String> date_col;

    @FXML
    private TableColumn<UserRequest, String> rec_col;

    @FXML
    private TableColumn<UserRequest, String> cost_col;

    public TableView<UserRequest> getRequest_table() {
        return request_table;
    }

    @FXML
    void initialize() {

        // tab 0
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

        //tab 1
        choose_a_doctor.setOnAction(actionEvent -> {
            if (choose_a_doctor.getSelectionModel().getSelectedItem() != null) {
                doctor = new Doctor(choose_a_doctor.getSelectionModel().getSelectedItem());
                DatabaseHandler databaseHandler = new DatabaseHandler();
                ResultSet resultSet = databaseHandler.findDoctor(doctor);
                try {
                    if (resultSet.next()) {
                        String id = resultSet.getString(Const.DOCTOR_ID);
                        String doc_fn = resultSet.getString(Const.DOCTOR_FIRSTNAME);
                        String doc_ln = resultSet.getString(Const.DOCTOR_LASTNAME);
                        String speciality = resultSet.getString(Const.DOCTOR_SPECIALITY);
                        String cabinet = resultSet.getString(Const.DOCTOR_CABINET);
                        String phone_number = resultSet.getString(Const.DOCTOR_PHONENUMBER);
                        Doctor doc = new Doctor(id, doc_fn, doc_ln, speciality, cabinet, phone_number);
                        doc_fn_tf.setText(doc.getFirstname());
                        doc_ln_tf.setText(doc.getLastname());
                        speciality_tf.setText(doc.getSpeciality());
                        cab_tf.setText(doc.getCabinet());
                        pn_tf.setText(doc.getPhone_number());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        choose_a_date.setConverter(converter);
        choose_a_date.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0);
            }
        });
        add_request.setOnAction(actionEvent -> {
            if (choose_a_doctor.getValue() != null && choose_a_date.getValue() != null) {
                DatabaseHandler databaseHandler = new DatabaseHandler();
                ResultSet resultSetDoctor = databaseHandler.findDoctor(doctor);
                try {
                    if (resultSetDoctor.next()) {
                        String doctor_id = resultSetDoctor.getString(Const.DOCTOR_ID);
                        databaseHandler.addRequestToDiagnoses(patient, doctor_id, choose_a_date.getValue().toString());
                        System.out.println("Вы успешно записались на прием!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                choose_a_doctor.getSelectionModel().clearSelection();
                choose_a_date.setValue(null);
            } else {
                System.out.println("Врач и дата не введены!");
            }
            fillRequestTable();
        });

        // tab 2
        doc_fn_col.setCellValueFactory(new PropertyValueFactory<>("doc_first_name"));
        doc_ln_col.setCellValueFactory(new PropertyValueFactory<>("doc_last_name"));
        doc_spec_col.setCellValueFactory(new PropertyValueFactory<>("doc_speciality"));
        diag_col.setCellValueFactory(new PropertyValueFactory<>("diag_name"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        rec_col.setCellValueFactory(new PropertyValueFactory<>("recomend"));
        rec_col.setCellFactory(tc -> {
            TableCell<UserRequest, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(rec_col.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });
        cost_col.setCellValueFactory(new PropertyValueFactory<>("cost"));
        fillRequestTable();

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

    public void fillRequestTable() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getAllRequests(patient);
        try {
            ObservableList<UserRequest> userRequests = getRequsrtList(resultSet);
            request_table.setItems(userRequests);
            request_table.setRowFactory((TableView<UserRequest> paramP) -> new TableRow<UserRequest>() {
                @Override
                protected void updateItem(UserRequest row, boolean paramBoolean) {
                    if (row != null) {
                        if(row.getDiag_name()!=null){
                            setStyle("-fx-background-color: #d9eed4; -fx-text-background-color: black;");
                        } else{
                            setStyle("-fx-background-color: #ffd4c7; -fx-text-background-color: black;");
                        }

                    } else {
                        setStyle(null);
                    }
                    super.updateItem(row, paramBoolean);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<UserRequest> getRequsrtList(ResultSet resultSet) throws SQLException {
        ObservableList<UserRequest> result = FXCollections.observableArrayList();
        while (resultSet.next()) {
            String doc_fn = resultSet.getString(Const.DOCTOR_FIRSTNAME);
            String doc_ln = resultSet.getString(Const.DOCTOR_LASTNAME);
            String doc_sp = resultSet.getString(Const.DOCTOR_SPECIALITY);
            String diag = resultSet.getString(Const.DIAGNOSES_NAME);
            String date = resultSet.getString(Const.DIAGNOSES_DATE);
            String recomends = resultSet.getString(Const.RECIPES_RECOMEND);
            String cost = resultSet.getString(Const.RECIPES_TOTALCOST);
            UserRequest userRequest = new UserRequest(doc_fn, doc_ln, doc_sp, diag, date, recomends, cost);
            result.add(userRequest);
        }
        return result;
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
            docs_name_list.add(first_name + " " + last_name + " (" + speciality + ")");
        }
        choose_a_doctor.setItems(docs_name_list);
    }
}
