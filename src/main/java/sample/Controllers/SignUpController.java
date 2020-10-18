package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import sample.Handlers.DatabaseHandler;
import sample.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SignUpController {

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
    }

}
