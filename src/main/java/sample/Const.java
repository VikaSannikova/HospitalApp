package sample;

public class Const {
    public static final String PATIENT_TABLE = "patients";
    public static final String PATIENT_ID = "idpat";
    public static final String PATIENT_FIRSTNAME = "first_name";
    public static final String PATIENT_LASTNAME = "last_name";
    public static final String PATIENT_USERNAME = "username";
    public static final String PATIENT_PASSWORD = "password";
    public static final String PATIENT_SEX = "sex";
    public static final String PATIENT_DOB = "dob";
    public static final String PATIENT_PHONENUMBER = "phone_number";
    public static final String PATIENT_EMAIL = "email";
    public static final String PATIENT_DOCID = "doc_id";

    public static final String DOCTOR_TABLE = "doctors";
    public static final String DOCTOR_ID = "iddoc";
    public static final String DOCTOR_FIRSTNAME = "first_name";
    public static final String DOCTOR_LASTNAME = "last_name";
    public static final String DOCTOR_USERNAME = "username";
    public static final String DOCTOR_PASSWORD = "password";
    public static final String DOCTOR_SPECIALITY = "speciality";
    public static final String DOCTOR_CABINET = "cabinet";
    public static final String DOCTOR_PHONENUMBER = "phone_number";

    public static final String DIAGNOSES_TABLE = "diagnoses";
    public static final String DIAGNOSES_ID = "iddiag";
    public static final String DIAGNOSES_PATIENT_ID = "patient_id";
    public static final String DIAGNOSES_DOCTOR_ID = "doctor_id";
    public static final String DIAGNOSES_NAME = "diag_name";
    public static final String DIAGNOSES_DATE = "date_of_request";

    public static final String RECIPES_TABLE = "recipes";
    public static final String RECIPES_ID = "idrec";
    public static final String RECIPES_DIAGNOS_ID = "diagnos_id";
    public static final String RECIPES_RECOMEND = "recomend";
    public static final String RECIPES_TOTALCOST = "total_cost";

}
