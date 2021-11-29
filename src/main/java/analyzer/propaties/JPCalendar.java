package analyzer.propaties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.lang.System.exit;

public abstract class JPCalendar extends Calendar {

    public static Calendar parseCalendar(String createdDate) {
        Calendar cal = Calendar.getInstance();
        final SimpleDateFormat strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            cal.setTime(strDate.parse(createdDate));
        } catch (ParseException e) {
            e.printStackTrace();
            exit(-1);
        }

        return (Calendar) cal.clone();

    }

    public static Calendar getWeekend(String baseDate){
        final Calendar date = parseCalendar(baseDate);
        date.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);

        return date;

    }
    public static Calendar getWeekend(Calendar baseDate,String week){
        switch (week){
            case "SUNDAY":
                baseDate.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
                break;
            default:
                baseDate.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
                break;
        }

        return (Calendar) baseDate.clone();

    }

    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(this.getTime());
    }


    public static String toJPString(Calendar cal){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    private static Calendar getLastWeek(Calendar baseDate){
        final Calendar weekend = getWeekend(baseDate,"SATURDAY");
        weekend.add(Calendar.DAY_OF_MONTH,-7);
        return weekend;
    }

    public Calendar setTime(Calendar cal, int hour,int minute,int second){
        cal.set(Calendar.HOUR,hour);
        cal.set(Calendar.MINUTE,minute);
        cal.set(Calendar.SECOND,second);
        return (Calendar) cal.clone();

    }




}
