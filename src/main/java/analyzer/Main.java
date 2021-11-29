package analyzer;

import analyzer.propaties.JPCalendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.System.exit;

public class Main {
    public static void main(String args[]){
        final String filename = args[0];
        final String StrStartDate = "2021-10-11 00:00:00";
        final String StrEndDate = "2021-12-10 23:59:59";
        List<List<AnalyzeResult>> results = new ArrayList<>();
        History history=null;

        try {
            history = new History(filename);
        } catch (IOException e) {
            e.printStackTrace();
            exit(-1);
        }

        final Calendar startDate = JPCalendar.parseCalendar(StrStartDate);
        final Calendar endDate = JPCalendar.parseCalendar(StrEndDate);

        Calendar nextStart = (Calendar) startDate.clone();
        Calendar nextEnd = JPCalendar.getWeekend(startDate,"SATURDAY");




        while(nextStart.before(endDate)){
            nextStart.set(Calendar.HOUR,0);
            nextStart.set(Calendar.MINUTE,0);
            nextStart.set(Calendar.SECOND,0);
            nextStart.set(Calendar.AM_PM,Calendar.AM);

            nextEnd.set(Calendar.HOUR,23);
            nextEnd.set(Calendar.MINUTE,59);
            nextEnd.set(Calendar.SECOND,59);
            nextEnd.set(Calendar.AM_PM,Calendar.PM);

            final String strNextStart= JPCalendar.toJPString(nextStart);
            final String strNextEnd = JPCalendar.toJPString(nextEnd);

            //メイン処理

//            try{
//                results.add(Analyzer.analyze(strNextStart,strNextEnd,history));
//
//            }catch (IOException e){
//                e.printStackTrace();
//            }

            //次回変数セット
            //開始日セット(現在終了日+1)
            nextStart.setTime(nextEnd.getTime());
            nextStart.add(Calendar.DAY_OF_MONTH,1);

            //終了日セット(1週間後)
            nextEnd.add(Calendar.DAY_OF_MONTH,7);
            history.allCounterReset();


            //debug
            System.out.println(strNextStart);
            System.out.println(strNextEnd);

        }

    }


}
