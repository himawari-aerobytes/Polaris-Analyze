package web;

import analyzer.analyze.Result;
import analyzer.analyze.Analyzer;
import analyzer.History;
import analyzer.propaties.JPCalendar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.*;

import static analyzer.analyze.Controller.getResults;
import static java.lang.System.exit;

@Controller
public class AnalyzerController {

    @GetMapping("/")
    public String helloGradleGet(Model model) {
        final List<Map<String, Result>> results = getResults();
        List<String> B3Label = new ArrayList<>();
        List<String> B4Label = new ArrayList<>();
        List<String> M2Label = new ArrayList<>();

        List<Double> B3Value = new ArrayList<>();
        List<Double> B4Value = new ArrayList<>();
        List<Double> M2Value = new ArrayList<>();

        String title = store.term;


        for(Map<String, Result> result : results){
           B3Label.add(result.get("B3").getStartDate()+"~"+result.get("B3").getEndDate());
           B4Label.add(result.get("B4").getStartDate() );
           M2Label.add(result.get("M2").getStartDate());

           B3Value.add(result.get("B3").getPercentage());
           B4Value.add(result.get("B4").getPercentage());
           M2Value.add(result.get("M2").getPercentage());
        }

        model.addAttribute("B3Label",B3Label);
        model.addAttribute("B4Label",B4Label);
        model.addAttribute("M2Label",M2Label);

        model.addAttribute("B3Value",B3Value);
        model.addAttribute("B4Value",B4Value);
        model.addAttribute("M2Value",M2Value);
        model.addAttribute("title",title);

        model.addAttribute("History",history);

        return "analyzer";
    }

    @PostMapping
    public String helloGradlePost() {
        return "analyzer";
    }






}
