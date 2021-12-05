package web;

import analyzer.propaties.Graph;
import analyzer.logic.History;
import analyzer.analyze.ResultsArray;
import analyzer.analyze.Store;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;


@Controller
public class AnalyzerController {

    @GetMapping("/")
    public String helloGradleGet(Model model) {
        History history = new History("source.csv");
        Store store = new Store(history);

        Graph<String,Double> B3 = new Graph<>();
        Graph<String,Double> B4 = new Graph<>();
        Graph<String,Double> M2 = new Graph<>();

        String title = store.term;


        for(ResultsArray result : store.returnValue){
            switch (result.getGrade()){
                case "B3":
                    B3.addValue(result.getValue());
                    B3.addKey(result.getDate());
                    break;
                case "B4":
                    B4.addValue(result.getValue());
                    B4.addKey(result.getDate());
                    break;
                case "M2":
                    M2.addValue(result.getValue());
                    M2.addKey(result.getDate());
                    break;
                default:
                    break;
            }
        }

        model.addAttribute("B3Label",B3.getKey());
        model.addAttribute("B4Label",B4.getKey());
        model.addAttribute("M2Label",M2.getKey());

        model.addAttribute("B3Value",B3.getValue());
        model.addAttribute("B4Value",B4.getValue());
        model.addAttribute("M2Value",M2.getValue());
        model.addAttribute("title",title);


        model.addAttribute("messageList",history.getHistory());

        return "analyzer";
    }

    @PostMapping
    public String helloGradlePost() {
        return "analyzer";
    }






}
