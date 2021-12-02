package analyzer.propaties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

import static java.lang.System.exit;

public abstract class Cal{

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static LocalDateTime toLocalDateTime(String date)  {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date formatDate = null;
        try {
            formatDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return LocalDateTime.ofInstant(formatDate.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDateTime nextOrSame(LocalDateTime d,String week){
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

    public static String toDateString(LocalDateTime d){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return d.format(dtf);
    }


}
