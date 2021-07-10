package com.example.coronavirustracker.model;

public class LocationState {
    private String state;
    private String country;
    private int cases;

    public String getState() {
        return state;
    }

    public int getCases() {
        return cases;
    }

    public String getCountry() {
        return country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "LocationState{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", cases=" + cases +
                '}';
    }
}
