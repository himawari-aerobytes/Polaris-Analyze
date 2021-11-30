package analyzer.analyze;

import analyzer.History;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

import static java.lang.System.exit;

public class Controller {
    static final private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static List<Map<String, Result>> getResults() {
        final String filename = "source.csv";

        final LocalDateTime StartDate = toLocalDateTime("2021-09-26 00:00:00");
        final LocalDateTime EndDate = toLocalDateTime("2021-11-24 23:59:59");

        List<Map<String, Result>> results = new ArrayList<>();
        History history = null;

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
                results.add(Analyzer.analyze(toDateString(nextStart),toDateString(nextEnd), history));

            } catch (IOException e) {
                e.printStackTrace();
            }

            //次回変数セット
            final LocalDateTime tmpNextEnd = nextEnd.minusNanos(0);
            nextStart = tmpNextEnd.plusDays(1);
            nextEnd = nextEnd.plusDays(7);


            history.allCounterReset();

        }

        return results;
    }

    private static LocalDateTime toLocalDateTime(String date)  {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date formatDate = null;
        try {
            formatDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return LocalDateTime.ofInstant(formatDate.toInstant(), ZoneId.systemDefault());
    }
    private static LocalDateTime nextOrSame(LocalDateTime d,String week){
        switch (week){
            case "SATURDAY":
                return d.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
            case "SUNDAY":
                return d.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
            default:
                break;
        }

        throw new IllegalArgumentException("入力された曜日は無効です");

    }

    private static String toDateString(LocalDateTime d){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return d.format(dtf);
    }
}
