package sample;

public class Patient {
    private String ID;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String sex;
    private String dob;
    private String phone_number;
    private String email;

    public Patient(String ID, String firstname, String lastname, String username, String password, String sex, String dob, String phone_number, String email) {
        this.ID = ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.dob = dob;
        this.phone_number = phone_number;
        this.email = email;
    }

    public Patient(String firstname, String lastname, String username, String password, String sex, String dob, String phone_number, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.dob = dob;
        this.phone_number = phone_number;
        this.email = email;
    }

    public Patient() {
    }

    public Patient(Patient patient) {
        this.ID = patient.getID();
        this.firstname = patient.getFirstname();
        this.lastname = patient.getLastname();
        this.username = patient.getUsername();
        this.password = patient.getPassword();
        this.sex = patient.getSex();
        this.dob = patient.getDob();
        this.phone_number = patient.getPhone_number();
        this.email = patient.getEmail();
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getID() { return ID; }

    public void setID(String ID) { this.ID = ID; }
}
