package sample;

public class Doctor {
    private String ID;
    private String firstname;
    private String lastname;
    private String speciality;
    private String cabinet;
    private String phone_number;

    public Doctor(String ID, String firstname, String lastname, String speciality, String cabinet, String phone_number) {
        this.ID = ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.speciality = speciality;
        this.cabinet = cabinet;
        this.phone_number = phone_number;
    }

    public Doctor() {

    }

    public Doctor(String fn_ln_sp){
        String[] strings = fn_ln_sp.split("\\s+");
        this.firstname = strings[0];
        this.lastname = strings[1];
        this.speciality = strings[2].replaceAll("[()]","");
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
