package ru.java.springbootwebjsp.model.paper;

public class ResultToJson {

    public double value;
    public String date;

    public ResultToJson() {
    }

    public ResultToJson(double value, String date) {
        this.value = value;
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
