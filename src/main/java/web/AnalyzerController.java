package web;

import analyzer.analyze.Result;
import analyzer.analyze.ResultsArray;
import analyzer.analyze.Store;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import java.util.*;



@Controller
public class AnalyzerController {

    @GetMapping("/")
    public String helloGradleGet(Model model) {
        Store store = new Store();
        List<String> B3Label = new ArrayList<>();
        List<String> B4Label = new ArrayList<>();
        List<String> M2Label = new ArrayList<>();

        List<Double> B3Value = new ArrayList<>();
        List<Double> B4Value = new ArrayList<>();
        List<Double> M2Value = new ArrayList<>();

        String title = store.term;


        for(ResultsArray result : store.returnValue){
            switch (result.getGrade()){
                case "B3":
                    B3Label.addAll(result.getDate());
                    B3Value.addAll(result.getValue());
                    break;
                case "B4":
                    B4Label.addAll(result.getDate());
                    B4Value.addAll(result.getValue());
                    break;
                case "M2":
                    M2Label.addAll(result.getDate());
                    M2Value.addAll(result.getValue());
                    break;
                default:
                    break;
            }
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
