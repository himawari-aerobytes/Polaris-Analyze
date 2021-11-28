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

        return cal;

    }


}
