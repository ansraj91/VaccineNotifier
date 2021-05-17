package com;

import java.util.List;

public class Config {
    private List<Integer> selectedPinCode;
    private String baseUrl;
    private int filterByAge;
    private int vaccineGreaterThan;
    private boolean printEnabled = true;
    private boolean beepNow = false;
    private String beepFilePath;
    private String openWebPageURL;
    private String dummyResponseObject;


    public String getBaseUrl() {
        return baseUrl;
    }
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
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
    public boolean isPrintEnabled() {
        return printEnabled;
    }
    public void setPrintEnabled(boolean printEnabled) {
        this.printEnabled = printEnabled;
    }
    public boolean isBeepNow() {
        return beepNow;
    }
    public void setBeepNow(boolean beepNow) {
        this.beepNow = beepNow;
    }
    public List<Integer> getSelectedPinCode() {
        return selectedPinCode;
    }

    public void setSelectedPinCode(List<Integer> selectedPinCode) {
        this.selectedPinCode = selectedPinCode;
    }

    public String getBeepFilePath() {
        return beepFilePath;
    }

    public void setBeepFilePath(String beepFilePath) {
        this.beepFilePath = beepFilePath;
    }

    public String getOpenWebPageURL() {
        return openWebPageURL;
    }

    public void setOpenWebPageURL(String openWebPageURL) {
        this.openWebPageURL = openWebPageURL;
    }

    public String getDummyResponseObject() {
        return dummyResponseObject;
    }

    public void setDummyResponseObject(String dummyResponseObject) {
        this.dummyResponseObject = dummyResponseObject;
    }




}
