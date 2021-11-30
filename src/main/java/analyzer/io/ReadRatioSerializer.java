package analyzer.io;

import analyzer.propaties.Cal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ReadRatioSerializer {
    private Map<String,Object> jsonObject = new HashMap<>();

    final SimpleDateFormat strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    final Calendar START_ANALYZE = Cal.parseCalendar("2021-09-26 00:00:00");
    Calendar END_ANALYZE = Calendar.getInstance();


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
