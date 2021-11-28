package analyzer.io;

import analyzer.propaties.JPCalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ReadRatioSerializer {
    private Map<String,Object> jsonObject = new HashMap<>();
    final SimpleDateFormat strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    final Calendar START_ANALYZE = JPCalendar.parseCalendar("2021-09-26 00:00:00");
    final Calendar END_ANALYZE = JPCalendar.parseCalendar("2021-11-13 23:59:59");

    final Calendar TODAY = Calendar.getInstance();

    private String calToString(Calendar cal){
        final int month =  cal.get(Calendar.MONTH);
        final int date = cal.get(Calendar.DATE);

        return (month+"/"+date);
    }


    public ReadRatioSerializer(){}

    public void entryData(Calendar startDate,Calendar endDate,int value){
        final String start = calToString(startDate);
        final String end = calToString(endDate);

        jsonObject.put(start+"〜"+end,value);
    }




}
