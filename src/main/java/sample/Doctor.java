package sample;

public class Doctor {
    private String ID;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String speciality;
    private String cabinet;
    private String phone_number;

    public Doctor(String ID, String firstname, String lastname, String username, String password, String speciality, String cabinet, String phone_number) {
        this.ID = ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.speciality = speciality;
        this.cabinet = cabinet;
        this.phone_number = phone_number;
    }

    public Doctor() {

    }

    public Doctor(String fn_ln_sp) {
        String[] strings = fn_ln_sp.split("\\s+");
        this.firstname = strings[0];
        this.lastname = strings[1];
        this.speciality = strings[2].replaceAll("[()]", "");
    }

    public Doctor(String ID, String firstname, String lastname, String speciality, String cabinet, String phone_number) {
        this.ID = ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.speciality = speciality;
        this.cabinet = cabinet;
        this.phone_number = phone_number;
    }

    public Doctor(Doctor doctor) {
        this.ID = doctor.getID();
        this.firstname = doctor.getFirstname();
        this.lastname = doctor.getLastname();
        this.username = doctor.getUsername();
        this.password = doctor.getPassword();
        this.speciality = doctor.getSpeciality();
        this.cabinet = doctor.getCabinet();
        this.phone_number = doctor.phone_number;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
