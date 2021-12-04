package analyzer.logic.analyze;

import analyzer.logic.History;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static analyzer.propaties.Cal.nextOrSame;
import static analyzer.propaties.Cal.toLocalDateTime;


public class Store {
    public List<Results> returnValue = new ArrayList<>();
    public Results B3 = new Results();
    public Results B4 = new Results();
    public Results M2 = new Results();
    public String term;


    public Store(History history) {

        final LocalDateTime StartDate = toLocalDateTime("2021-07-16 00:00:00");
        final LocalDateTime EndDate = toLocalDateTime("2021-12-03 23:59:59");

        this.term ="既読率の推移(" +StartDate.getMonthValue()+"/"+StartDate.getDayOfMonth()+" ~ "+EndDate.getMonthValue()+"/"+EndDate.getDayOfMonth()+")";
        List<List<Map<String, Object>>> results = new ArrayList<>();

        LocalDateTime nextStart = StartDate;
        LocalDateTime nextEnd = nextOrSame(StartDate,"SATURDAY");

        while (nextStart.isBefore(EndDate)) {
            //0:00:00　に設定
            nextStart.with(LocalTime.of(0,0,0));
            //23:59:59に設定
            nextEnd.with(LocalTime.of(23,59,59));

            //メイン処理
            try {
                results.add(Analyzer.analyze(nextStart,nextEnd, history));

            } catch (IOException e) {
                e.printStackTrace();
            }

            //次回変数セット
            final LocalDateTime tmpNextEnd = nextEnd.minusNanos(0); //オブジェクトのコピー作成
            nextStart = tmpNextEnd.plusDays(1);
            nextEnd = nextEnd.plusDays(7);


            history.allCounterReset();

        }

        for(List<Map<String,Object>> result : results){
            for(Map<String,Object> x : result){
               switch ((String) x.get("grade")){
                   case "B3":
                       B3.addValue((Double) x.get("percentage"));
                       B3.addKey((String) x.get("date"));
                       break;
                   case "B4":
                       B4.addValue((Double) x.get("percentage"));
                       B4.addKey((String) x.get("date"));
                       break;
                   case "M2":
                       M2.addValue((Double) x.get("percentage"));
                       M2.addKey((String) x.get("date"));
                       break;
                   default:
                       break;
               }

           }


        }

        B3.setGrade("B3");
        B4.setGrade("B4");
        M2.setGrade("M2");


        returnValue.add(B3);
        returnValue.add(B4);
        returnValue.add(M2);

    }


}
