package sample.Handlers;

import sample.Configs;
import sample.Const;
import sample.Doctor;
import sample.Patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        String URL = "jdbc:mysql://localhost:3306/hospital" +
                "?verifyServerCertificate=false" +
                "&useSSL=false" +
                "&requireSSL=false" +
                "&useLegacyDatetimeCode=false" +
                "&amp" +
                "&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(URL, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(Patient patient) {
        String insert = "INSERT INTO " + Const.PATIENT_TABLE +
                "(" + Const.PATIENT_FIRSTNAME + ", " + Const.PATIENT_LASTNAME +
                "," + Const.PATIENT_USERNAME + ", " + Const.PATIENT_PASSWORD +
                "," + Const.PATIENT_SEX + ", " + Const.PATIENT_DOB +
                "," + Const.PATIENT_PHONENUMBER + ", " + Const.PATIENT_EMAIL + ") " +
                "VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, patient.getFirstname());
            preparedStatement.setString(2, patient.getLastname());
            preparedStatement.setString(3, patient.getUsername());
            preparedStatement.setString(4, patient.getPassword());
            preparedStatement.setString(5, patient.getSex());
            preparedStatement.setString(6, patient.getDob());
            preparedStatement.setString(7, patient.getPhone_number());
            preparedStatement.setString(8, patient.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getPatient(Patient patient) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.PATIENT_TABLE + " WHERE " +
                Const.PATIENT_USERNAME + "=? AND " + Const.PATIENT_PASSWORD + "=?";
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, patient.getUsername());
            preparedStatement.setString(2, patient.getPassword());

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getDoctor(Doctor doctor) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.DOCTOR_TABLE + " WHERE " +
                Const.DOCTOR_USERNAME + "=? AND " + Const.DOCTOR_PASSWORD + "=?";
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, doctor.getUsername());
            preparedStatement.setString(2, doctor.getPassword());

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void updatePatient(Patient old_patient, Patient new_patient) {
        String update = "UPDATE " + Const.PATIENT_TABLE +
                " SET " + Const.PATIENT_FIRSTNAME + " = ?," +
                Const.PATIENT_LASTNAME + " = ?," +
                Const.PATIENT_USERNAME + " = ?," +
                Const.PATIENT_PASSWORD + " = ?," +
                Const.PATIENT_SEX + " = ?," +
                Const.PATIENT_DOB + " = ?," +
                Const.PATIENT_PHONENUMBER + " = ?," +
                Const.PATIENT_EMAIL + " = ?" +
                " WHERE " + Const.PATIENT_ID + " = ?";
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = getDbConnection().prepareStatement(update);
            preparedStatement.setString(1, new_patient.getFirstname());
            preparedStatement.setString(2, new_patient.getLastname());
            preparedStatement.setString(3, new_patient.getUsername());
            preparedStatement.setString(4, new_patient.getPassword());
            preparedStatement.setString(5, new_patient.getSex());
            preparedStatement.setString(6, new_patient.getDob());
            preparedStatement.setString(7, new_patient.getPhone_number());
            preparedStatement.setString(8, new_patient.getEmail());
            preparedStatement.setString(9, old_patient.getID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getDoctorsList() {
        ResultSet resultSet = null;
        String select = "SELECT " + Const.DOCTOR_FIRSTNAME + "," + Const.DOCTOR_LASTNAME + "," + Const.DOCTOR_SPECIALITY +
                " FROM " + Const.DOCTOR_TABLE;
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = getDbConnection().prepareStatement(select);

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public ResultSet findDoctor(Doctor doctor){
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.DOCTOR_TABLE + " WHERE " +
                Const.DOCTOR_FIRSTNAME + "=? AND " + Const.DOCTOR_LASTNAME + "=? AND " + Const.DOCTOR_SPECIALITY + "=?";
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, doctor.getFirstname());
            preparedStatement.setString(2, doctor.getLastname());
            preparedStatement.setString(3, doctor.getSpeciality());

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void addRequestToDiagnoses(Patient patient, String doc_id, String date){
        String insert = "INSERT INTO " + Const.DIAGNOSES_TABLE +
                "(" + Const.DIAGNOSES_PATIENT_ID + ", " + Const.DIAGNOSES_DOCTOR_ID + ", " + Const.DIAGNOSES_DATE + ") " +
                "VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, patient.getID());
            preparedStatement.setString(2, doc_id);
            preparedStatement.setString(3, date);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAllRequests(Patient patient){
        ResultSet resultSet = null;
        String select = "SELECT " + Const.DOCTOR_FIRSTNAME + "," +
                                    Const.DOCTOR_LASTNAME + "," +
                                    Const.DOCTOR_SPECIALITY + "," +
                                    Const.DIAGNOSES_NAME + "," +
                                    Const.DIAGNOSES_DATE + "," +
                                    Const.RECIPES_RECOMEND + "," +
                                    Const.RECIPES_TOTALCOST + " FROM " +
                        Const.DOCTOR_TABLE + " doc LEFT OUTER JOIN " + Const.DIAGNOSES_TABLE + " diag ON " +
                        "doc." + Const.DOCTOR_ID +"=diag." + Const.DIAGNOSES_DOCTOR_ID +
                        " LEFT OUTER JOIN " + Const.RECIPES_TABLE + " rec ON " +
                        "diag." + Const.DIAGNOSES_ID + "=rec." + Const.RECIPES_DIAGNOS_ID +
                        " WHERE " + "diag." +Const.DIAGNOSES_PATIENT_ID + "=?";
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, patient.getID());

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}
