package com.example.coronavirustracker.controller;

import com.example.coronavirustracker.model.LocationState;
import com.example.coronavirustracker.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationState> locationStates = coronaVirusDataService.getLocationStates();
        int totalActiveCases = locationStates.stream().mapToInt(stat -> stat.getCases()).sum();
        model.addAttribute("locationStates", coronaVirusDataService.getLocationStates());
        model.addAttribute("totalActiveCases", totalActiveCases);
        return "home";
    }
}
