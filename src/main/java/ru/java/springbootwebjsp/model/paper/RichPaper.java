package ru.java.springbootwebjsp.model.paper;

import java.util.ArrayList;
import java.util.List;

public class RichPaper {

    private String name;
    private String description;
    protected List<DataRate> dataRates = new ArrayList<>();

    public RichPaper() {}

    public RichPaper(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DataRate> getDataRates() {
        return dataRates;
    }

    public void setDataRates(List<DataRate> dataRates) {
        this.dataRates.clear();
        this.dataRates = dataRates;
    }

    public void setDataRates(DataRate dataRates) {
        this.dataRates = new ArrayList<>();
        this.dataRates.add(dataRates);
    }

}