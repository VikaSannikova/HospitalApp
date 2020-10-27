package sample;

public class UserRequest {
    private String doc_first_name;
    private String doc_last_name;
    private String doc_speciality;
    private String diag_name;
    private String date;
    private String recomend;
    private String cost;

    public UserRequest(String doc_first_name, String doc_last_name, String doc_speciality, String diag_name, String date, String recomend, String cost) {
        this.doc_first_name = doc_first_name;
        this.doc_last_name = doc_last_name;
        this.doc_speciality = doc_speciality;
        this.diag_name = diag_name;
        this.date = date;
        this.recomend = recomend;
        this.cost = cost;
    }

    public String getDoc_first_name() {
        return doc_first_name;
    }

    public void setDoc_first_name(String doc_first_name) {
        this.doc_first_name = doc_first_name;
    }

    public String getDoc_last_name() {
        return doc_last_name;
    }

    public void setDoc_last_name(String doc_last_name) {
        this.doc_last_name = doc_last_name;
    }

    public String getDoc_speciality() {
        return doc_speciality;
    }

    public void setDoc_speciality(String doc_speciality) {
        this.doc_speciality = doc_speciality;
    }

    public String getDiag_name() {
        return diag_name;
    }

    public void setDiag_name(String diag_name) {
        this.diag_name = diag_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRecomend() {
        return recomend;
    }

    public void setRecomend(String recomend) {
        this.recomend = recomend;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
