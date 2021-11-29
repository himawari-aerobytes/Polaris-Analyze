package web;

import analyzer.AnalyzeResult;
import analyzer.Analyzer;
import analyzer.History;
import analyzer.propaties.CUIColor;
import analyzer.propaties.JPCalendar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.*;

import static java.lang.System.exit;

@Controller
public class AnalyzerController {

    @GetMapping("/")
    public String helloGradleGet(Model model) {
        final List<Map<String, AnalyzeResult>> results = getResults();
        List<String> B3Label = new ArrayList<>();
        List<String> B4Label = new ArrayList<>();
        List<String> M2Label = new ArrayList<>();

        List<Double> B3Value = new ArrayList<>();
        List<Double> B4Value = new ArrayList<>();
        List<Double> M2Value = new ArrayList<>();


        for(Map<String,AnalyzeResult> result : results){
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

        return "analyzer";
    }

    @PostMapping
    public String helloGradlePost() {
        return "analyzer";
    }

    public List<Map<String,AnalyzeResult>> getResults() {
        final String filename = "source.csv";
        final String StrStartDate = "2021-9-26 00:00:00";
        final String StrEndDate = "2021-11-24 23:59:59";
        List<Map<String,AnalyzeResult>> results = new ArrayList<>();
        History history = null;

        try {
            history = new History(filename);
        } catch (
                IOException e) {
            e.printStackTrace();
            exit(-1);
        }

        final Calendar startDate = JPCalendar.parseCalendar(StrStartDate);
        final Calendar endDate = JPCalendar.parseCalendar(StrEndDate);

        Calendar nextStart = (Calendar) startDate.clone();
        Calendar nextEnd = JPCalendar.getWeekend(startDate, "SATURDAY");

        while (nextStart.before(endDate)) {
            nextStart.set(Calendar.HOUR, 0);
            nextStart.set(Calendar.MINUTE, 0);
            nextStart.set(Calendar.SECOND, 0);
            nextStart.set(Calendar.AM_PM, Calendar.AM);

            nextEnd.set(Calendar.HOUR, 23);
            nextEnd.set(Calendar.MINUTE, 59);
            nextEnd.set(Calendar.SECOND, 59);
            //nextEnd.set(Calendar.AM_PM, Calendar.PM);

            final String strNextStart = JPCalendar.toJPString(nextStart);
            final String strNextEnd = JPCalendar.toJPString(nextEnd);

            System.out.println("##########");
            System.out.println(strNextStart);
            System.out.println(strNextEnd);

            //メイン処理

            try {
                results.add(Analyzer.analyze(strNextStart, strNextEnd, history));

            } catch (IOException e) {
                e.printStackTrace();
            }

            //次回変数セット
            //開始日セット(現在終了日+1)
            nextStart.setTime(nextEnd.getTime());
            nextStart.add(Calendar.DAY_OF_MONTH, 1);

            //終了日セット(1週間後)
            nextEnd.add(Calendar.DAY_OF_MONTH, 7);
            history.allCounterReset();


            //debug
            System.out.println(strNextStart);
            System.out.println(strNextEnd);

        }

        return results;
    }




}
