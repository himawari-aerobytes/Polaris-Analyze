package analyzer.analyze;

import analyzer.History;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static analyzer.propaties.Cal.nextOrSame;
import static analyzer.propaties.Cal.toLocalDateTime;
import static java.lang.System.exit;

public class Controller {
    public static List<Map<String, Result>> getResults() {
        final String filename = "source.csv";

        final LocalDateTime StartDate = toLocalDateTime("2021-09-26 00:00:00");
        final LocalDateTime EndDate = toLocalDateTime("2021-11-24 23:59:59");

        List<Map<String, Result>> results = new ArrayList<>();
        List<String> date = new ArrayList<>();
        List<Double> value = new ArrayList<>();

        History history = new History();

        try {
            history = new History(filename);
        } catch (IOException e) {
            e.printStackTrace();
            exit(-1);
        }


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

        return results;
    }


}
