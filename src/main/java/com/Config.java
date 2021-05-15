package com;

import java.util.List;

public class Config {
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    String baseUrl;

    public int getFilterByAge() {
        return filterByAge;
    }

    public void setFilterByAge(int filterByAge) {
        this.filterByAge = filterByAge;
    }

    public int getVaccineGreaterThan() {
        return vaccineGreaterThan;
    }

    public void setVaccineGreaterThan(int vaccineGreaterThan) {
        this.vaccineGreaterThan = vaccineGreaterThan;
    }

    int filterByAge;
    int vaccineGreaterThan;

    public boolean isPrintEnabled() {
        return printEnabled;
    }

    public void setPrintEnabled(boolean printEnabled) {
        this.printEnabled = printEnabled;
    }

    boolean printEnabled = true;

    public boolean isBeepNow() {
        return beepNow;
    }

    public void setBeepNow(boolean beepNow) {
        this.beepNow = beepNow;
    }

    boolean beepNow = false;

    public List<Integer> getSelectedPinCode() {
        return selectedPinCode;
    }

    public void setSelectedPinCode(List<Integer> selectedPinCode) {
        this.selectedPinCode = selectedPinCode;
    }

    List<Integer> selectedPinCode;

}
