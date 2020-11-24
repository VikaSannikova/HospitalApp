package sample;

import javafx.scene.control.Button;

import java.awt.*;

public class DoctorRequest {
    private String pat_first_name;
    private String pat_last_name;
    private String pat_sex;
    private String diag_name;
    private String date;
    private String recomend;
    private String cost;
    private String diag_id;

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    private Button button;


    public DoctorRequest(String pat_first_name, String pat_last_name, String pat_sex, String diag_name, String date, String recomend, String cost, String diag_id) {
        this.pat_first_name = pat_first_name;
        this.pat_last_name = pat_last_name;
        this.pat_sex = pat_sex;
        this.diag_name = diag_name;
        this.date = date;
        this.recomend = recomend;
        this.cost = cost;
        this.diag_id = diag_id;
    }

    public String getDiag_id() {
        return diag_id;
    }

    public void setDiag_id(String diag_id) {
        this.diag_id = diag_id;
    }

    public String getPat_first_name() {
        return pat_first_name;
    }

    public void setPat_first_name(String pat_first_name) {
        this.pat_first_name = pat_first_name;
    }

    public String getPat_last_name() {
        return pat_last_name;
    }

    public void setPat_last_name(String pat_last_name) {
        this.pat_last_name = pat_last_name;
    }

    public String getPat_sex() {
        return pat_sex;
    }

    public void setPat_sex(String pat_sex) {
        this.pat_sex = pat_sex;
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

    @Override
    public String toString() {
        return "DoctorRequest{" +
                "pat_first_name='" + pat_first_name + '\'' +
                ", pat_last_name='" + pat_last_name + '\'' +
                ", pat_sex='" + pat_sex + '\'' +
                ", diag_name='" + diag_name + '\'' +
                ", date='" + date + '\'' +
                ", recomend='" + recomend + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
