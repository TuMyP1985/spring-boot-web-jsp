package ru.java.springbootwebjsp.model.paper;

import java.time.LocalDate;

public class DataRate {

    private LocalDate date;
    private double value;
    private double delta;
    private double deltaPerсent;

    public DataRate() {
    }

    public DataRate(LocalDate date, double value, double delta, double deltaPerсent) {
        this.date = date;
        this.value = value;
        this.delta = delta;
        this.deltaPerсent = deltaPerсent;
    }

    public DataRate(LocalDate date, double value, double delta) {
        this(date, value, delta, 0);
    }

    public DataRate(LocalDate date, double value) {
        this(date, value, 0, 0);

    }

    public DataRate(DataRate d) {
        this(d.getDate(), d.getValue(), d.getDelta(), d.getDeltaPerсent());

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public double getDeltaPerсent() {
        return deltaPerсent;
    }

    public void setDeltaPerсent(double deltaPerсent) {
        this.deltaPerсent = deltaPerсent;
    }

}
