package com.example.coronavirustracker.service;

import com.example.coronavirustracker.model.LocationState;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

    private static String data = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/06-30-2021.csv";

    private List<LocationState> locationStates = new ArrayList<>();

    @PostConstruct
    public void fetchData() throws IOException, InterruptedException {
        List<LocationState> newStates = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(data)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader reader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        for (CSVRecord record : records) {
            LocationState locationState = new LocationState();
            locationState.setState(record.get("Province_State"));
            locationState.setCountry(record.get("Country_Region"));
            String active = record.get("Active").equals("") ? "0" : record.get("Active");
            locationState.setCases(Integer.parseInt(active));
            // System.out.println(locationState.toString());
            newStates.add(locationState);
        }

        this.locationStates = newStates;
    }

    public List<LocationState> getLocationStates() {
        return locationStates;
    }
}
