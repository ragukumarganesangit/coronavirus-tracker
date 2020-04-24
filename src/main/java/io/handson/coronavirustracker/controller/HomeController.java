package io.handson.coronavirustracker.controller;

import io.handson.coronavirustracker.model.LocationStats;
import io.handson.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/ragu")
    public String home(Model model){
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(S -> S.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(S -> S.getDifferenceFromPrevious()).sum();
        model.addAttribute("locationstats", coronaVirusDataService.getAllStats());
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }
}
