package analyzer.analyze;

import analyzer.logic.History;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static analyzer.propaties.Cal.nextOrSame;
import static analyzer.propaties.Cal.toLocalDateTime;


public class Store {
    public List<ResultsArray> returnValue = new ArrayList<>();
    public ResultsArray B3 = new ResultsArray();
    public ResultsArray B4 = new ResultsArray();
    public ResultsArray M2 = new ResultsArray();
    public String term;


    public Store(History history) {

        final LocalDateTime StartDate = toLocalDateTime("2021-07-16 00:00:00");
        final LocalDateTime EndDate = toLocalDateTime("2021-12-03 23:59:59");

        this.term ="既読率の推移(" +StartDate.getMonthValue()+"/"+StartDate.getDayOfMonth()+" ~ "+EndDate.getMonthValue()+"/"+EndDate.getDayOfMonth()+")";
        List<List<Result>> results = new ArrayList<>();

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

        for(List<Result> result : results){
            for(Result x : result){

               switch (x.getGrade()){
                   case "B3":
                       B3.addValue(x.getValue());
                       B3.addDate(x.getDate());
                       break;
                   case "B4":
                       B4.addValue(x.getValue());
                       B4.addDate(x.getDate());
                       break;
                   case "M2":
                       M2.addValue(x.getValue());
                       M2.addDate(x.getDate());
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
