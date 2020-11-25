package sample.controllers;

import com.gluonhq.charm.glisten.control.ToggleButtonGroup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Const;
import sample.Doctor;
import sample.DoctorRequest;
import sample.Handlers.DatabaseHandler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class DoctorProfileController {
    @FXML
    private TabPane tab_pane;

    @FXML
    private Tab doc_user_data;

    @FXML
    private Button update_button;

    @FXML
    private TextField doc_fn_tf;

    @FXML
    private TextField doc_ln_tf;

    @FXML
    private TextField doc_spec_tf;

    @FXML
    private TextField doc_login_tf;

    @FXML
    private TextField doc_phone_number_tf;

    @FXML
    private PasswordField doc_password_tf;

    @FXML
    private ToggleButtonGroup buts;

    @FXML
    private TextField doc_cab_tf;

    @FXML
    private Tab actual_patients;

    @FXML
    private TableView<DoctorRequest> actual_pats_table;

    @FXML
    private TableColumn<DoctorRequest, String> pat_col;

    @FXML
    private TableColumn<DoctorRequest, String> pat_fn_col;

    @FXML
    private TableColumn<DoctorRequest, String> pat_ln_col;

    @FXML
    private TableColumn<DoctorRequest, String> pat_sex_col;

    @FXML
    private TableColumn<DoctorRequest, String> diag_col;

    @FXML
    private TableColumn<DoctorRequest, String> date_col;

    @FXML
    private TableColumn<DoctorRequest, String> rec_col;

    @FXML
    private TableColumn<DoctorRequest, String> cost_col;

    @FXML
    public TableColumn<DoctorRequest, Void> action_col;

    @FXML
    private TableView<DoctorRequest> done_pats_table;

    @FXML
    private TableColumn<DoctorRequest, String> done_pat_col;

    @FXML
    private TableColumn<DoctorRequest, String> done_pat_fn_col;

    @FXML
    private TableColumn<DoctorRequest, String> done_pat_ln_col;

    @FXML
    private TableColumn<DoctorRequest, String> done_pat_sex_col;

    @FXML
    private TableColumn<DoctorRequest, String> done_diag_col;

    @FXML
    private TableColumn<DoctorRequest, String> done_date_col;

    @FXML
    private TableColumn<DoctorRequest, String> done_rec_col;

    @FXML
    private TableColumn<DoctorRequest, String> done_cost_col;

    @FXML
    private Button exit_button;

    Doctor doctor = new Doctor();
    Doctor upd_doctor = new Doctor();

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        this.upd_doctor = new Doctor(doctor);
    }

    public void setFields(Doctor doctor) {
        doc_fn_tf.setText(doctor.getFirstname());
        doc_ln_tf.setText(doctor.getLastname());
        doc_spec_tf.setText(doctor.getSpeciality());
        doc_cab_tf.setText(doctor.getCabinet());
        doc_login_tf.setText(doctor.getUsername());
        doc_password_tf.setText(doctor.getPassword());
        doc_phone_number_tf.setText(doctor.getPhone_number());
    }

    @FXML
    void initialize() {
        //tab 0
        doc_fn_tf.setOnMouseClicked(actionTvent -> {
            doc_fn_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_doctor.setFirstname(newValue);
            });
        });
        doc_ln_tf.setOnMouseClicked(actionTvent -> {
            doc_ln_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_doctor.setLastname(newValue);
            });
        });
        doc_login_tf.setOnMouseClicked(actionTvent -> {
            doc_login_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_doctor.setUsername(newValue);
            });
        });
        doc_password_tf.setOnMouseClicked(actionTvent -> {
            doc_password_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_doctor.setPassword(newValue);
            });
        });
        doc_spec_tf.setOnMouseClicked(actionTvent -> {
            doc_spec_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_doctor.setSpeciality(newValue);
            });
        });
        doc_phone_number_tf.setOnMouseClicked(actionEvent -> {
            doc_phone_number_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_doctor.setPhone_number(newValue);
            });
        });
        doc_cab_tf.setOnMouseClicked(actionEvent -> {
            doc_cab_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                update_button.setDisable(false);
                upd_doctor.setCabinet(newValue);
            });
        });
        update_button.setOnAction(actionEvent -> {
            updateDoctor(doctor, upd_doctor);
            update_button.setDisable(true);
            System.out.println("Данные обновлены!");
        });

        //tab 1

        pat_fn_col.setCellValueFactory(new PropertyValueFactory<>("pat_first_name"));
        pat_ln_col.setCellValueFactory(new PropertyValueFactory<>("pat_last_name"));
        pat_sex_col.setCellValueFactory(new PropertyValueFactory<>("pat_sex"));
        diag_col.setCellValueFactory(new PropertyValueFactory<>("diag_name"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        rec_col.setCellValueFactory(new PropertyValueFactory<>("recomend"));
        rec_col.setCellFactory(userRequestStringTableColumn -> {
            TableCell<DoctorRequest, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(rec_col.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        cost_col.setCellValueFactory(new PropertyValueFactory<>("cost"));
        action_col.setStyle("-fx-alignment: CENTER;");
        addButtonToTable();
        editableTable();
        fillActualRequestTable();

        // tab 2

        done_pat_fn_col.setCellValueFactory(new PropertyValueFactory<>("pat_first_name"));
        done_pat_ln_col.setCellValueFactory(new PropertyValueFactory<>("pat_last_name"));
        done_pat_sex_col.setCellValueFactory(new PropertyValueFactory<>("pat_sex"));
        done_diag_col.setCellValueFactory(new PropertyValueFactory<>("diag_name"));
        done_date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        done_rec_col.setCellValueFactory(new PropertyValueFactory<>("recomend"));
        done_rec_col.setCellFactory(userRequestStringTableColumn -> {
            TableCell<DoctorRequest, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(rec_col.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        done_cost_col.setCellValueFactory(new PropertyValueFactory<>("cost"));
        fillDoneRequestTable();



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

    private void addButtonToTable() {
        Callback<TableColumn<DoctorRequest, Void>, TableCell<DoctorRequest, Void>> cellFactory = new Callback<TableColumn<DoctorRequest, Void>, TableCell<DoctorRequest, Void>>() {
            @Override
            public TableCell<DoctorRequest, Void> call(final TableColumn<DoctorRequest, Void> param) {
                final TableCell<DoctorRequest, Void> cell = new TableCell<DoctorRequest, Void>() {
                    private final Button btn = new Button("Обновить");

                    {
                        // TODO активные и неактивные строчки
                        //btn.setDisable(true);

                        btn.setOnAction((ActionEvent event) -> {
                            DoctorRequest doctorRequest = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + doctorRequest);
                            updateUserRequestByDoctor(doctorRequest);
                            fillActualRequestTable();
                            fillDoneRequestTable();
                            //btn.setDisable(true);
                        });


                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        action_col.setCellFactory(cellFactory);
    }

    private void updateUserRequestByDoctor(DoctorRequest doctorRequest) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.updateUserRequestByDoctor(doctorRequest);
    }


    private void editableTable() {
        diag_col.setCellFactory(TextFieldTableCell.forTableColumn());
        diag_col.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setDiag_name(event.getNewValue());
        });
        rec_col.setCellFactory(TextFieldTableCell.forTableColumn());
        rec_col.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setRecomend(event.getNewValue());
        });
        cost_col.setCellFactory(TextFieldTableCell.forTableColumn());
        cost_col.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setCost(event.getNewValue());
        });
        actual_pats_table.setEditable(true);
    }

    public void fillActualRequestTable() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getActualRequestsToDoctor(doctor);
        try {
            ObservableList<DoctorRequest> doctorRequests = getRequestListToDoctor(resultSet);
            actual_pats_table.setItems(doctorRequests);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillDoneRequestTable() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getDoneRequestsToDoctor(doctor);
        try {
            ObservableList<DoctorRequest> doctorRequests = getRequestListToDoctor(resultSet);
            done_pats_table.setItems(doctorRequests);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<DoctorRequest> getRequestListToDoctor(ResultSet resultSet) throws SQLException {
        ObservableList<DoctorRequest> result = FXCollections.observableArrayList();
        while (resultSet.next()) {
            String pat_fn = resultSet.getString(Const.PATIENT_FIRSTNAME);
            String pat_ln = resultSet.getString(Const.PATIENT_LASTNAME);
            String pat_sex = "мужской";
            if (resultSet.getString(Const.PATIENT_SEX) == "f") {
                pat_sex = "женский";
            }
            String diag = resultSet.getString(Const.DIAGNOSES_NAME);
            String date = resultSet.getString(Const.DIAGNOSES_DATE);
            String recomends = resultSet.getString(Const.RECIPES_RECOMEND);
            String cost = resultSet.getString(Const.RECIPES_TOTALCOST);
            String diag_id = resultSet.getString(Const.DIAGNOSES_ID);

            DoctorRequest doctorRequest = new DoctorRequest(pat_fn, pat_ln, pat_sex, diag, date, recomends, cost, diag_id);
            result.add(doctorRequest);
        }
        return result;
    }


    private void updateDoctor(Doctor doctor, Doctor upd_doctor) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.updateDoctor(doctor, upd_doctor);
    }
}
